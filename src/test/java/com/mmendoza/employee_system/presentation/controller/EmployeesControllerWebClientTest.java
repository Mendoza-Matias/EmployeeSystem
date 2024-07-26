package com.mmendoza.employee_system.presentation.controller;

import com.mmendoza.employee_system.domain.dto.employee.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) /*define random port*/
class EmployeesControllerWebClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllEmployees() {
        webTestClient.get().uri("http://localhost:8081/api/employees") /*performs the query to the endpoint*/
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].dni").isEqualTo(123)
        ;
    }
}