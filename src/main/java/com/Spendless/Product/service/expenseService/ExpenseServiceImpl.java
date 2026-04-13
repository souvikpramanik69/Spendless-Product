package com.Spendless.Product.service.expenseService;

import com.Spendless.Product.dto.ExpenseDto;
import com.Spendless.Product.exception.*;
import com.Spendless.Product.mapper.ExpenseToExpenseDto;
import com.Spendless.Product.model.Expenses;
import com.Spendless.Product.model.Section;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.ExpensePayload;
import com.Spendless.Product.repository.ExpenseRepository;
import com.Spendless.Product.repository.SectionRepository;
import com.Spendless.Product.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements  ExpenseService{

    private final UserRepository userRepository;
    private final SectionRepository sectionRepository;
    private final ExpenseRepository expenseRepository;
    public ExpenseServiceImpl(UserRepository userRepository,SectionRepository sectionRepository,ExpenseRepository expenseRepository){
        this.userRepository = userRepository;
        this.sectionRepository = sectionRepository;
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public ExpenseDto createExpense(ExpensePayload payload){
        Users user = userRepository.findById(payload.getUser_id()).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
        Section section = sectionRepository.findById(payload.getSection_id()).orElseThrow(()-> new SectionNotFoundException("Section doesn't exist"));
         Double totalCost = expenseRepository.getTotalAmountBySectionId(payload.getSection_id());
         double currentCost = payload.getAmount() + totalCost;
         double payloadAmount = payload.getAmount();
         double budget = section.getBudget();
        if( section.getBudget() == 0 || payloadAmount > budget || currentCost >  budget ){
            throw new BudgetExceedException("Budget has been exceed , please increase your budget");
        }
        Expenses expenses = new Expenses();
        expenses.setAmount(payload.getAmount());
        expenses.setName(payload.getName());
        expenses.setSection(section);
        expenses.setUsers(user);
        section.setTotalCost(currentCost);
        sectionRepository.save(section);
        return ExpenseToExpenseDto.mapToDto(expenseRepository.save(expenses));
    }

    @Transactional
    public  String deleteExpenseByCreator(UUID expenseId,UUID sectionId, UUID creator_id){


       Expenses expense =  expenseRepository.findById(expenseId).orElseThrow(()-> new ExpenseNotFoundException("Expense doesn't exist"));

       Users user = userRepository.findById(creator_id).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
      Section section = sectionRepository.findById(sectionId).orElseThrow(()-> new ExpenseNotFoundException("Section doesn't exist"));
      if(expense.getSection().getId() != section.getId()){
          throw new ExpenseNotFoundException("Expense not belongs to this section");
      }
        System.out.println("Creator " + creator_id);
        System.out.println("User id " + section.getCreated_user_id());


      if(!section.getCreated_user_id().equals(user.getId())){
          throw new SectionOwnerNotFoundException("You don't have any access to delete this expense , because you are not owner in this section");
      }
      expenseRepository.deleteById(expenseId);
      return "Expense has been deleted successfully by "+user.getEmail()+" (Owner)";
    }


    public List<ExpenseDto> getAllExpensesBySectionId(UUID sectionId){
        return  expenseRepository.getAllSectionsBySectionId(sectionId).stream().map(item->{
            ExpenseDto dto = new ExpenseDto();
            dto.setAmount(item.getAmount());
            dto.setName(item.getName());
            dto.setId(item.getId());
            dto.setPaid_by(item.getUsers().getEmail());
            dto.setUpdatedAt(item.getUpdatedAt());
            dto.setCreatedAt(item.getCreatedAt());
            return dto;

        }).toList();
    }



}
