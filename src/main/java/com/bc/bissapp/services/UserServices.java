package com.bc.bissapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bc.bissapp.entities.SubDepartment;
import com.bc.bissapp.entities.User;
import com.bc.bissapp.iservices.IUserService;
import com.bc.bissapp.repositories.SubDepartmentRepository;
import com.bc.bissapp.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServices implements IUserService {

    private final UserRepository userRepository;
    private final SubDepartmentRepository subDepartmentRepository;

    @Override
    public User createUser(User toPersist) {
        // Fetch SubDepartment by ID before saving User
        if (toPersist.getSubDepartment() != null && toPersist.getSubDepartment().getId() != null) {
            SubDepartment subDept = subDepartmentRepository.findById(toPersist.getSubDepartment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("SubDepartment not found"));
            toPersist.setSubDepartment(subDept);
        }
        return userRepository.save(toPersist);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUserById(Long Id, User toUpdate) {
        User setUser = userRepository.findById(Id).orElse(null);
        if (setUser != null) {
            setUser.setName(toUpdate.getName());
            setUser.setEmail(toUpdate.getEmail());
            setUser.setAge(toUpdate.getAge());
            setUser.setUserStatus(toUpdate.getUserStatus());
            if (toUpdate.getSubDepartment() != null && toUpdate.getSubDepartment().getId() != null) {
                SubDepartment subDept = subDepartmentRepository.findById(toUpdate.getSubDepartment().getId())
                        .orElseThrow(() -> new IllegalArgumentException("SubDepartment not found"));
                setUser.setSubDepartment(subDept);
            }
            return userRepository.save(setUser);
        }
        return null;
    }

    @Override
    public void deleteUserById(Long Id) {
        userRepository.deleteById(Id);
    }

    @Override
    public List<User> findUserBySubDepartment(String subDepartmentName) {
        return userRepository.FindUserBySubDepartmentName(subDepartmentName);
    }

}
