package com.Spendless.Product.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String email;
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="user_sections",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name="section_id")
    )
    @JsonManagedReference
    private Set<Section> sections = new HashSet<>();

    @OneToMany( mappedBy = "users", cascade = CascadeType.ALL)
    private List<Expenses> expenses;

    public void addSection(Section section){
        this.getSections().add(section);
        section.getUsers().add(this);
    }


}
