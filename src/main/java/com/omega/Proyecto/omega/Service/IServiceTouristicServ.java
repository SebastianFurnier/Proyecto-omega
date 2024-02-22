package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServ {
    TouristicServ createService(TouristicServ touristicServ);
    boolean deleteService(Long id);
    TouristicServ getService(Long id);
    List<TouristicServ> getAllServices();
    TouristicServ editService(TouristicServ touristicServ);
}
