package com.omega.Proyecto.omega.Model;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Client extends Person{
    private boolean flag;
}
