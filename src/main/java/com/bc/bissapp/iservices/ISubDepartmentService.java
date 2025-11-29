package com.bc.bissapp.iservices;

import java.util.List;

import com.bc.bissapp.entities.SubDepartment;

public interface ISubDepartmentService {

    SubDepartment createSubDepartment(SubDepartment toPersistSubDepartment);

    List<SubDepartment> getSubDepartments();

    SubDepartment getSubDepartmentById(Long subDepartmentId);

    SubDepartment updateSubDepartmentById(Long Id, SubDepartment toUpdate);

    void deleteSubDepartmentById(Long Id);

    List<SubDepartment> findSubDepartmentsByDeptName(String departmentName);
}
