package com.epam.demo.managerassignment.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    MANAGER(Sets.newHashSet(ApplicationUserPermission.PRODUCT_READ, ApplicationUserPermission.PRODUCT_WRITE, ApplicationUserPermission.USER_READ, ApplicationUserPermission.USER_WRITE, ApplicationUserPermission.TABLE_READ, ApplicationUserPermission.TABLE_WRITE)),
    USER(Sets.newHashSet(ApplicationUserPermission.PRODUCT_READ, ApplicationUserPermission.TABLE_READ, ApplicationUserPermission.USER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}