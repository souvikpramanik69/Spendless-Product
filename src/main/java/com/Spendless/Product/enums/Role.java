package com.Spendless.Product.enums;

import java.util.Set;

public enum Role {

    ADMIN(Set.of(Permissions.READ,Permissions.WRITE,Permissions.DELETE)),
    USER(Set.of(Permissions.READ));

    Set<Permissions> permissions;

     Role(Set<Permissions> permissions){
        this.permissions = permissions;
    }

}
