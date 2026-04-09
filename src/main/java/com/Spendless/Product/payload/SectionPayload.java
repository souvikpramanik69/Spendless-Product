package com.Spendless.Product.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SectionPayload {
    @Schema(description = "User name", example = "Section 1")
    @NotBlank(message = "Name is required")
    private String name;
    
    private double budget;

    private UUID userId;

}