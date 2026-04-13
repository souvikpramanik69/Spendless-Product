package com.Spendless.Product.service.billService;

import com.Spendless.Product.dto.BillDto;

import java.util.UUID;

public interface BillService {

    public BillDto createBill( UUID section_id);

}
