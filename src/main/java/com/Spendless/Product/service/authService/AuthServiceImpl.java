package com.Spendless.Product.service.authService;

import com.Spendless.Product.dto.AuthDto;
import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.exception.UserAlreadyExistException;
import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.mapper.UserToUserDtoMapper;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDto  signupService( UserPayload payload){

        Boolean isUserExist = userRepository.findByEmail(payload.getEmail()).isPresent();
        if(isUserExist){
          throw new UserAlreadyExistException("User already exist");
        }

    Users newUser = new Users();
    newUser.setEmail(payload.getEmail());
    newUser.setName(payload.getName());
    newUser.setRole("ROLE_"+payload.getRole().toUpperCase());
    newUser.setPassword(passwordEncoder.encode(payload.getPassword()));
    newUser.setProvider_id(Boolean.parseBoolean(payload.getProvider_id()) ? payload.getProvider_id() : "");
    newUser.setProvider_name(payload.getProvider_name());
    UserDto dto = UserToUserDtoMapper.mapToDto(userRepository.save(newUser));

    return dto;




    }


    public Users singinService(UserPayload payload){

    Users user =   userRepository.findByEmail(payload.getEmail()).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
    if(!passwordEncoder.matches(payload.getPassword(),user.getPassword())){
      throw new UserNotFoundException("User doesn't exist");
    }
    return user;
    }


}
