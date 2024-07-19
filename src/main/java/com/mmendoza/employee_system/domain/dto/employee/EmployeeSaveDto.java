package com.mmendoza.employee_system.domain.dto.employee;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeSaveDto {

    private Integer dni;

    private String name;

    private String lastName;

    private boolean isHired;
}
