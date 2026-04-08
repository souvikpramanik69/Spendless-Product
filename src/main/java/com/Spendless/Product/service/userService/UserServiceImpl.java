package com.Spendless.Product.service.userService;

import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.mapper.UserToUserDtoMapper;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.repository.UserRepository;
import com.Spendless.Product.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ApiResponse<String,UserDto> createUserService(UserPayload payload){
        ApiResponse<String,UserDto> response = new ApiResponse<String,UserDto>();
try{
    Users newUser = new Users();
    newUser.setId(UUID.randomUUID());
    newUser.setEmail(payload.getEmail());
    newUser.setPassword(payload.getPassword());
    userRepository.save(newUser);
    UserDto dto = UserToUserDtoMapper.mapToDto(newUser);

    response.setCode(201);
    response.setMessage("Signup Successfully");
    response.setData(dto);
    return response;
}catch (Exception e){
    response.setCode(500);
    response.setMessage(e.getMessage());
    response.setStatus(ApiResponse.Status.ERROR);
    return ResponseEntity.status(response.getCode()).body(response).getBody();
}






    }
}
