package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Model.SalePackage;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositorySalePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSalePackage implements IServiceSalePackage {

    @Autowired
    private IRepositorySalePackage repositoryPaquete;
    @Override
    public void createPackage(List<TouristicServ> services) {
        SalePackage packageAux = new SalePackage();
        packageAux.setTouristicServs(services);
        repositoryPaquete.save(packageAux);
    }

    @Override
    public void deletePackage(Long id) {
        repositoryPaquete.deleteById(id);
    }

    @Override
    public SalePackage getPackage(Long id) {
        return repositoryPaquete.findById(id).orElse(null);
    }

    @Override
    public List<SalePackage> getAllPackage() {
        return repositoryPaquete.findAll();
    }

    @Override
    public void editSalePackage(List<TouristicServ> services, Long idPackage) {
        SalePackage packageAux = this.getPackage(idPackage);
        packageAux.setTouristicServs(services);
        repositoryPaquete.save(packageAux);
    }
}
