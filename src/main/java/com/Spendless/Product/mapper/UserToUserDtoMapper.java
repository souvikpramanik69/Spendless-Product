package com.Spendless.Product.mapper;

import com.Spendless.Product.dto.AuthDto;
import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.model.Users;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserToUserDtoMapper extends  UserDto{
    public static UserDto mapToDto(Users user){

        UserDto dto = new UserDto();
         dto.setEmail(user.getEmail());
         dto.setId(user.getId());
         dto.setPassword(user.getPassword());
         dto.setUpdatedAt(user.getUpdatedAt());
         dto.setCreatedAt(user.getUpdatedAt());
         dto.setProvider_id(user.getProvider_id());
         dto.setProvider_name(user.getProvider_name());
         return dto;

    }




}
