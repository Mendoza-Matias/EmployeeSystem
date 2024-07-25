package com.mmendoza.employee_system.bussines.services.impl.employee;

import com.mmendoza.employee_system.bussines.mappers.impl.EmployeeMapperImpl;
import com.mmendoza.employee_system.bussines.services.impl.EmployeeServiceImpl;
import com.mmendoza.employee_system.bussines.validation.EmployeeValidation;
import com.mmendoza.employee_system.domain.dto.employee.EmployeeDto;
import com.mmendoza.employee_system.domain.entity.Employee;
import com.mmendoza.employee_system.domain.enums.Contract;
import com.mmendoza.employee_system.persistence.repository.IEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private IEmployeeRepository repository;

    @Mock
    private EmployeeMapperImpl mapper;

    @Mock
    private EmployeeValidation employeeValidation;

    private Employee employee;

    private EmployeeDto employeeDto;

    private List<Employee> employees;

    private List<EmployeeDto> employeeDtos;

    private List<EmployeeDto> effectiveDto;

    private List<EmployeeDto> hiredDto;

    @BeforeEach
    void setUp() {

        employee = Employee.builder()
                .dni(123543321)
                .name("name")
                .lastName("lastName")
                .salary(new BigDecimal("500.000"))
                .contract(Contract.EFFECTIVE)
                .build();

        employeeDto = EmployeeDto.builder()
                .dni(123543321)
                .name("name")
                .lastName("lastName")
                .build();

        employees = Arrays.asList(
                Employee.builder()
                        .dni(123543321)
                        .name("name")
                        .lastName("lastName")
                        .salary(new BigDecimal("500.000"))
                        .contract(Contract.EFFECTIVE)
                        .build(),

                Employee.builder()
                        .dni(345678932)
                        .name("name")
                        .lastName("lastName")
                        .salary(new BigDecimal("600.000"))
                        .contract(Contract.HIRED)
                        .build()
        );

        employeeDtos = Arrays.asList(
                EmployeeDto.builder()
                        .dni(123543321)
                        .name("name")
                        .lastName("lastName")
                        .build(),
                EmployeeDto.builder()
                        .dni(345678932)
                        .name("name")
                        .lastName("lastName")
                        .build()
        );

        effectiveDto = Arrays.asList(
                EmployeeDto.builder()
                        .dni(123543321)
                        .name("name")
                        .lastName("lastName")
                        .build());

        hiredDto = Arrays.asList(
                EmployeeDto.builder()
                        .dni(345678932)
                        .name("name")
                        .lastName("lastName")
                        .build()
        );

    }

    @Nested
    class testService {

        @Test
        @DisplayName("return all employees")
        void getAllEmployees() {

            Mockito.when(repository.findAll()).thenReturn(employees);
            Mockito.when(mapper.toDTOAList(Mockito.anyList())).thenReturn(employeeDtos);

            List<EmployeeDto> response = service.getAllEmployees();

            Mockito.verify(repository).findAll();
            Mockito.verify(mapper).toDTOAList(Mockito.anyList());

            assertEquals(2, response.size());
            assertEquals(123543321, response.get(0).getDni());
            assertEquals("name", response.get(0).getName());
            assertEquals("lastName", response.get(0).getLastName());
        }

        @Test
        @DisplayName("return all employees with contract effective")
        void getAllEmployeesByContractIsEffective() {

            ArgumentCaptor<Contract> captor = ArgumentCaptor.forClass(Contract.class);
            Mockito.when(repository.getAllByContract(captor.capture())).thenReturn(employees);
            Mockito.when(mapper.toDTOAList(Mockito.anyList())).thenReturn(effectiveDto);

            List<EmployeeDto> response = service.getAllEmployeesByContract("EFECTIVO");

            Mockito.verify(repository).getAllByContract(Mockito.any(Contract.class));

            assertEquals("EFFECTIVE", captor.getValue().name());
            assertEquals(123543321, response.get(0).getDni());
            assertEquals("name", response.get(0).getName());
            assertEquals("lastName", response.get(0).getLastName());
        }

        @Test
        @DisplayName("return all employees with contract hired")
        void getAllEmployeesByContractIsHired() {

            ArgumentCaptor<Contract> captor = ArgumentCaptor.forClass(Contract.class);
            Mockito.when(repository.getAllByContract(captor.capture())).thenReturn(employees);
            Mockito.when(mapper.toDTOAList(Mockito.anyList())).thenReturn(hiredDto);

            List<EmployeeDto> response = service.getAllEmployeesByContract("CONTRATADO");

            Mockito.verify(repository).getAllByContract(Mockito.any(Contract.class));
            assertEquals("HIRED", captor.getValue().name());
            assertEquals(345678932, response.get(0).getDni());
            assertEquals("name", response.get(0).getName());
            assertEquals("lastName", response.get(0).getLastName());
        }

        @Test
        @DisplayName("return all employees with contract different")
        void getAllEmployeesByContractDifferent() {
            Mockito.when(mapper.toDTOAList(Mockito.anyList())).thenReturn(employeeDtos);
            List<EmployeeDto> response = service.getAllEmployeesByContract("DESPEDIDO");
            assertEquals(2, response.size());
        }

        @Test
        @DisplayName("returns an employee for dni")
        void findByDni() {
            ArgumentCaptor <Integer> captor = ArgumentCaptor.forClass(Integer.class);

            Mockito.when(repository.findByDni(captor.capture())).thenReturn(Optional.of(employee));

            Mockito.when(mapper.toDTO(Mockito.any(Employee.class))).thenReturn(employeeDto);

            EmployeeDto response = service.findByDni(123543321);

            Mockito.verify(repository).findByDni(Mockito.anyInt());

            assertEquals(123543321,captor.getValue());
            assertEquals("name", response.getName());
            assertEquals("lastName", response.getLastName());
        }

        @Test
        @DisplayName("save new employee")
        void saveEmployee() {
        }

        @Test
        @DisplayName("delete , modify and save new data of employee")
        void updateEmployee() {
        }

        @Test
        @DisplayName("delete employee")
        void deleteEmployee() {

            Mockito.doNothing().when(repository).deleteById(123543321);

            service.deleteEmployee(123543321);

            Mockito.verify(repository).deleteById(Mockito.anyInt());
        }

        @Test
        @DisplayName("check if an employee exists")
        void employeeExist() {

            Mockito.when(repository.existsByDni((123543321))).thenReturn(true);

            boolean response = service.employeeExist((123543321));

            Mockito.verify(repository).existsByDni(123543321);

            assertTrue(response);
        }

        @Test
        @DisplayName("get contract an employee")
        void getContractEmployee() {
        }

        @Test
        @DisplayName("get salary an employee")
        void getSalaryEmployee() {
        }
    }

    @Nested
    class testServiceErrors {

    }
}