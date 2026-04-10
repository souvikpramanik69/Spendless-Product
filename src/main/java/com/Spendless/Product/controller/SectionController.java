package com.Spendless.Product.controller;

import com.Spendless.Product.dto.SectionDto;
import com.Spendless.Product.payload.SectionPayload;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.sectionService.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Section Api")
@RequestMapping("/api/v1")
public class SectionController {

   private final SectionService sectionService;
   public SectionController(SectionService sectionService){
       this.sectionService = sectionService;
   }

    @PostMapping("/section")
    @Operation(summary = "Register new section", description = "Creates a new section")
    public ResponseEntity<ApiResponse<SectionDto>> createSection(@Valid @RequestBody SectionPayload payload){
        return ResponseEntity.status(201).body(new ApiResponse<SectionDto>(201,"Section created successfully", ApiResponse.Status.SUCCESS,sectionService.createSection(payload)));
    }


    @GetMapping("/sections")
    @Operation(summary = "Get all sections",description = "Get all sections with all users")
    public ResponseEntity<ApiResponse<List<SectionDto>>> getAllSections(){
       return ResponseEntity.status(200).body(new ApiResponse<>(200,"All section has been retrieved", ApiResponse.Status.SUCCESS,sectionService.getAllSections()));
    }

    @DeleteMapping("/section/{id}")
    @Operation(summary = "Delete section",description = "Delete Section by id")
    public ResponseEntity<ApiResponse<List<SectionDto>>> deleteSection(@PathVariable UUID id){
       return ResponseEntity.status(200).body(new ApiResponse<>(200,sectionService.deleteSection(id), ApiResponse.Status.SUCCESS));
    }

    @PutMapping("/section/users")
    public ResponseEntity<ApiResponse<SectionDto>> addUsersIntoSection(@RequestBody SectionPayload payload){
     return ResponseEntity.status(200).body(new ApiResponse<>(200,"User added successfully in this section", ApiResponse.Status.SUCCESS,sectionService.addUpdateUsersIntoSection(payload)));
    }

    @GetMapping("user/{id}/sections")
    public ResponseEntity<ApiResponse<List<SectionDto>>> getAllSectionByUserId(@PathVariable UUID id){
       return ResponseEntity.status(200).body(new ApiResponse<>(200,"All section has been retrieved by " + id, ApiResponse.Status.SUCCESS,sectionService.getAllSectionByUserId(id)));
    }






}
