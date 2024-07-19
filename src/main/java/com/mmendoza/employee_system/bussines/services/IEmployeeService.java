package com.mmendoza.employee_system.bussines.services;

import com.mmendoza.employee_system.domain.dto.employee.EmployeeDto;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeSaveDto;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeUpdateDto;
import com.mmendoza.employee_system.domain.entity.Employee;
import com.mmendoza.employee_system.domain.enums.Contract;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> getAllEmployees();

    List <EmployeeDto> getAllEmployeesByContract (String request);

    EmployeeDto findByDni(Integer dni);

    void saveEmployee(EmployeeSaveDto employee);

    void updateEmployee(EmployeeUpdateDto employee);

    void deleteEmployee(Integer dni);

    boolean employeeExist(Integer dni);

    Contract getContractEmployee(boolean contract);

    BigDecimal getSalaryEmployee(boolean type);
}
