package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Service.ServiceTouristicServicePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/package")
public class ControllerPackage {
    @Autowired
    private ServiceTouristicServicePackage servicePackage;

    @PostMapping("/create")
    public void createPackage(@RequestBody List<TouristicServ> services) throws ErrorDataException {
        servicePackage.createPackage(services);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePackage(@PathVariable Long id) throws ObjectNotFoundException{
        servicePackage.deletePackage(id);
    }

    @GetMapping("/get/{id}")
    public TouristicServicesPackage getPackage(@PathVariable Long id) throws ObjectNotFoundException{
        return servicePackage.getPackage(id);
    }

    @GetMapping("/getAll")
    public List<TouristicServicesPackage> getAllPackages(){
        return servicePackage.getAllPackage();
    }

    @PutMapping("/addService")
    public void addService(@RequestBody TouristicServ service, @RequestBody Long id) throws ObjectNotFoundException{
        List<TouristicServ> servicesListAux = new ArrayList<>();
        servicesListAux.add(service);
        servicePackage.editSalePackage(servicesListAux, id);
    }

    @PutMapping("/addAllServices")
    public void addAllService(@RequestBody List<TouristicServ> services, @RequestBody Long id) throws ObjectNotFoundException{
        servicePackage.editSalePackage(services, id);
    }
}
