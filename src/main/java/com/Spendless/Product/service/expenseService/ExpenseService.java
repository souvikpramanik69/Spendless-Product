package com.Spendless.Product.service.expenseService;

import com.Spendless.Product.dto.ExpenseDto;
import com.Spendless.Product.payload.ExpensePayload;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {

    public ExpenseDto createExpense(ExpensePayload payload);
    public String deleteExpenseByCreator(UUID expenseId,UUID sectionId, UUID creator_id);
    public List<ExpenseDto> getAllExpensesBySectionId(UUID sectionId);





}
