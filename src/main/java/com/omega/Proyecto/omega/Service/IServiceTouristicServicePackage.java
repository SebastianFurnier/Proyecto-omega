package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServicePackage {
    TouristicServicesPackage createPackage(List<TouristicServ> services);
    void deletePackage(Long id);
    TouristicServicesPackage getPackage(Long id);
    List<TouristicServicesPackage> getAllPackage();
    void editSalePackage(List<TouristicServ> services, Long idPackage);

}
