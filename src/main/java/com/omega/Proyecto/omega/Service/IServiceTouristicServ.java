package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServ {
    public TouristicServ createService(TouristicServ touristicServ);
    public boolean deleteService(Long id);
    public TouristicServ getService(Long id);
    public List<TouristicServ> getAllServices();
    public TouristicServ editService(TouristicServ touristicServ);
}
