package com.mmendoza.employee_system.domain.dto.employee;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveEmployeeRequest {

    private String dni;

    private String name;

    private String lastName;

    private boolean isHired;
}
