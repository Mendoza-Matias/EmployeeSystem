package com.mmendoza.employee_system.bussines.services;

import com.mmendoza.employee_system.domain.dto.employee.EmployeeResponse;
import com.mmendoza.employee_system.domain.dto.employee.SaveEmployeeRequest;
import com.mmendoza.employee_system.domain.entity.EmployeeType;

import java.math.BigDecimal;
import java.util.List;

public interface IEmployeeService {

    List<EmployeeResponse> getAllEmployees();

    void saveEmployee(SaveEmployeeRequest request);

    void updateEmployee(SaveEmployeeRequest request);

    String deleteEmployee(String dni);

    void validateExistEmployee(String dni);

    void validateFormatDni(String dni);

    void validateField(String name, String lastName);

    void validateRequest(SaveEmployeeRequest request);
}
