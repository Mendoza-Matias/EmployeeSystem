package com.mmendoza.employee_system.persistence.repository;

import com.mmendoza.employee_system.domain.entity.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeTypeRepository extends JpaRepository<EmployeeType, String> {

    EmployeeType findByType(String type);

}
