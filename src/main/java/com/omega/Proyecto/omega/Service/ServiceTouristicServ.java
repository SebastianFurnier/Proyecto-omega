package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTouristicServ implements IServiceTouristicServ {
    @Autowired
    private IRepositoryTouristicServ repositoryTouristicServ;
    @Override
    public TouristicServ createService(TouristicServ touristicServ) {
        return repositoryTouristicServ.save(touristicServ);
    }

    @Override
    public boolean deleteService(Long id) {
        repositoryTouristicServ.deleteById(id);
        return true;
    }

    @Override
    public TouristicServ getService(Long id) {
        return repositoryTouristicServ.findById(id).orElse(null);
    }

    @Override
    public List<TouristicServ> getAllServices() {
        return repositoryTouristicServ.findAll();
    }

    @Override
    public TouristicServ editService(TouristicServ touristicServ){
        return this.createService(touristicServ);
    }
}
