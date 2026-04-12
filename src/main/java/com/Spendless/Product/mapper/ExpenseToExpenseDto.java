package com.Spendless.Product.mapper;

import com.Spendless.Product.dto.ExpenseDto;
import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.model.Expenses;
import com.Spendless.Product.model.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseToExpenseDto {

    public static ExpenseDto mapToDto(Expenses expense){

        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setName(expense.getName());
        dto.setAmount(expense.getAmount());
        dto.setCreatedAt(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());
        dto.setPaid_by(expense.getUsers().getEmail());
        return dto;

    }
}
