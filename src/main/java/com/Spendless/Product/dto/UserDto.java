package com.Spendless.Product.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserDto  {

    private UUID id;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
