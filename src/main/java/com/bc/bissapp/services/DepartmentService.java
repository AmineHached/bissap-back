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
    
    @Autowired
    private final BlockChainService blockChainService;

    @Override
    public Department createDepartment(Department toPersistDepartment) {
        // Ensure incoming payload cannot force a merge/update by providing an id (or 0)
        toPersistDepartment.setId(null);
        Department created = departmentRepository.save(toPersistDepartment);
        
        // Add transaction to blockchain when department is created
        String blockData = "{ \"action\": \"Create Department\", \"departmentId\": " + created.getId() + ", \"departmentName\": \"" + created.getName() + "\" }";
        blockChainService.addData(blockData);
        
        return created;
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
            Department updated = departmentRepository.save(dept);
            String blockData = "{ \"action\": \"Update Department\", \"departmentId\": " + updated.getId() + ", \"departmentName\": \"" + updated.getName() + "\" }";
            blockChainService.addData(blockData);
            return updated;
        }
        return null;
    }

    @Override
    public String deleteDepartmentById(Long Id) {
        Department dept = departmentRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Department not found"));
        // Delete subdepartments first to handle cascade
        if (dept.getSubDepartments() != null && !dept.getSubDepartments().isEmpty()) {
            dept.getSubDepartments().clear();
        }
        String blockData = "{ \"action\": \"Delete Department\", \"departmentId\": " + dept.getId() + ", \"departmentName\": \"" + dept.getName() + "\" }";
        blockChainService.addData(blockData);
        departmentRepository.delete(dept);
        return "Deleted Successfully";
    }
}
