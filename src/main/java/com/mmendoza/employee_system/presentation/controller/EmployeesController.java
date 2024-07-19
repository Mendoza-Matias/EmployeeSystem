package com.mmendoza.employee_system.presentation.controller;

import com.mmendoza.employee_system.bussines.services.IEmployeeService;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeDto;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeSaveDto;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeesController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesByContract(@RequestParam String type){
        return ResponseEntity.ok(employeeService.getAllEmployeesByContract(type));
    }

    @GetMapping("dni")
    public ResponseEntity<EmployeeDto> findEmployeeByDni(@RequestParam Integer dni) {
        return ResponseEntity.ok(employeeService.findByDni(dni));
    }

    @PostMapping("create")
    public ResponseEntity<HttpStatus> createEmployee(@RequestBody EmployeeSaveDto employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<HttpStatus> updateEmployee(@RequestBody EmployeeUpdateDto employee) {
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Integer dni) {
        employeeService.deleteEmployee(dni);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
