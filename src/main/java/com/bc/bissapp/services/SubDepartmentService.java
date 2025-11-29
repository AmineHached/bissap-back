package com.bc.bissapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bc.bissapp.entities.Department;
import com.bc.bissapp.entities.SubDepartment;
import com.bc.bissapp.iservices.ISubDepartmentService;
import com.bc.bissapp.repositories.DepartmentRepository;
import com.bc.bissapp.repositories.SubDepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubDepartmentService implements ISubDepartmentService {

    private final SubDepartmentRepository subDepartmentRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public SubDepartment createSubDepartment(SubDepartment toPersistSubDepartment) {
        // Fetch Department by ID before saving SubDepartment
        if (toPersistSubDepartment.getDepartment() != null && toPersistSubDepartment.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(toPersistSubDepartment.getDepartment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            toPersistSubDepartment.setDepartment(dept);
        }
        return subDepartmentRepository.save(toPersistSubDepartment);
    }

    @Override
    public List<SubDepartment> getSubDepartments() {
        return subDepartmentRepository.findAll();
    }

    @Override
    public SubDepartment getSubDepartmentById(Long subDepartmentId) {
        return subDepartmentRepository.findById(subDepartmentId)
                .orElseThrow(() -> new IllegalArgumentException("SubDepartment not found"));
    }

    @Override
    public SubDepartment updateSubDepartmentById(Long Id, SubDepartment toUpdate) {
        SubDepartment subDept = subDepartmentRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("SubDepartment not found"));
        if (subDept != null) {
            subDept.setName(toUpdate.getName());
            if (toUpdate.getDepartment() != null && toUpdate.getDepartment().getId() != null) {
                Department dept = departmentRepository.findById(toUpdate.getDepartment().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Department not found"));
                subDept.setDepartment(dept);
            }
            return subDepartmentRepository.save(subDept);
        }
        return null;
    }

    @Override
    public void deleteSubDepartmentById(Long Id) {
        SubDepartment subDept = subDepartmentRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("SubDepartment not found"));
        subDepartmentRepository.delete(subDept);
    }

    @Override
    public List<SubDepartment> findSubDepartmentsByDeptName(String departmentName) {
        return subDepartmentRepository.findSubDepartmentByDepartmentName(departmentName);
    }
}
