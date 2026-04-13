package com.Spendless.Product.dto;
import com.Spendless.Product.model.Section;
import com.Spendless.Product.model.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionDto {

    private UUID id;
    private String name;
    private double budget;
    private double totalCost;
    private Set<UserDto> users;
    private double remainingBudget;
    public List<ExpenseDto> expenses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String created_user_name;
    private UUID created_user_id;



}
