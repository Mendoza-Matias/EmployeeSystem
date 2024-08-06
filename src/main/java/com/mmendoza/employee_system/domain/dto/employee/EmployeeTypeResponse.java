package com.mmendoza.employee_system.domain.dto.employee;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeTypeResponse {

    private String name;

    private String lastName;

    private String description;
}
