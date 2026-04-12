package com.Spendless.Product.controller;

import com.Spendless.Product.dto.ExpenseDto;
import com.Spendless.Product.model.Expenses;
import com.Spendless.Product.payload.ExpensePayload;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.expenseService.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/expense")
    public ResponseEntity<ApiResponse<ExpenseDto>> createExpense( @RequestBody ExpensePayload payload){
        return ResponseEntity.status(201).body(new ApiResponse<>(201,"Expense has been added sucessfully", ApiResponse.Status.SUCCESS,expenseService.createExpense(payload)));
    }

    @DeleteMapping("/expense/{expenseId}/section/{sectionId}/user/{userId}")
    public ResponseEntity<ApiResponse<ExpenseDto>> createExpense(
                                                                 @PathVariable("userId") UUID userId,
                                                                 @PathVariable("sectionId") UUID sectionId,
                                                                  @PathVariable("expenseId") UUID expenseId
    ){
        System.out.println("Expense "+ expenseId);
        UUID expense = expenseId;
        return ResponseEntity.status(200).body(new ApiResponse<>(200,expenseService.deleteExpenseByCreator(expense,sectionId,userId), ApiResponse.Status.SUCCESS));
    }


}