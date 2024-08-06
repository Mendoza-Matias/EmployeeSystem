package com.mmendoza.employee_system.bussines.services.impl;

import com.mmendoza.employee_system.bussines.services.IEmployeeService;
import com.mmendoza.employee_system.bussines.services.mappers.impl.EmployeeMapper;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeResponse;
import com.mmendoza.employee_system.domain.dto.employee.SaveEmployeeRequest;
import com.mmendoza.employee_system.domain.entity.Employee;
import com.mmendoza.employee_system.domain.entity.EmployeeType;
import com.mmendoza.employee_system.domain.exception.EmployeeException;
import com.mmendoza.employee_system.domain.exception.NotFoundException;
import com.mmendoza.employee_system.persistence.repository.IEmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository repository;

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private EmployeeMapper mapper;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return mapper.toDtoList(repository.findAllWithTypes());
    }

    @Override
    public void saveEmployee(SaveEmployeeRequest request) {
        this.validateRequest(request);
        repository.save(Employee.
                builder()
                .dni(request.getDni())
                .name(request.getName())
                .lastName(request.getLastName())
                .type(typeService.getType(request.isHired()))
                .build());
    }

    @Override
    public void updateEmployee(SaveEmployeeRequest request) {
        Employee employee = repository.findByDni(request.getDni()).orElseThrow(() -> new NotFoundException("employee not found"));
        repository.deleteByDni(request.getDni());
        this.validateField(request.getName(), request.getLastName());
        repository.save(Employee.builder()
                .dni(employee.getDni())
                .name(request.getName())
                .lastName(request.getLastName())
                .type(employee.getType())
                .build());
    }

    @Transactional
    @Override
    public String deleteEmployee(String dni) {
        repository.findByDni(dni).orElseThrow(() -> new NotFoundException("employee not found"));
        repository.deleteByDni(dni);
        return dni.substring(5, 8);
    }

    @Override
    public void validateExistEmployee(String dni) {
        if (repository.existsByDni(dni)) {
            throw new EmployeeException("the employee exist in the system");
        }
    }

    @Override
    public void validateFormatDni(String dni) {
        if (dni == null) {
            throw new EmployeeException("the employee dni is required");
        }
        if (dni.contains(".") || dni.isEmpty()) {
            throw new EmployeeException("the dni format is incorrect");
        }
        if (dni.length() != 8) {
            throw new EmployeeException("the length of dni is incorrect");
        }
    }

    @Override
    public void validateField(String name, String lastName) {
        if (name == null) {
            throw new EmployeeException("the employee name is required");
        }
        if (name.isEmpty()) {
            throw new EmployeeException("the employee name not is empty");
        }
        if (lastName == null) {
            throw new EmployeeException("the employee lastName is required");
        }
        if (lastName.isEmpty()) {
            throw new EmployeeException("the employee lastName not is empty");
        }
        if (!(name.length() >= 4 && lastName.length() >= 4)) {
            throw new EmployeeException("the employee name length should be 4");
        }
    }

    @Override
    public void validateRequest(SaveEmployeeRequest request) {
        this.validateExistEmployee(request.getDni());
        this.validateFormatDni(request.getDni());
        this.validateField(request.getName(), request.getLastName());
    }
}
