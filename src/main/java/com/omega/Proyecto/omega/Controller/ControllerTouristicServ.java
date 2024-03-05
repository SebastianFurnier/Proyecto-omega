package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Service.ServiceTouristicServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class ControllerTouristicServ {
    @Autowired
    private ServiceTouristicServ serviceTouristicServ;

    @PostMapping("/create")
    public void createService(@RequestBody TouristicServ touristicServ) throws ErrorDataException {
        serviceTouristicServ.createService(touristicServ);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteService(@PathVariable Long id) throws ObjectNFException, ErrorDataException {
        serviceTouristicServ.deleteService(id);
    }

    @GetMapping("/get/{id}")
    public TouristicServ getService(@PathVariable Long id) throws ObjectNFException {
        return serviceTouristicServ.getActiveService(id);
    }

    @GetMapping("/getAll")
    public List<TouristicServ> getAllServices(){
        return serviceTouristicServ.getAllServices();
    }

    @PostMapping("/activate/{id}")
    public TouristicServ activateService(@PathVariable Long id) throws ObjectNFException, ErrorDataException {
        return serviceTouristicServ.activateService(id);
    }

    @PutMapping("/edit")
    public void editService(@RequestBody TouristicServ touristicServ) throws ErrorDataException {
        serviceTouristicServ.editService(touristicServ);
    }
}
