package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTouristicServ implements IServiceTouristicServ {
    @Autowired
    private IRepositoryTouristicServ repositoryServicio;
    @Override
    public void createService(TouristicServ touristicServ) {
        repositoryServicio.save(touristicServ);
    }

    @Override
    public void deleteService(Long id) {
        repositoryServicio.deleteById(id);
    }

    @Override
    public TouristicServ getService(Long id) {
        return repositoryServicio.findById(id).orElse(null);
    }

    @Override
    public List<TouristicServ> getAllServices() {
        return repositoryServicio.findAll();
    }

    @Override
    public void edit(TouristicServ touristicServ){
        this.createService(touristicServ);
    }
}
