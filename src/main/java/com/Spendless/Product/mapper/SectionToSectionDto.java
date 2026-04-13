package com.Spendless.Product.mapper;

import com.Spendless.Product.dto.SectionDto;
import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.model.Section;
import com.Spendless.Product.model.Users;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class SectionToSectionDto {

    public static SectionDto mapToDto(Section section){

        SectionDto dto = new SectionDto();
        dto.setId(section.getId());
        dto.setName(section.getName());
        dto.setBudget(section.getBudget());
        dto.setUpdatedAt(section.getUpdatedAt());
        dto.setCreatedAt(section.getUpdatedAt());
        dto.setCreated_user_id(section.getCreated_user_id());
        dto.setCreated_user_name(section.getCreated_user_name());
//        double remainingBudget = section.getBudget() - section.getTotalCost();
//        dto.setRemainingBudget(remainingBudget);
        Set<UserDto> users = section.getUsers().stream().map((item)->{
            UserDto dtoData = new UserDto();
            dtoData.setId(item.getId());
            dtoData.setEmail(item.getEmail());
            dtoData.setUpdatedAt(item.getUpdatedAt());
            dtoData.setCreatedAt(item.getCreatedAt());
            dtoData.setPassword(item.getPassword());
            return dtoData;
        }).collect(Collectors.toSet());
        dto.setUsers(users);
        return dto;

    }



}
