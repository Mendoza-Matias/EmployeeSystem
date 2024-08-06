package com.mmendoza.employee_system.bussines.services.impl;

import com.mmendoza.employee_system.bussines.services.mappers.impl.EmployeeMapper;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeResponse;
import com.mmendoza.employee_system.domain.dto.employee.SaveEmployeeRequest;
import com.mmendoza.employee_system.domain.entity.Employee;
import com.mmendoza.employee_system.domain.entity.EmployeeType;
import com.mmendoza.employee_system.domain.exception.EmployeeException;
import com.mmendoza.employee_system.persistence.repository.IEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private IEmployeeRepository repository;

    @Mock
    private EmployeeMapper mapper;

    @Mock
    private TypeServiceImpl typeService;

    private List<Employee> employees;

    private List<EmployeeResponse> employeeResponses;

    @BeforeEach
    void setUp() {
        employees = Arrays.asList(
                new Employee().builder()
                        .dni("12345678")
                        .name("name")
                        .lastName("lastName")
                        .type(new EmployeeType().builder()
                                .type("E")
                                .description("effective")
                                .salary(new BigDecimal("200.000"))
                                .build())
                        .build());

        employeeResponses = Arrays.asList(
                EmployeeResponse.builder()
                        .name("name")
                        .lastName("lastName")
                        .description("effective")
                        .build()
        );
    }

    @Nested
    class methods {
        @Test
        void getAllEmployees() {
            Mockito.when(repository.findAllWithTypes()).thenReturn(employees);
            Mockito.when(mapper.toDtoList(Mockito.anyList())).thenReturn(employeeResponses);

            List<EmployeeResponse> actual = service.getAllEmployees();

            assertEquals(1, actual.size());
            assertEquals("name", actual.get(0).getName());
            assertEquals("lastName", actual.get(0).getLastName());
            assertEquals("effective", actual.get(0).getDescription());
        }

        @Test
        void saveEmployee() {
            service.saveEmployee(SaveEmployeeRequest.builder()
                    .dni("12345678")
                    .name("name")
                    .lastName("lastName")
                    .isHired(false)
                    .build());
            Mockito.verify(repository).save(Mockito.any());
        }

        @Test
        void updateEmployee() {
        }

        @Test
        void deleteEmployee() {
        }
    }

    @Nested
    class error {
        @Test
        void verifyDniNull() {
            EmployeeException exception = assertThrows(EmployeeException.class, () ->
                    service.saveEmployee(SaveEmployeeRequest.builder()
                            .dni(null)
                            .name("name")
                            .lastName("lastName")
                            .isHired(true)
                            .build()));
            assertEquals("the employee dni is required", exception.getMessage());
        }

        @Test
        void verifyDniEmpty() {
            EmployeeException exception = assertThrows(EmployeeException.class, () ->
                    service.saveEmployee(SaveEmployeeRequest.builder()
                            .dni("")
                            .name("name")
                            .lastName("lastName")
                            .isHired(true)
                            .build()));
            assertEquals("the dni format is incorrect", exception.getMessage());
        }

        @Test
        void verifyDniContainsPoint() {
            EmployeeException exception = assertThrows(EmployeeException.class, () ->
                    service.saveEmployee(SaveEmployeeRequest.builder()
                            .dni("44.547.239")
                            .name("name")
                            .lastName("lastName")
                            .isHired(true)
                            .build()));
            assertEquals("the dni format is incorrect", exception.getMessage());
        }

        @Test
        void verifyDniLength() {
            EmployeeException exception = assertThrows(EmployeeException.class, () ->
                    service.saveEmployee(SaveEmployeeRequest.builder()
                            .dni("44")
                            .name("name")
                            .lastName("lastName")
                            .isHired(true)
                            .build()));
            assertEquals("the length of dni is incorrect", exception.getMessage());
        }

        @Test
        void verifyNameNull() {
            EmployeeException exception = assertThrows(EmployeeException.class, () -> service.saveEmployee(SaveEmployeeRequest.builder()
                    .dni("12345678")
                    .name(null)
                    .lastName("lastName")
                    .isHired(true)
                    .build()));
            assertEquals("the employee name is required", exception.getMessage());
        }

        @Test
        void verifyNameEmpty() {
            EmployeeException exception = assertThrows(EmployeeException.class, () -> service.saveEmployee(SaveEmployeeRequest.builder()
                    .dni("12345678")
                    .name("")
                    .lastName("lastName")
                    .isHired(true)
                    .build()));
            assertEquals("the employee name not is empty", exception.getMessage());
        }

        @Test
        void verifyLastNameNull() {
            EmployeeException exception = assertThrows(EmployeeException.class, () -> service.saveEmployee(SaveEmployeeRequest.builder()
                    .dni("12345678")
                    .name("name")
                    .lastName(null)
                    .isHired(true)
                    .build()));
            assertEquals("the employee lastName is required", exception.getMessage());
        }

        @Test
        void verifyLastNameEmpty() {
            EmployeeException exception = assertThrows(EmployeeException.class, () -> service.saveEmployee(SaveEmployeeRequest.builder()
                    .dni("12345678")
                    .name("name")
                    .lastName("")
                    .isHired(true)
                    .build()));
            assertEquals("the employee lastName not is empty", exception.getMessage());
        }

        @Test
        void verifyNameAndLastNameLength() {
            EmployeeException exception = assertThrows(EmployeeException.class, () -> service.saveEmployee(SaveEmployeeRequest.builder()
                    .dni("12345678")
                    .name("m")
                    .lastName("m")
                    .isHired(true)
                    .build()));
            assertEquals("the employee name length should be 4", exception.getMessage());
        }

    }

    @Nested
    class arguments {

    }
}