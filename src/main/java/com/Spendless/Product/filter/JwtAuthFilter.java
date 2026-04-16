package com.Spendless.Product.filter;

import com.Spendless.Product.exception.TokenNotValidException;
import com.Spendless.Product.exception.UnAuthorizeException;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.userService.CustomUserDetailsService;
import com.Spendless.Product.utils.JwtUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    ObjectMapper objectMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorization = request.getHeader("Authorization");
            try {
                if(authorization == null || !authorization.startsWith("Bearer ")){
                    ObjectMapper mapper = new ObjectMapper();
                    ApiResponse<String> responseData = new ApiResponse<>();
                    responseData.setStatus(ApiResponse.Status.ERROR);
                    responseData.setCode(401);
                    responseData.setMessage("Authorization header missing");
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write( mapper.writeValueAsString(responseData));
                    return;
                }
                String token = authorization.substring(7);
                System.out.println("Token in middleware " + token);
                String email = jwtUtility.extractUsername(token);

                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);


                 if(jwtUtility.validateToken(token,userDetails)){
                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                 }
                 else {
                     ObjectMapper mapper = new ObjectMapper();
                     ApiResponse<String> responseData = new ApiResponse<>();
                     responseData.setStatus(ApiResponse.Status.ERROR);
                     responseData.setCode(401);
                     responseData.setMessage("Invalid Token");
                     response.setContentType("application/json");
                     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                     response.getWriter().write( mapper.writeValueAsString(responseData));
                 }
                }

                filterChain.doFilter(request,response);

            } catch (Exception e) {
                ObjectMapper mapper = new ObjectMapper();
                ApiResponse<String> responseData = new ApiResponse<>();
                responseData.setStatus(ApiResponse.Status.ERROR);
                responseData.setCode(401);
                responseData.setMessage(e.getMessage());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write( mapper.writeValueAsString(responseData));
            }

    }





}
