package com.bc.bissapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bc.bissapp.entities.Department;
import com.bc.bissapp.iservices.IDepartmentService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final IDepartmentService iDepartmentService;

    @PostMapping("/create")
    public Department createDepartment(@RequestBody Department department) {
        return iDepartmentService.createDepartment(department);
    }

    @GetMapping("/all")
    public List<Department> callAll() {
        return iDepartmentService.getAllDepartments();
    }

    @GetMapping("/get/{id}")
    public Department callOne(@PathVariable("id") Long id) {
        return iDepartmentService.getDepartmentById(id);
    }

    @PutMapping("/update/{id}")
    public Department callUpdate(@RequestBody Department department, @PathVariable("id") Long id) {
        return iDepartmentService.updateDepartmentById(id, department);
    }

    @DeleteMapping("/delete/{id}")
    public String callDelete(@PathVariable("id") Long id) {
        return iDepartmentService.deleteDepartmentById(id);
    }
}