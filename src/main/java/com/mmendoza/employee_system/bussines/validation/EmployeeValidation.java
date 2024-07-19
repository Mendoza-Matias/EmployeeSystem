package com.mmendoza.employee_system.bussines.validation;

import com.mmendoza.employee_system.bussines.validation.models.EmployeeValidateModel;
import com.mmendoza.employee_system.domain.exception.EmployeeException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidation {
    //Field validations

    public void validateFields(EmployeeValidateModel employee) {

        if (employee == null) {
            throw new EmployeeException("The employee is null");
        }
        if (employee.getName().isEmpty()) {
            throw new EmployeeException("The employee name is empty");
        }
        if (employee.getLastName().isEmpty()) {
            throw new EmployeeException("The employee last name is empty");
        }
    }

    public void validateDni(Integer dni) {
        if (dni == null) {
            throw new EmployeeException("The dni is null");
        }
        if (dni.toString().contains(".")) {
            throw new EmployeeException("The dni contains .");
        }
        if(dni.toString().length() < 8){
            throw new EmployeeException("The dni must contain 8 digits");
        }
    }

    public void validateNameType(String type) {
        if (type == null || type.isEmpty()) {
            throw new EmployeeException("The type is empty");
        }
    }
}
