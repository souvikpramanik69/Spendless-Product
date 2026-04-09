package com.Spendless.Product.dto;

import com.Spendless.Product.model.Expenses;
import com.Spendless.Product.model.Section;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserDto  {

    private UUID id;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Section> sections;
    private List<Expenses> expenses;

}
