package com.mmendoza.employee_system.presentation.controller;

import com.mmendoza.employee_system.bussines.services.IEmployeeService;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeResponse;
import com.mmendoza.employee_system.domain.dto.employee.SaveEmployeeRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody @Valid SaveEmployeeRequest request) {
        service.saveEmployee(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateEmployee(@RequestBody @Valid SaveEmployeeRequest request) {
        service.updateEmployee(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{dni}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "dni") String dni) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteEmployee(dni));
    }
}
