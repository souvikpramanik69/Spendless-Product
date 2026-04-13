package com.Spendless.Product.controller;

import com.Spendless.Product.dto.ExpenseDto;
import com.Spendless.Product.model.Expenses;
import com.Spendless.Product.payload.ExpensePayload;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.expenseService.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/expense")
    @Operation(summary = "create expense")
    public ResponseEntity<ApiResponse<ExpenseDto>> createExpense( @RequestBody ExpensePayload payload){
        return ResponseEntity.status(201).body(new ApiResponse<>(201,"Expense has been added sucessfully", ApiResponse.Status.SUCCESS,expenseService.createExpense(payload)));
    }

    @DeleteMapping("/expense/{expenseId}/section/{sectionId}/user/{userId}")
    @Operation(summary = "create expense")
    public ResponseEntity<ApiResponse<ExpenseDto>> createExpense(@PathVariable("userId") UUID userId, @PathVariable("sectionId") UUID sectionId,@PathVariable("expenseId") UUID expenseId
    ){
        System.out.println("Expense "+ expenseId);
        UUID expense = expenseId;
        return ResponseEntity.status(200).body(new ApiResponse<>(200,expenseService.deleteExpenseByCreator(expense,sectionId,userId), ApiResponse.Status.SUCCESS));
    }


    @GetMapping("/section/{sectionId}/expenses")
    @Operation(summary = "all expenses api")
    public ResponseEntity<ApiResponse<List<ExpenseDto>>> getAllExpensesBySectionId(@PathVariable("sectionId") UUID sectionId){
        return ResponseEntity.status(200).body(new ApiResponse<List<ExpenseDto>>(200,"All expenses has been retrieved by section ", ApiResponse.Status.SUCCESS,expenseService.getAllExpensesBySectionId(sectionId)));
    }





}