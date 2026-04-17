package com.Spendless.Product.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
public enum Role {

    ROLE_ADMIN(Set.of(Permissions.READ,Permissions.WRITE,Permissions.DELETE)),
    ROLE_USER(Set.of(Permissions.READ));

    Set<Permissions> permissions;

     Role(Set<Permissions> permissions){
        this.permissions = permissions;
    }




}
