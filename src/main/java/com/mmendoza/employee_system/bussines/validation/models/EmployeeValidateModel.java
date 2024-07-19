package com.mmendoza.employee_system.bussines.validation.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeValidateModel {

    private String name;

    private String lastName;

}
