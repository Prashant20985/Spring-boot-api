package com.example.Springboot.tutorial.controller;

import com.example.Springboot.tutorial.entity.Department;
import com.example.Springboot.tutorial.error.DepartmentNotFoundException;
import com.example.Springboot.tutorial.service.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentCode("CE-05")
                .departmentName("CE")
                .departmentAddress("warsaw")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentCode("CE-05")
                .departmentName("CE")
                .departmentAddress("warsaw")
                .departmentId(1L)
                .build();

        Mockito.when(departmentService.saveDepartment(department))
                .thenReturn(inputDepartment);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\": \"CE\",\n" +
                        "\t\"departmentAddress\": \"warsaw\",\n" +
                        "\t\"departmentCode\": \"CE-05\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentByID() throws Exception {
        Mockito.when(departmentService.fetchDepartmentByID(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.departmentName")
        .value(department.getDepartmentName()));
    }
}