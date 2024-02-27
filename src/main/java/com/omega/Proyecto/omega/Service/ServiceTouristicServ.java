package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTouristicServ implements IServiceTouristicServ
{
    @Autowired
    private IRepositoryTouristicServ repositoryTouristicServ;

    @Override
    public TouristicServ createService(TouristicServ touristicServ) {
        return repositoryTouristicServ.save(touristicServ);
    }

    @Override
    public boolean deleteService(Long id) throws ObjectNotFoundException {
        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        optionalTouristicServ.orElseThrow(() -> new ObjectNotFoundException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error"
        )));

        TouristicServ touristicServAux = optionalTouristicServ.get();
        touristicServAux.setActive(false);
        this.createService(touristicServAux);

        return true;
    }

    @Override
    public TouristicServ getService(Long id) throws ObjectNotFoundException {

        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        return optionalTouristicServ.orElseThrow(() -> new ObjectNotFoundException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error"
        )));
    }

    @Override
    public List<TouristicServ> getAllActiveServices() {
        return repositoryTouristicServ.getActiveTouristicServices();
    }

    @Override
    public TouristicServ activateService(Long id) throws ObjectNotFoundException{
        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        optionalTouristicServ.orElseThrow(() -> new ObjectNotFoundException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error"
        )));
        TouristicServ touristicServAux = optionalTouristicServ.get();
        touristicServAux.setActive(true);
        this.createService(touristicServAux);

        return touristicServAux;
    }

    @Override
    public TouristicServ editService(TouristicServ touristicServ){
        return this.createService(touristicServ);
    }
}
