package com.bc.bissapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bc.bissapp.entities.SubDepartment;
import com.bc.bissapp.iservices.ISubDepartmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/subDepartments")
@AllArgsConstructor
public class SubDepartmentController {

    private final ISubDepartmentService iSubDepartmentService;

    @PostMapping("/create")
    public ResponseEntity<SubDepartment> createSubDepartment(@Valid @RequestBody SubDepartment subDepartment) {
        SubDepartment created = iSubDepartmentService.createSubDepartment(subDepartment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping({"/", "/all"})
    public ResponseEntity<List<SubDepartment>> getAllSubDepartments() {
        List<SubDepartment> subDepartments = iSubDepartmentService.getSubDepartments();
        return ResponseEntity.status(HttpStatus.OK).body(subDepartments);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SubDepartment> getSubDepartmentById(@PathVariable("id") Long id) {
        SubDepartment subDepartment = iSubDepartmentService.getSubDepartmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(subDepartment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubDepartment> updateSubDepartment(@Valid @RequestBody SubDepartment subDepartment, @PathVariable("id") Long id) {
        SubDepartment updated = iSubDepartmentService.updateSubDepartmentById(id, subDepartment);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubDepartment(@PathVariable("id") Long id) {
        iSubDepartmentService.deleteSubDepartmentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/{departmentName}")
    public ResponseEntity<List<SubDepartment>> getSubDepartmentsByDeptName(@PathVariable("departmentName") String departmentName) {
        List<SubDepartment> subDepartments = iSubDepartmentService.findSubDepartmentsByDeptName(departmentName);
        return ResponseEntity.status(HttpStatus.OK).body(subDepartments);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
