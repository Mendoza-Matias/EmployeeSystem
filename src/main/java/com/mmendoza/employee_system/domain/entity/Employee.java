package com.mmendoza.employee_system.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "employee")

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String dni;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "type" )
    private EmployeeType type;

    @Transient
    private boolean isHired;
}
