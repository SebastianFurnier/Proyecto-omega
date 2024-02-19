package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServ {
    public void createService(TouristicServ touristicServ);
    public void deleteService(Long id);
    public TouristicServ getService(Long id);
    public List<TouristicServ> getAllServices();
    public void edit(TouristicServ touristicServ);
}
