package com.omega.Proyecto.omega.Service;

import java.time.LocalDate;
import java.util.Date;

public interface IServiceBonusPoint {
    String getSalesToday(LocalDate today);

    String getMonthlySales();
}
