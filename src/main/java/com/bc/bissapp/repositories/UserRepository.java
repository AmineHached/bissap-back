package com.bc.bissapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bc.bissapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.subDepartment.name = :subDepartmentName")
    List<User> FindUserBySubDepartmentName(@Param("subDepartmentName") String subDepartmentName);

}
