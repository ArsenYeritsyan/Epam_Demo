package com.epam.demo.managerassignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "table")
@Data
public class Table extends AbstractBaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "created_by")
    @JsonIgnoreProperties({"createdTables", "assignedTables", "products"})
    private User createdBy;

    @ManyToOne()
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    private Boolean assigned;

    @OneToMany(mappedBy = "table")
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        if (!super.equals (o)) return false;
        Table table = (Table) o;
        return Objects.equals (createdBy, table.createdBy) && Objects.equals (assignedTo, table.assignedTo) && Objects.equals (assigned, table.assigned) && Objects.equals (orders, table.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), createdBy, assignedTo, assigned, orders);
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

}
