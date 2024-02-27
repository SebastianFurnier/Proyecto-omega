package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TouristicServ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTouristicService;
    private String name;
    private String description;
    private String destination;
    private LocalDate serviceDate;
    private float cost;
    private boolean active;
}
