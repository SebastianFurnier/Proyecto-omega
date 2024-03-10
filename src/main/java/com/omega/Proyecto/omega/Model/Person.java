package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String username;
    private String dni;
    private LocalDate birthDay;
    private String nationality;
    private String phoneNumber;
    private String email;
}
