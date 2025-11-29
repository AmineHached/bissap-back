package com.bc.bissapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bc.bissapp.entities.SubDepartment;

@Repository
public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Long> {

    @Query("SELECT s FROM SubDepartment s WHERE s.department.name = :departmentName")
    List<SubDepartment> findSubDepartmentByDepartmentName(@Param("departmentName") String departmentName);

}
