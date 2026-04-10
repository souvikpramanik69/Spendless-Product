package com.Spendless.Product.service.sectionService;

import com.Spendless.Product.dto.SectionDto;
import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.exception.SectionAlreadyExistException;
import com.Spendless.Product.exception.SectionNotFoundException;
import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.mapper.SectionToSectionDto;
import com.Spendless.Product.model.Section;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.SectionPayload;
import com.Spendless.Product.repository.SectionRepository;
import com.Spendless.Product.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService{

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public SectionServiceImpl(SectionRepository sectionRepository,UserRepository userRepository){
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public SectionDto createSection(SectionPayload payload) {

        Users user = userRepository.findById(payload.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User doesn't exist"));
        sectionRepository.findByName(payload.getName())
                .ifPresent(s -> {
                    throw new SectionAlreadyExistException("Section already exists");
                });
        Section section = new Section();
        section.setName(payload.getName());
        section.setBudget(payload.getBudget());
        section.addUsers(user);
        Section savedSection = sectionRepository.save(section);

        return SectionToSectionDto.mapToDto(savedSection);
    }

    public List<SectionDto> getAllSections(){
     return sectionRepository.findAll().stream().map(item->{
         SectionDto dto = new SectionDto();
         dto.setId(item.getId());
        Set<UserDto> userDtoData =  item.getUsers().stream().map((value)->{
             UserDto userDto = new UserDto();
             userDto.setId(value.getId());
             userDto.setEmail(value.getEmail());
             userDto.setExpenses(value.getExpenses());
             userDto.setCreatedAt(value.getCreatedAt());
             userDto.setUpdatedAt(value.getUpdatedAt());
             return userDto;
         }).collect(Collectors.toSet());
        dto.setUsers(userDtoData);
        dto.setName(item.getName());
        dto.setUpdatedAt(item.getUpdatedAt());
        dto.setBudget(item.getBudget());
        dto.setCreatedAt(item.getCreatedAt());
        return dto;
     }).toList();


    }

    @Transactional
    public String deleteSection(UUID id){
      sectionRepository.findById(id).orElseThrow(()-> new SectionNotFoundException("Section doesn't exist"));
      sectionRepository.deleteById(id);
      return "Section has been deleted successfully";
    }

    @Transactional
    public SectionDto addUpdateUsersIntoSection(SectionPayload payload){
        Section section = sectionRepository.findById(payload.getId()).orElseThrow(()-> new SectionNotFoundException("Section doesn't exist"));
        List<Users> users = userRepository.findAllById(payload.getUsers());
        if(users.isEmpty()){
            throw new UserNotFoundException("No Users Found");
        }
        users.forEach(section::addUsers);
        return SectionToSectionDto.mapToDto(sectionRepository.save(section));
    }

    public List<SectionDto> getAllSectionByUserId(UUID id){
        userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
       return  sectionRepository.findByUsersId(id).stream().map((item)->{
            SectionDto dto = new SectionDto();
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setUpdatedAt(item.getUpdatedAt());
            dto.setCreatedAt(item.getCreatedAt());
             return dto;
        }).toList();

    }





}
