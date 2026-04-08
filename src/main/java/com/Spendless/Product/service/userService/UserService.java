package com.Spendless.Product.service.userService;


import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.response.ApiResponse;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserService {

    public UserDto signupService( UserPayload payload);
    public UserDto singinService( UserPayload payload);

}
