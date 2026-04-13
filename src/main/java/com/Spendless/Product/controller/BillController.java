package com.Spendless.Product.controller;

import com.Spendless.Product.dto.BillDto;
import com.Spendless.Product.dto.ExpenseDto;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.billService.BillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Bill")
public class BillController {

    public final BillService billService;
    public BillController(BillService billService){
        this.billService = billService;
    }

    @GetMapping("section/{sectionId}/bill")
    public ResponseEntity<ApiResponse<BillDto>> createBill(@PathVariable UUID sectionId){
        return ResponseEntity.status(200).body(new ApiResponse<BillDto>(200,"Bill generated successfully", ApiResponse.Status.SUCCESS,billService.createBill(sectionId)));
    }


}
