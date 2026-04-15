package com.Spendless.Product.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
public class Users implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String email;
    private String password;
    private String name;
    private String provider_id;
    private String provider_name;

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
    @JsonManagedReference
    private List<Expenses> expenses = new ArrayList<>();


    private String role;


    public void addSection(Section section){
        this.getSections().add(section);
        section.getUsers().add(this);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
