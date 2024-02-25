package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private String destiny;
    private Date serviceDate;
    private float cost;
}
