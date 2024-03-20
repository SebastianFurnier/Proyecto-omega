package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServPack;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Service.ServiceTouristicServPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/package")
@CrossOrigin(origins = "http://localhost:3000/")
public class ControllerPackage {
    @Autowired
    private ServiceTouristicServPack servicePackage;

    @PostMapping("/create")
    public void createPackage(@RequestBody List<TouristicServ> services) throws ErrorDataException {
        servicePackage.createPackage(services);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePackage(@PathVariable Long id) throws ObjectNFException {
        servicePackage.deletePackage(id);
    }

    @GetMapping("/getActive/{id}")
    public TouristicServPack getActivePackage(@PathVariable Long id) throws ObjectNFException {
        return servicePackage.getActivePackage(id);
    }

    @GetMapping("/getInactive/{id}")
    public TouristicServPack getInactivePackage(@PathVariable Long id) throws ObjectNFException{
        return servicePackage.getInactivePackage(id);
    }

    @GetMapping("/getAllActive")
    public List<TouristicServPack>  getAllActivePackage(){
        return servicePackage.getAllActivePackage();
    }

    @GetMapping("/getAllInactive")
    public List<TouristicServPack> getAllInactivePackage(){
        return servicePackage.getAllInactivePackage();
    }

    @GetMapping("/getAll")
    public List<TouristicServPack> getAllPackages(){
        return servicePackage.getAllPackage();
    }

    @PutMapping("/addService")
    public void addService(@RequestBody TouristicServ service, @RequestBody Long id) throws ObjectNFException {
        List<TouristicServ> servicesListAux = new ArrayList<>();
        servicesListAux.add(service);
        servicePackage.editPackage(servicesListAux, id);
    }

    @PutMapping("/addAllServices")
    public void addAllService(@RequestBody List<TouristicServ> services, @RequestBody Long id) throws ObjectNFException {
        servicePackage.editPackage(services, id);
    }
}
