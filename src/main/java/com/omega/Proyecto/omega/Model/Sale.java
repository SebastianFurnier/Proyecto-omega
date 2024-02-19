package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVenta;
    private Date DateSale;
    private PaymentMethod paymentMethod;
    //@OneToOne
    //private Persona empleado;
    //@OneToOne
    // Private Persona cliente;
    @OneToOne
    private SalePackage aSalePackage;
}
