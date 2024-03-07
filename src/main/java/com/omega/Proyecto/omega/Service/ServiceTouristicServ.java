package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (touristicServ.getName() == null || touristicServ.getName().isBlank())
            return "Name cannot be empty.";

        if(touristicServ.getCost() <= 0)
            return "Cost must be greater than zero.";

        if(touristicServ.getDestination() == null || touristicServ.getDestination().isBlank())
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
    public void deleteService(Long id) throws ObjectNFException, ErrorDataException {
        TouristicServ touristicServAux = this.getActiveService(id);
        touristicServAux.setActive(false);
        this.createService(touristicServAux);
    }

    @Override
    public TouristicServ getActiveService(Long id) throws ObjectNFException {

        Optional<TouristicServ> optionalTouristicServ =
                repositoryTouristicServ.getTouristicServsByActiveAndIdTouristicService(true, id);
        return optionalTouristicServ.orElseThrow(() -> new ObjectNFException("ID not found.", new ExceptionDetails(
                "There is no active service with this ID.", "error", HttpStatus.NOT_FOUND
        )));
    }

    @Override
    public TouristicServ getInactiveService(Long id) throws ObjectNFException {
        Optional<TouristicServ> optionalTouristicServ =
                repositoryTouristicServ.getTouristicServsByActiveAndIdTouristicService(false, id);
        return optionalTouristicServ.orElseThrow(() -> new ObjectNFException("ID not found.", new ExceptionDetails(
                "There is no service with this ID.", "error", HttpStatus.NOT_FOUND
        )));
    }

    @Override
    public List<TouristicServ> getAllActiveServices() {
        return repositoryTouristicServ.getTouristicServByActive(true);
    }

    @Override
    public List<TouristicServ> getAllInactiveServices(){
        return repositoryTouristicServ.getTouristicServByActive(false);
    }

    @Override
    public List<TouristicServ> getAllServices(){
        return repositoryTouristicServ.findAll();
    }

    @Override
    public TouristicServ activateService(Long id) throws ObjectNFException, ErrorDataException {

        TouristicServ touristicServAux = this.getInactiveService(id);

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
