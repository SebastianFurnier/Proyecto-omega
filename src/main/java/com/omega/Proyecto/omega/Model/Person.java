package com.omega.Proyecto.omega.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String username;
    private String dni;
    private Date birthDay;
    private String nationality;
    private String phoneNumber;
    private String email;
}
