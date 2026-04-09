package com.Spendless.Product.dto;
import com.Spendless.Product.model.Section;
import com.Spendless.Product.model.Users;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class SectionDto {

    private UUID id;
    private String name;
    private double budget;
    private Set<UserDto> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
