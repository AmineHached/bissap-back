package com.bc.bissapp.iservices;

import java.util.List;

import com.bc.bissapp.entities.Department;

public interface IDepartmentService {
    
    Department createDepartment(Department toPersistDepartment);

    List<Department> getAllDepartments();

    Department getDepartmentById(Long departmentId);

    Department updateDepartmentById(Long Id, Department toUpdate);
    
    String deleteDepartmentById(Long Id);

}