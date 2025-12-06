package com.bc.bissapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final BlockChainService blockChainService;

    @Override
    public SubDepartment createSubDepartment(SubDepartment toPersistSubDepartment) {
        // Ensure incoming payload cannot force a merge by providing an id
        toPersistSubDepartment.setId(null);
        // Fetch Department by ID before saving SubDepartment
        if (toPersistSubDepartment.getDepartment() != null && toPersistSubDepartment.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(toPersistSubDepartment.getDepartment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            toPersistSubDepartment.setDepartment(dept);
        }
        SubDepartment created = subDepartmentRepository.save(toPersistSubDepartment);
        // Add transaction to blockchain in a separate thread to avoid Hibernate conflicts
        try {
            String blockData = "{ \"action\": \"Create SubDepartment\", \"subDepartmentId\": " + created.getId() + ", \"subDepartmentName\": \"" + created.getName() + "\" }";
            blockChainService.addData(blockData);
        } catch (Exception e) {
            // Log but don't fail the request if blockchain fails
            System.err.println("Blockchain error: " + e.getMessage());
        }
        return created;
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
            SubDepartment updated = subDepartmentRepository.save(subDept);
            try {
                String blockData = "{ \"action\": \"Update SubDepartment\", \"subDepartmentId\": " + updated.getId() + ", \"subDepartmentName\": \"" + updated.getName() + "\" }";
                blockChainService.addData(blockData);
            } catch (Exception e) {
                System.err.println("Blockchain error: " + e.getMessage());
            }
            return updated;
        }
        return null;
    }

    @Override
    public void deleteSubDepartmentById(Long Id) {
        SubDepartment subDept = subDepartmentRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("SubDepartment not found"));
        // Delete users first to handle cascade
        if (subDept.getUsers() != null && !subDept.getUsers().isEmpty()) {
            subDept.getUsers().clear();
        }
        subDepartmentRepository.delete(subDept);
        try {
            String blockData = "{ \"action\": \"Delete SubDepartment\", \"subDepartmentId\": " + subDept.getId() + ", \"subDepartmentName\": \"" + subDept.getName() + "\" }";
            blockChainService.addData(blockData);
        } catch (Exception e) {
            System.err.println("Blockchain error: " + e.getMessage());
        }
    }

    @Override
    public List<SubDepartment> findSubDepartmentsByDeptName(String departmentName) {
        return subDepartmentRepository.findSubDepartmentByDepartmentName(departmentName);
    }
}
