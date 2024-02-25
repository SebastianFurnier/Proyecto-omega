package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.ServicePackage;
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
        ServicePackage packageAux = new ServicePackage();
        packageAux.setTouristicServs(services);
        repositoryPaquete.save(packageAux);
    }

    @Override
    public void deletePackage(Long id) {
        repositoryPaquete.deleteById(id);
    }

    @Override
    public ServicePackage getPackage(Long id) {
        return repositoryPaquete.findById(id).orElse(null);
    }

    @Override
    public List<ServicePackage> getAllPackage() {
        return repositoryPaquete.findAll();
    }

    @Override
    public void editSalePackage(List<TouristicServ> services, Long idPackage) {
        ServicePackage packageAux = this.getPackage(idPackage);
        packageAux.setTouristicServs(services);
        repositoryPaquete.save(packageAux);
    }
}
