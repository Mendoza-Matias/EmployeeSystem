package com.mmendoza.employee_system.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "employee_type")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeType {

    @Id
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "salary")
    private BigDecimal salary;

    @OneToMany(mappedBy = "type")
    private List<Employee> employees;
}
