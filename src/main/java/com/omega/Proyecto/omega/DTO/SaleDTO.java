package com.omega.Proyecto.omega.DTO;


import com.omega.Proyecto.omega.Model.PaymentMethod;
import com.omega.Proyecto.omega.Model.Sale;
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
    private Long idPack;
    private boolean active;
    private float cost;

    public SaleDTO(Sale sale) {
        this.idSale = sale.getIdSale();
        this.dateSale = sale.getDateSale();
        this.paymentMethod = sale.getPaymentMethod();
        this.employee = new EmployeeDTO(sale.getEmployee());
        this.client = new PersonDTO(sale.getClient());
        this.idPack = sale.getTouristicServPack().getIdPack();
        this.active = sale.isActive();
        this.cost = sale.getCost();
    }
}
