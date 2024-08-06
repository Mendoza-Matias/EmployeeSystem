package com.mmendoza.employee_system.persistence.repository;

import com.mmendoza.employee_system.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {

    /*performs a JOIN between the Employee entity and the EmployeeType entity*/
    @Query("SELECT e FROM Employee e JOIN FETCH e.type")
    List<Employee> findAllWithTypes();

    void deleteByDni(String dni);

    boolean existsByDni(String dni);

    Optional<Employee> findByDni(String dni);

}
