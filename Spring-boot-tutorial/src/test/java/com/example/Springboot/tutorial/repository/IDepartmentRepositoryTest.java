package com.example.Springboot.tutorial.repository;

import com.example.Springboot.tutorial.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class IDepartmentRepositoryTest {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Mechanical")
                .departmentAddress("Delhi")
                .departmentCode("ME-20")
                .build();

        entityManager.persist(department);
    }

    @Test
    public void findById(){
        Department department = departmentRepository.findById(1L).get();

        Assertions.assertEquals(department.getDepartmentName(),"Mechanical");
    }
}