package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServ {
    TouristicServ createService(TouristicServ touristicServ) throws ErrorDataException;
    void deleteService(Long id) throws ObjectNotFoundException, ErrorDataException;
    TouristicServ getService(Long id) throws ObjectNotFoundException;
    List<TouristicServ> getAllActiveServices();
    TouristicServ editService(TouristicServ touristicServ) throws ErrorDataException;
    TouristicServ activateService(Long id) throws ObjectNotFoundException, ErrorDataException;
}
