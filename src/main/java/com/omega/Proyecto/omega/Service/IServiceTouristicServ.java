package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServ {
    TouristicServ createService(TouristicServ touristicServ);
    boolean deleteService(Long id) throws ObjectNotFoundException;
    TouristicServ getService(Long id) throws ObjectNotFoundException;
    TouristicServ getInactiveService(Long id);
    List<TouristicServ> getAllActiveServices();
    TouristicServ editService(TouristicServ touristicServ);
    List<TouristicServ> getAllInactiveServices();
    TouristicServ activateService(Long id) throws ObjectNotFoundException;
}
