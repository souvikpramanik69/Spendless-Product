package com.Spendless.Product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String name;
    private double budget = 0.0;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToMany(mappedBy = "sections")
    @JsonBackReference
    private Set<Users> users = new HashSet<>();

    public Section(UUID id,String name,double budget,Set<Users> users){
       this.id = id;
       this.name=name;
       this.budget = budget;
       this.users = users;
    }

    public void addUsers(Users user){
        this.getUsers().add(user);
        user.getSections().add(this);
    }



}
