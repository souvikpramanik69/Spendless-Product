package com.Spendless.Product.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Items {

    @Id
    public String id;
    private String name;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private  List<Product> products;





}
