package com.mmendoza.employee_system.domain.dto.employee;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeDto {

    private String name;

    private Integer dni;

    private String lastName;

}
