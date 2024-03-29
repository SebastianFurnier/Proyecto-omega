package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSale;
    private LocalDate dateSale;

    private PaymentMethod paymentMethod;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Client client;
    @OneToOne
    private TouristicServPack touristicServPack;
    private boolean active;
    private float cost;
}
