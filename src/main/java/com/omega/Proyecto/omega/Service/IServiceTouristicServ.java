package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServ {
    TouristicServ createService(TouristicServ touristicServ) throws ErrorDataException;
    void deleteService(Long id) throws ObjectNotFoundException, ErrorDataException;
    TouristicServ getActiveService(Long id) throws ObjectNotFoundException;
    TouristicServ getInactiveService(Long id) throws ObjectNotFoundException;
    List<TouristicServ> getAllActiveServices();
    List<TouristicServ> getAllInactiveServices();
    TouristicServ editService(TouristicServ touristicServ) throws ErrorDataException;

    List<TouristicServ> getAllServices();

    TouristicServ activateService(Long id) throws ObjectNotFoundException, ErrorDataException;
}
