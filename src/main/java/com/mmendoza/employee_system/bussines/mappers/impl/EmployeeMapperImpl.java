package com.mmendoza.employee_system.bussines.mappers.impl;

import com.mmendoza.employee_system.bussines.mappers.IGenericMapper;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeDto;
import com.mmendoza.employee_system.domain.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper implements IGenericMapper<Employee, EmployeeResponse> {
    @Override
    public EmployeeResponse toDto(Employee employee) {
        return EmployeeResponse.builder()
                .name(employee.getName())
                .lastName(employee.getLastName())
                .description(employee.getType().getDescription())
                .build();
    }

    @Override
    public List<EmployeeResponse> toDtoList(List<Employee> employee) {
        return employee.stream().map(e ->
                EmployeeResponse.
                        builder()
                        .name(e.getName())
                        .lastName(e.getLastName())
                        .description(e.getType().getDescription())
                        .build()
        ).collect(Collectors.toList());
    }

}