package com.Spendless.Product.service.userService;

import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.mapper.UserToUserDtoMapper;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers(){
       List<Users> userData = userRepository.findAll();
       return userData.stream().map(item->{
           UserDto dto = new UserDto();
           dto.setId(item.getId());
           dto.setPassword(item.getPassword());
           dto.setEmail(item.getEmail());
           dto.setExpenses(item.getExpenses());
           dto.setSections(item.getSections());
           dto.setCreatedAt(item.getCreatedAt());
           dto.setUpdatedAt(item.getUpdatedAt());
           return dto;
       }).toList();
    }

    public String deleteUserById(UUID id){
       Users userData = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
       userRepository.deleteById(id);
       return "User has been deleted successfully";
    }

    @Transactional
    public UserDto updateUser(UserPayload payload){
        Users userData = userRepository.findById(payload.getId()).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
        userData.setPassword(payload.getPassword());
        userData.setEmail(userData.getEmail());
        return UserToUserDtoMapper.mapToDto(userData);

    }


}
