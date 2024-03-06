package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public boolean deleteService(Long id) throws ObjectNFException {
        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        optionalTouristicServ.orElseThrow(() -> new ObjectNFException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error",HttpStatus.BAD_REQUEST)));

        TouristicServ touristicServAux = optionalTouristicServ.get();
        touristicServAux.setActive(false);
        this.createService(touristicServAux);

        return true;
    }

    //When I can merge this branch with Omega 24 branch
    // I will modify this method to only search active id's.
    @Override
    public TouristicServ getService(Long id) throws ObjectNFException {
        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        return optionalTouristicServ.orElseThrow(() -> new ObjectNFException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error",HttpStatus.BAD_REQUEST )));
    }

    @Override
    public TouristicServ getInactiveService(Long id){
        return repositoryTouristicServ.getInactiveTouristicServicesById(id);
    }

    @Override
    public List<TouristicServ> getAllActiveServices() {
        return repositoryTouristicServ.getActiveTouristicServices();
    }

    @Override
    public List<TouristicServ> getAllInactiveServices(){
        return repositoryTouristicServ.getInactiveTouristicServices();
    }

    //When I can merge this branch with Omega 24 branch
    // I will modify this method to only search active id's.
    @Override
    public TouristicServ activateService(Long id) throws ObjectNFException {
        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        optionalTouristicServ.orElseThrow(() -> new ObjectNFException("ID not found.", new ExceptionDetails("There is no service with this ID.",
                "error", HttpStatus.BAD_REQUEST)));

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
