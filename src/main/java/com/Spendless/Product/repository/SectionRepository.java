package com.Spendless.Product.repository;

import com.Spendless.Product.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface SectionRepository  extends JpaRepository<Section, UUID> {

    Optional<Section> findByName(String name);
    List<Section> findByUsersId(UUID userId);

}
