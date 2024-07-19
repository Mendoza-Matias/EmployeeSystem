package com.mmendoza.employee_system.domain.entity;

import com.mmendoza.employee_system.domain.enums.Contract;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;
import java.math.BigDecimal;

@Entity
@Table(name = "employee")

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private Integer dni;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "contract")
    @Enumerated(EnumType.STRING)
    private Contract contract;

    @Transient
    private boolean isHired; //aux
}
