package com.bc.bissapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bc.bissapp.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    

}