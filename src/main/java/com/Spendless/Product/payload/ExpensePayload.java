package com.Spendless.Product.payload;

import com.Spendless.Product.model.Section;
import com.Spendless.Product.model.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ExpensePayload {
    private UUID id;
    private String name;
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID user_id;
    private UUID section_id;
}
