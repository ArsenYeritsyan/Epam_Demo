package com.epam.demo.managerassignment.model;

import com.epam.demo.managerassignment.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Role extends AbstractBaseEntity {
    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "`role_name`", length = 7, nullable = false)
    ApplicationUserRole roleName;

    @ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    List<User> users;

    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        if (!super.equals (o)) return false;
        Role role = (Role) o;
        return roleName == role.roleName && Objects.equals (users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), roleName, users);
    }
}
