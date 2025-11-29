package com.bc.bissapp.iservices;

import java.util.List;

import com.bc.bissapp.entities.User;

public interface IUserService {

    User createUser(User toPersistUser);

    List<User> getUsers();

    User getUserById(Long userId);

    User updateUserById(Long Id, User toUpdate);

    void deleteUserById(Long Id);

    List<User> findUserBySubDepartment(String subDepartmentName);

}
