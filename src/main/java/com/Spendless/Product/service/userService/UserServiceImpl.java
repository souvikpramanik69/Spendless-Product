package com.Spendless.Product.service.userService;

import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.exception.UserAlreadyExistException;
import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.mapper.UserToUserDtoMapper;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.repository.UserRepository;
import com.Spendless.Product.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDto  signupService( UserPayload payload){

        Boolean isUserExist = userRepository.findByEmail(payload.getEmail()).isPresent();
        if(isUserExist){
          throw new UserAlreadyExistException("User already exist");
        }

    Users newUser = new Users();
    newUser.setEmail(payload.getEmail());
    newUser.setPassword(payload.getPassword());
    UserDto dto = UserToUserDtoMapper.mapToDto(userRepository.save(newUser));

    return dto;




    }


    public UserDto singinService( UserPayload payload){
       Users userData = userRepository.findByEmail(payload.getEmail()).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
       return UserToUserDtoMapper.mapToDto(userData);
    }


}
