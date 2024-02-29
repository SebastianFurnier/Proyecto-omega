package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTouristicServ implements IServiceTouristicServ
{
    @Autowired
    private IRepositoryTouristicServ repositoryTouristicServ;

    private String checkDataService(TouristicServ touristicServ){
        if (touristicServ.getName().isBlank())
            return "Name cannot be empty.";

        if(touristicServ.getCost() <= 0)
            return "Cost must be greater than zero.";

        if(touristicServ.getDestination().isBlank())
            return "Destination cannot be empty";

        if(touristicServ.getServiceDate().isBefore(LocalDate.now()))
            return "Date incorrect.";

        return null;
    }

    @Override
    public TouristicServ createService(TouristicServ touristicServ) throws ErrorDataException {
        String errorMassage = checkDataService(touristicServ);
        if (errorMassage != null){
            throw new ErrorDataException("Incorrect data.",
                    new ExceptionDetails(errorMassage, "error", HttpStatus.BAD_REQUEST));
        }
        return repositoryTouristicServ.save(touristicServ);
    }

    @Override
    public void deleteService(Long id) throws ObjectNotFoundException, ErrorDataException {

        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        optionalTouristicServ.orElseThrow(() -> new ObjectNotFoundException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error", HttpStatus.NOT_FOUND
        )));

        TouristicServ touristicServAux = optionalTouristicServ.get();
        touristicServAux.setActive(false);
        this.createService(touristicServAux);

    }

    @Override
    public TouristicServ getService(Long id) throws ObjectNotFoundException {

        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        return optionalTouristicServ.orElseThrow(() -> new ObjectNotFoundException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error", HttpStatus.NOT_FOUND
        )));
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
    public TouristicServ activateService(Long id) throws ObjectNotFoundException, ErrorDataException {

        Optional<TouristicServ> optionalTouristicServ = repositoryTouristicServ.findById(id);
        optionalTouristicServ.orElseThrow(() -> new ObjectNotFoundException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error", HttpStatus.NOT_FOUND
        )));

        TouristicServ touristicServAux = optionalTouristicServ.get();

        if (!touristicServAux.isActive()) {
            touristicServAux.setActive(true);
            this.createService(touristicServAux);
        }

        return touristicServAux;
    }

    @Override
    public TouristicServ editService(TouristicServ touristicServ) throws ErrorDataException {
        return this.createService(touristicServ);
    }
}
