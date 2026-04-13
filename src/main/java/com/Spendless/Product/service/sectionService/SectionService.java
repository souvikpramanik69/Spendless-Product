package com.Spendless.Product.service.sectionService;

import com.Spendless.Product.dto.SectionDto;
import com.Spendless.Product.payload.SectionPayload;

import java.util.List;
import java.util.UUID;

public interface SectionService {

    public SectionDto createSection(SectionPayload payload);
    public List<SectionDto> getAllSections();
    public String deleteSection(UUID id);
    public SectionDto addUpdateUsersIntoSection(SectionPayload payload);
    public List<SectionDto> getAllSectionByUserId(UUID id);



}
