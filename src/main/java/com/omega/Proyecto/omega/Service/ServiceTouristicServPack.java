package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Model.TouristicServPack;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServPack;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTouristicServPack implements IServiceTouristicServPack {

    @Autowired
    private IRepositoryTouristicServPack repositoryPackage;
    @Autowired
    private IRepositoryTouristicServ repositoryTouristicServ;

    @Override
    public TouristicServPack createPackage(List<Long> services) throws ErrorDataException {

        if (services.isEmpty())
            throw new ErrorDataException("Incorrect data.",
                    new ExceptionDetails("You must send one o more services.", "error", HttpStatus.BAD_REQUEST));

        List<TouristicServ> servList = new ArrayList<>();

        for (Long id: services) {
            Optional<TouristicServ> serv =
                    repositoryTouristicServ.getTouristicServByActiveAndIdServ(true, id);
            serv.ifPresent(servList::add);
        }

        TouristicServPack packageAux = new TouristicServPack();
        packageAux.setTouristicServs(servList);

        return repositoryPackage.save(packageAux);
    }

    @Override
    public void deletePackage(Long id) throws ObjectNFException {

        TouristicServPack touristicServPack = this.getActivePackage(id);
        touristicServPack.setActive(false);

        repositoryPackage.save(touristicServPack);
    }

    @Override
    public TouristicServPack getActivePackage(Long id) throws ObjectNFException {
        Optional<TouristicServPack> optionalTouristicServicesPackage =
                repositoryPackage.getTouristicServPackByActiveAndIdPack(true, id);

        return optionalTouristicServicesPackage.orElseThrow( () -> new ObjectNFException("ID not found.",
                new ExceptionDetails("There is no active package with this ID.", "error",
                        HttpStatus.BAD_REQUEST)));
    }

    @Override
    public TouristicServPack getInactivePackage(Long id) throws ObjectNFException {
        Optional<TouristicServPack> optionalTouristicServicesPackage =
                repositoryPackage.getTouristicServPackByActiveAndIdPack(false, id);

        return optionalTouristicServicesPackage.orElseThrow( () -> new ObjectNFException("ID not found.",
                new ExceptionDetails("There is no package with this ID.",
                        "error", HttpStatus.BAD_REQUEST)));

    }

    @Override
    public List<TouristicServPack> getAllPackage() {
        return repositoryPackage.findAll();
    }

    @Override
    public List<TouristicServPack> getAllInactivePackage(){
        return repositoryPackage.getTouristicServsPacksByActive(false);
    }

    @Override
    public List<TouristicServPack> getAllActivePackage(){
        return repositoryPackage.getTouristicServsPacksByActive(true);
    }

    @Override
    public void editPackage(List<TouristicServ> services, Long idPackage) throws ObjectNFException {
        TouristicServPack packageAux = this.getActivePackage(idPackage);
        packageAux.setTouristicServs(services);
        repositoryPackage.save(packageAux);
    }
}
