package com.omega.Proyecto.omega.Controller;

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
    public void createPackage(@RequestBody List<TouristicServ> services){
        servicePackage.createPackage(services);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePackage(@PathVariable Long id){
        servicePackage.deletePackage(id);
    }

    @GetMapping("/get/{id}")
    public TouristicServicesPackage getPackage(@PathVariable Long id){
        return servicePackage.getPackage(id);
    }

    @GetMapping("/getAll")
    public List<TouristicServicesPackage> getAllPackages(){
        return servicePackage.getAllPackage();
    }

    @PutMapping("/addService")
    public void addService(@RequestBody TouristicServ service, @RequestBody Long id){
        List<TouristicServ> servicesListAux = new ArrayList<>();
        servicesListAux.add(service);
        servicePackage.editSalePackage(servicesListAux, id);
    }

    @PutMapping("/addAllServices")
    public void addAllService(@RequestBody List<TouristicServ> services, @RequestBody Long id){
        servicePackage.editSalePackage(services, id);
    }


}
