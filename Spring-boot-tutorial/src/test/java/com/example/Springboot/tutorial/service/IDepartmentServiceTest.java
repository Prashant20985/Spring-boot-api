package com.example.Springboot.tutorial.service;

import com.example.Springboot.tutorial.entity.Department;
import com.example.Springboot.tutorial.error.DepartmentNotFoundException;
import com.example.Springboot.tutorial.repository.IDepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    @DisplayName("Update Department")
    public void updateDepartment() {
        Department department = departmentService.fetchDepartmentByName("IT");
        department.setDepartmentAddress("Mumbai");
        departmentRepository.save(department);
        Department updateDept = departmentService.fetchDepartmentByName("IT");
        Assertions.assertThat(updateDept.getDepartmentAddress()).isEqualTo("Mumbai");
    }

    @Test
    @DisplayName("Get data based on department name")
    public void fetchDepartmentByName() {
        String departmentName = "IT";
        Department found =
                departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }
}