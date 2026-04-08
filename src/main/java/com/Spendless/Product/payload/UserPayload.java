package com.Spendless.Product.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPayload {
    @Schema(description = "User email", example = "test@gmail.com")
    private String email;
    @Schema(description = "User email", example = "test@123")
    private String password;

}
