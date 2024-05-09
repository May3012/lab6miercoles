package com.example.pruebalab6.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "technician")
public class Technician {
    @Id
    @Column(name = "TechnicianID", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @Size(max = 100)
    @NotNull
    @Column(name = "LastName", nullable = false, length = 100)
    private String lastName;

    @Size(max = 8)
    @NotNull
    @Column(name = "Dni", nullable = false, length = 8)
    private String dni;

    @Size(max = 9)
    @NotNull
    @Column(name = "Phone", nullable = false, length = 9)
    private String phone;

    @NotNull
    @Column(name = "Age", nullable = false)
    private Integer age;

}