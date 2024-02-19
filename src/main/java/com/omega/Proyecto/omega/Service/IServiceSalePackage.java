package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.SalePackage;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceSalePackage {
    public void createPackage(List<TouristicServ> services);
    public void deletePackage(Long id);
    public SalePackage getPackage(Long id);
    public List<SalePackage> getAllPackage();
    public void editSalePackage(List<TouristicServ> services, Long idPackage);

}
