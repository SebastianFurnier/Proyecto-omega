package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServ {
    TouristicServ createService(TouristicServ touristicServ) throws ErrorDataException;
    void deleteService(Long id) throws ObjectNFException, ErrorDataException;
    TouristicServ getActiveService(Long id) throws ObjectNFException;
    TouristicServ getInactiveService(Long id) throws ObjectNFException;
    List<TouristicServ> getAllActiveServices();
    List<TouristicServ> getAllInactiveServices();
    TouristicServ editService(TouristicServ touristicServ) throws ErrorDataException;

    List<TouristicServ> getAllServices();

    TouristicServ activateService(Long id) throws ObjectNFException, ErrorDataException;
}
