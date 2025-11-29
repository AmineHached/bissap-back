package com.bc.bissapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.bissapp.entities.Department;
import com.bc.bissapp.iservices.IDepartmentService;
import com.bc.bissapp.repositories.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService implements IDepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department toPersistDepartment) {
        return departmentRepository.save(toPersistDepartment);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @Override
    public Department updateDepartmentById(Long Id, Department toUpdate) {
        Department dept = departmentRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Department not found"));
        if (dept != null) {
            dept.setName(toUpdate.getName());
            return departmentRepository.save(dept);
        }
        return null;
    }

    @Override
    public String deleteDepartmentById(Long Id) {
        Department dept = departmentRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Department not found"));
        departmentRepository.delete(dept);
        return "Deleted Successfully";
    }
}
