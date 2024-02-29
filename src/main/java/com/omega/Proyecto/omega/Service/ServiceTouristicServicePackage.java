package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServPackage;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTouristicServicePackage implements IServiceTouristicServicePackage {

    @Autowired
    private IRepositoryTouristicServPackage repositoryPackage;
    @Override
    public TouristicServicesPackage createPackage(List<TouristicServ> services) throws ErrorDataException {

        if (services.isEmpty())
            throw new ErrorDataException("Incorrect data.",
                    new ExceptionDetails("Incorrect data.", "error", HttpStatus.BAD_REQUEST));

        TouristicServicesPackage packageAux = new TouristicServicesPackage();
        packageAux.setTouristicServs(services);
        return repositoryPackage.save(packageAux);
    }

    @Override
    public void deletePackage(Long id) throws ObjectNotFoundException {

        Optional<TouristicServicesPackage> optionalTouristicServicesPackage = repositoryPackage.findById(id);

        optionalTouristicServicesPackage.orElseThrow(() -> new ObjectNotFoundException("ID not found.",
                new ExceptionDetails("There is no package with this ID.",
                        "error", HttpStatus.NOT_FOUND)));

        TouristicServicesPackage touristicServicesPackage = optionalTouristicServicesPackage.get();
        touristicServicesPackage.setActive(false);

        repositoryPackage.save(touristicServicesPackage);
    }

    @Override
    public TouristicServicesPackage getPackage(Long id) throws ObjectNotFoundException {
        Optional<TouristicServicesPackage> optionalTouristicServicesPackage = repositoryPackage.findById(id);

        return optionalTouristicServicesPackage.orElseThrow( () -> new ObjectNotFoundException("ID not found.",
                new ExceptionDetails("There is no package with this ID.", "error", HttpStatus.BAD_REQUEST)));
    }

    @Override
    public TouristicServicesPackage getInactivePackage(Long id){
        return repositoryPackage.getInactiveTouristicServicesPackageById(id);
    }

    @Override
    public List<TouristicServicesPackage> getAllPackage() {
        return repositoryPackage.findAll();
    }

    @Override
    public List<TouristicServicesPackage> getAllInactivePackage(){
        return repositoryPackage.getInactiveTouristicServicesPackage();
    }

    @Override
    public void editSalePackage(List<TouristicServ> services, Long idPackage) throws ObjectNotFoundException{
        TouristicServicesPackage packageAux = this.getPackage(idPackage);
        packageAux.setTouristicServs(services);
        repositoryPackage.save(packageAux);
    }
}
