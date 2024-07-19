package com.mmendoza.employee_system.persistence.repository;

import com.mmendoza.employee_system.domain.entity.Employee;
import com.mmendoza.employee_system.domain.enums.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByDni(Integer dni);

    Optional<Employee> findByDni(Integer dni);

    List<Employee> getAllByContract(Contract contract);
}
