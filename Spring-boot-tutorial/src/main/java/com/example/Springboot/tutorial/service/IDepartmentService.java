package com.example.Springboot.tutorial.service;

import com.example.Springboot.tutorial.entity.Department;
import com.example.Springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface IDepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> fetchDepartment();

    public Department fetchDepartmentByID(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentByID(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    public Department fetchDepartmentByName(String name);
}
