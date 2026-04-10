package com.Spendless.Product.service.authService;


import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.payload.UserPayload;

public interface AuthService {

    public UserDto signupService( UserPayload payload);
    public UserDto singinService( UserPayload payload);

}
