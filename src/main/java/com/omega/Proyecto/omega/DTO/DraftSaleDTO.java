package com.omega.Proyecto.omega.DTO;

import com.omega.Proyecto.omega.Model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DraftSaleDTO {
    private LocalDate dateSale;
    private PaymentMethod paymentMethod;
    private Long employeeId;
    private Long clientId;
    private Long packId;
}
