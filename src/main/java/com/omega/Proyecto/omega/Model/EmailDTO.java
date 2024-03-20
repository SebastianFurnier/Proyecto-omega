package com.omega.Proyecto.omega.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private String receiver;
    private String subject;
    private String message;
    private List<String> destinations;
    private String clientName;
    private String sellerName;
    private LocalDate saleDate;
    private float cost;
}
