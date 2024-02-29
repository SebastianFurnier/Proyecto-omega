package com.omega.Proyecto.omega.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Employee extends Person{
    private String position;
    private Long salary;
    private boolean flag;
}
