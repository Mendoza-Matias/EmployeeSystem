package com.mmendoza.employee_system.bussines.services.impl;

import com.mmendoza.employee_system.bussines.mappers.impl.EmployeeMapperImpl;
import com.mmendoza.employee_system.bussines.services.IEmployeeService;
import com.mmendoza.employee_system.bussines.validation.EmployeeValidation;
import com.mmendoza.employee_system.bussines.validation.models.EmployeeValidateModel;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeDto;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeSaveDto;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeUpdateDto;
import com.mmendoza.employee_system.domain.entity.Employee;
import com.mmendoza.employee_system.domain.enums.Contract;
import com.mmendoza.employee_system.domain.exception.EmployeeException;
import com.mmendoza.employee_system.persistence.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapperImpl employeeMapper;

    @Autowired
    private EmployeeValidation employeeValidation;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDTOAList(employeeRepository.findAll());
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByContract(String request) {

        employeeValidation.validateNameType(request);

        List<EmployeeDto> response;

        if (request.equals("CONTRATADO")) {
            response = employeeMapper.toDTOAList(employeeRepository.getAllByContract(Contract.HIRED));
        } else if (request.equals("EFECTIVO")) {
            response = employeeMapper.toDTOAList(employeeRepository.getAllByContract(Contract.EFFECTIVE));
        } else {
            response = employeeMapper.toDTOAList(employeeRepository.findAll());
        }
        return response;
    }

    @Override
    public EmployeeDto findByDni(Integer dni) {
        employeeValidation.validateDni(dni);
        return employeeMapper.toDTO(employeeRepository.findByDni(dni).orElseThrow(() -> new EmployeeException("")));
    }

    @Override
    public void saveEmployee(EmployeeSaveDto employee) {
        //validation of fields
        employeeValidation.validateFields(
                EmployeeValidateModel.
                        builder()
                        .name(employee.getName())
                        .lastName(employee.getLastName())
                        .build()
        );

        //validation of dni
        employeeValidation.validateDni(employee.getDni());
        this.employeeExist(employee.getDni());

        Contract contract = this.getContractEmployee(employee.isHired());

        BigDecimal salary = this.getSalaryEmployee(employee.isHired());

        Employee newEmployee = Employee.builder()
                .name(employee.getName())
                .lastName(employee.getLastName())
                .dni(employee.getDni())
                .salary(salary)
                .contract(contract)
                .build();

        employeeRepository.save(newEmployee);
    }

    @Override
    public void updateEmployee(EmployeeUpdateDto employee) {

        /* validate dni */
        employeeValidation.validateDni(employee.getDni());

        /*validate of fields*/
        employeeValidation.validateFields(
                EmployeeValidateModel.builder()
                        .name(employee.getName())
                        .lastName(employee.getLastName())
                        .build());

        /*get employee exist*/
        Employee employeeData = employeeRepository.findByDni(employee.getDni()).orElseThrow(() -> new EmployeeException(""));

        deleteEmployee(employee.getDni()); /*delete employee exist*/

        Employee updateEmployee = Employee
                .builder()
                .dni(employeeData.getDni())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .contract(employeeData.getContract())
                .salary(employeeData.getSalary())
                .build();

        employeeRepository.save(updateEmployee);
    }

    @Override
    public void deleteEmployee(Integer dni) {
        employeeValidation.validateDni(dni);
        employeeRepository.deleteById(dni);
    }

    @Override
    public boolean employeeExist(Integer dni) {
        return employeeRepository.existsByDni(dni);
    }

    @Override
    public Contract getContractEmployee(boolean contract) {
        return contract ? Contract.HIRED : Contract.EFFECTIVE;
    }

    @Override
    public BigDecimal getSalaryEmployee(boolean type) {
        return type ? new BigDecimal("500") : new BigDecimal("200");
    }

}
