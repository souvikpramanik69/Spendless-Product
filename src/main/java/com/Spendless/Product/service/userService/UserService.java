package com.Spendless.Product.service.userService;

import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.payload.UserPayload;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<UserDto> getAllUsers();
    public String deleteUserById(UUID id);
    public UserDto updateUser(UserPayload payload);



}
