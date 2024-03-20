package com.omega.Proyecto.omega.DTO;


import com.omega.Proyecto.omega.Model.PaymentMethod;
import com.omega.Proyecto.omega.Model.TouristicServPack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Long idSale;
    private LocalDate dateSale;
    private PaymentMethod paymentMethod;
    private EmployeeDTO employee;
    private PersonDTO client;
    private TouristicServPack touristicServPack;
    private boolean active;
    private float cost;
}
