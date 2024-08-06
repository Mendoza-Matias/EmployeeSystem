package com.mmendoza.employee_system.bussines.services.impl;

import com.mmendoza.employee_system.domain.entity.EmployeeType;
import com.mmendoza.employee_system.persistence.repository.IEmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl {

    @Autowired
    private IEmployeeTypeRepository typeRepository;

    public EmployeeType getType(boolean condition) {
        return condition ? typeRepository.findByType("H") : typeRepository.findByType("S");
    }
}
