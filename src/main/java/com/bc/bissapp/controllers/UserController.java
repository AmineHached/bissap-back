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

import com.bc.bissapp.entities.User;
import com.bc.bissapp.iservices.IUserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        //return iUserService.createUser(user);
        User createdUser = iUserService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> callAll() {
        List<User> users = iUserService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> callOne(@PathVariable("id") Long id) {
        User user = iUserService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> callUpdate(@RequestBody User user, @PathVariable("id") Long id) {
        User updatedUser = iUserService.updateUserById(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> callDelete(@PathVariable("id") Long id) {
        iUserService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/{subDepartmentName}")
    public ResponseEntity<List<User>> getUserBySubDepartment(@PathVariable("subDepartmentName") String subDepartmentName) {
        List<User> users = iUserService.findUserBySubDepartment(subDepartmentName);
        return ResponseEntity.status(HttpStatus.OK).body(users);
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
