package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.ServicePackage;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceSalePackage {
    public void createPackage(List<TouristicServ> services);
    public void deletePackage(Long id);
    public ServicePackage getPackage(Long id);
    public List<ServicePackage> getAllPackage();
    public void editSalePackage(List<TouristicServ> services, Long idPackage);

}
