package com.mmendoza.employee_system.domain.dto.employee;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeUpdateDto {

    private Integer dni;

    private String name;

    private String lastName;

}
