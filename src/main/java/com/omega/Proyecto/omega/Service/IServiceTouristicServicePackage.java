package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServicePackage {
    TouristicServicesPackage createPackage(List<TouristicServ> services) throws ErrorDataException;
    void deletePackage(Long id) throws ObjectNotFoundException;
    TouristicServicesPackage getActivePackage(Long id) throws ObjectNotFoundException;
    TouristicServicesPackage getInactivePackage(Long id) throws ObjectNotFoundException;
    List<TouristicServicesPackage> getAllPackage();

    List<TouristicServicesPackage> getAllActivePackage();

    void editPackage(List<TouristicServ> services, Long idPackage) throws ObjectNotFoundException;
    List<TouristicServicesPackage> getAllInactivePackage();

}
