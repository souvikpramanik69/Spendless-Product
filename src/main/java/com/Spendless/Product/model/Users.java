package com.Spendless.Product.model;

import com.Spendless.Product.enums.Permissions;
import com.Spendless.Product.enums.Role;
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
import java.util.stream.Collectors;

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


    @Enumerated(EnumType.STRING)
    private Role role;


    public void addSection(Section section){
        this.getSections().add(section);
        section.getUsers().add(this);
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      Set<GrantedAuthority> authorities = new HashSet<>();
      authorities.add(new SimpleGrantedAuthority(role.name()));

      Set<SimpleGrantedAuthority> permissions = role.getPermissions().stream().map(item->
     new SimpleGrantedAuthority(item.name())).collect(Collectors.toSet());
      authorities.addAll(permissions);
        return authorities;



    }

    @Override
    public String getUsername() {
        return this.email;
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
