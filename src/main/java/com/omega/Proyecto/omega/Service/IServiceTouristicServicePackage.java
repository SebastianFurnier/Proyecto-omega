package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServicePackage {
    public TouristicServicesPackage createPackage(List<TouristicServ> services);
    public void deletePackage(Long id);
    public TouristicServicesPackage getPackage(Long id);
    public List<TouristicServicesPackage> getAllPackage();
    public void editSalePackage(List<TouristicServ> services, Long idPackage);

}
