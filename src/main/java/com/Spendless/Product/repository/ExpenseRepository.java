package com.Spendless.Product.repository;

import com.Spendless.Product.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expenses, UUID> {

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expenses e WHERE e.section.id = :sectionId")
    Double getTotalAmountBySectionId(@Param("sectionId") UUID sectionId);
}
