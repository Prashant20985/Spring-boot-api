package com.example.Springboot.tutorial.service;

import com.example.Springboot.tutorial.entity.Department;
import com.example.Springboot.tutorial.repository.IDepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IDepartmentServiceTest {

    @Autowired
    private IDepartmentService departmentService;
    @MockBean
    private IDepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("warsaw")
                .departmentCode("IT-05")
                .departmentId(1L)
                .build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
        .thenReturn(department);
    }

//    @Test
//    void saveDepartment() {
//    }
//
//    @Test
//    void fetchDepartment() {
//    }
//
//    @Test
//    void fetchDepartmentByID() {
//    }
//
//    @Test
//    void deleteDepartmentByID() {
//    }
//
//    @Test
//    void updateDepartment() {
//    }

    @Test
    @DisplayName("Get data based on department name")
    public void fetchDepartmentByName() {
        String departmentName = "IT";
        Department found =
                departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }
}