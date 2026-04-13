package com.Spendless.Product.service.billService;

import com.Spendless.Product.dto.BillDto;
import com.Spendless.Product.exception.SectionNotFoundException;
import com.Spendless.Product.model.Section;
import com.Spendless.Product.repository.ExpenseRepository;
import com.Spendless.Product.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BillServiceImpl implements  BillService{

    private final SectionRepository sectionRepository;
    private final ExpenseRepository expenseRepository;
    public BillServiceImpl(SectionRepository sectionRepository,ExpenseRepository expenseRepository){
        this.sectionRepository = sectionRepository;
        this.expenseRepository = expenseRepository;
    }

    public BillDto createBill(UUID section_id){
        Section section = sectionRepository.findById(section_id).orElseThrow(()-> new SectionNotFoundException("Section doesn't exist"));
        Map<String, Object> data = new HashMap<>();
        Double totalCost = section.getTotalCost();
        Double budget = section.getBudget();
        section.getUsers().forEach((item)->{
            Map<String, Object> paidData = new HashMap<>();
            double paidByUser = expenseRepository.getTotalAmountByUserId(item.getId(),section_id);
            Integer totalUser = section.getUsers().size();
            Double perHeadAmount = totalCost/totalUser;
            paidData.put("total paid",paidByUser);
            System.out.println("Total paid by " + item.getName()+" "+ paidByUser);
            if(paidByUser > perHeadAmount){
                double willGetData =  paidByUser - perHeadAmount;
                paidData.put(item.getEmail()+" will get",willGetData);
                System.out.println("Running in if");

            }
            else {
                double willPay = perHeadAmount - paidByUser;
                paidData.put(item.getEmail()+" will pay",willPay);
                System.out.println("Running in if");
            }

            data.put(item.getEmail(),paidData);

        });
        BillDto newBill = new BillDto();
        newBill.setTotalBudget(section.getBudget());
        newBill.setSectionName(section.getName());
        newBill.setTotalCost(totalCost);
        newBill.setPaidByData(data);
        newBill.setId(UUID.randomUUID());
        newBill.setCreatedAt(LocalDateTime.now());
        newBill.setUpdatedAt(LocalDateTime.now());
        return newBill;

    }


}
