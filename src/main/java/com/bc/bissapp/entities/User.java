package com.bc.bissapp.entities;

import com.bc.bissapp.entities.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    @Column(nullable = false, length = 50)
    private String name;

    @Min(value = 18, message = "L'âge doit être au moins 18 ans")
    @Max(value = 100, message = "L'âge doit être au plus 100 ans")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", length = 20)
    private UserStatus userStatus;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    @Size(min = 5, max = 100, message = "L'email doit contenir entre 5 et 100 caractères")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @JsonIgnoreProperties("users")
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sub_department_id")
    private SubDepartment subDepartment;

}
