package com.Spendless.Product.controller;

import com.Spendless.Product.dto.SectionDto;
import com.Spendless.Product.payload.SectionPayload;
import com.Spendless.Product.repository.UserRepository;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.sectionService.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "Section Api")
@RequestMapping("/api")
public class SectionController {

   private final SectionService sectionService;
   public SectionController(SectionService sectionService){
       this.sectionService = sectionService;
   }

   @PostMapping("/section")
   @Operation(summary = "Register new section", description = "Creates a new section")
    public ResponseEntity<ApiResponse<SectionDto>> createSection(@Valid @RequestBody SectionPayload payload){
       SectionDto data = sectionService.createSection(payload);
        return ResponseEntity.status(201).body(new ApiResponse<SectionDto>(201,"Section created successfully", ApiResponse.Status.SUCCESS,data));
    }




}
