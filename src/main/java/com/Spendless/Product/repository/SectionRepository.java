package com.Spendless.Product.repository;

import com.Spendless.Product.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SectionRepository  extends JpaRepository<Section, UUID> {

    Optional<Section> findByName(String name);

}
