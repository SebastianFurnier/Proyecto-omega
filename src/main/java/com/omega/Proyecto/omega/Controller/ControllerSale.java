package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Service.ServiceSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class ControllerSale {
    @Autowired
    private ServiceSale serviceSale;

    @PostMapping("/create")
    public void createSale(@RequestBody Sale sale) throws ErrorDataException {
        serviceSale.createSale(sale);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Long id) throws ObjectNFException {
        serviceSale.deleteSale(id);
    }

    @GetMapping("/getActiveSale/{id}")
    public Sale getActiveSale(@PathVariable Long id) throws ObjectNFException {
        return serviceSale.getActiveSale(id);
    }

    @GetMapping("/getInactiveSale/{id}")
    public Sale getInactiveSale(@PathVariable Long id) throws ObjectNFException{
        return serviceSale.getInactiveSale(id);
    }

    @GetMapping("/getAllActive")
    public List<Sale> getAllActiveSales(){
        return serviceSale.getAllActiveSales();
    }

    @GetMapping("/getAllInactive")
    public List<Sale> getAllInactiveSales(){
        return serviceSale.getAllInactiveSales();
    }

    @GetMapping("/getAllSale")
    public List<Sale> getAllSale(){
        return serviceSale.getAllSales();
    }
}
