package com.Spendless.Product.service.sectionService;

import com.Spendless.Product.dto.SectionDto;
import com.Spendless.Product.exception.SectionAlreadyExistException;
import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.mapper.SectionToSectionDto;
import com.Spendless.Product.model.Section;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.SectionPayload;
import com.Spendless.Product.repository.SectionRepository;
import com.Spendless.Product.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SectionServiceImpl implements SectionService{

    private final SectionRepository repository;
    private final UserRepository userRepository;
    public SectionServiceImpl(SectionRepository repository,UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }


    @Transactional
    public SectionDto createSection(SectionPayload payload) {

        Users user = userRepository.findById(payload.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User doesn't exist"));
        repository.findByName(payload.getName())
                .ifPresent(s -> {
                    throw new SectionAlreadyExistException("Section already exists");
                });
        Section section = new Section();
        section.setName(payload.getName());
        section.setBudget(payload.getBudget());
        section.addUsers(user);
        Section savedSection = repository.save(section);

        return SectionToSectionDto.mapToDto(savedSection);
    }
}
