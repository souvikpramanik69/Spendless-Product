package com.Spendless.Product.repository;

import com.Spendless.Product.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, UUID> {

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expenses e WHERE e.section.id = :sectionId")
    Double getTotalAmountBySectionId(@Param("sectionId") UUID sectionId);
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expenses e WHERE e.users.id = :userId and e.section.id = :sectionId")
    Double getTotalAmountByUserId(@Param("userId") UUID userId,@Param("sectionId") UUID sectionId);
    List<Expenses> getAllSectionsBySectionId(@Param("sectionId") UUID sectionId);
}
