package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTouristicServicePackage implements IServiceTouristicServicePackage {

    @Autowired
    private IRepositoryTouristicServPackage repositoryPackage;
    @Override
    public TouristicServicesPackage createPackage(List<TouristicServ> services) {
        TouristicServicesPackage packageAux = new TouristicServicesPackage();
        packageAux.setTouristicServs(services);
        return repositoryPackage.save(packageAux);
    }

    @Override
    public void deletePackage(Long id) {
        repositoryPackage.deleteById(id);
    }

    @Override
    public TouristicServicesPackage getPackage(Long id) {
        return repositoryPackage.findById(id).orElse(null);
    }

    @Override
    public List<TouristicServicesPackage> getAllPackage() {
        return repositoryPackage.findAll();
    }

    @Override
    public void editSalePackage(List<TouristicServ> services, Long idPackage) {
        TouristicServicesPackage packageAux = this.getPackage(idPackage);
        packageAux.setTouristicServs(services);
        repositoryPackage.save(packageAux);
    }
}
