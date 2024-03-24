package com.omega.Proyecto.omega.Service;

import java.time.LocalDate;

public interface IServiceBonusPoint {
    String getSalesToday(LocalDate today);

    String getMonthlySales();
}
