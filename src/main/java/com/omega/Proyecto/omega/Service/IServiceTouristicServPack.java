package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServPack;
import com.omega.Proyecto.omega.Model.TouristicServ;

import java.util.List;

public interface IServiceTouristicServPack {
    TouristicServPack createPackage(List<Long> services) throws ErrorDataException;

    void deletePackage(Long id) throws ObjectNFException;

    TouristicServPack getActivePackage(Long id) throws ObjectNFException;

    TouristicServPack getInactivePackage(Long id) throws ObjectNFException;

    List<TouristicServPack> getAllPackage();

    List<TouristicServPack> getAllActivePackage();

    void editPackage(List<TouristicServ> services, Long idPackage) throws ObjectNFException;

    List<TouristicServPack> getAllInactivePackage();

}
