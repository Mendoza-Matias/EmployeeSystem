package com.mmendoza.employee_system.bussines.mappers.impl;

import com.mmendoza.employee_system.bussines.mappers.IGenericMapper;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeDto;
import com.mmendoza.employee_system.domain.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapperImpl implements IGenericMapper<Employee, EmployeeDto> {

    @Override
    public EmployeeDto toDTO(Employee ent) {
        return EmployeeDto
                .builder()
                .name(ent.getName())
                .lastName(ent.getLastName())
                .build();
    }

    @Override
    public List<EmployeeDto> toDTOAList(List<Employee> ent) {
        return ent.stream().map(e ->
                EmployeeDto.builder()
                        .name(e.getName())
                        .lastName(e.getLastName())
                        .dni(e.getDni())
                        .build()).collect(Collectors.toList());
    }
}