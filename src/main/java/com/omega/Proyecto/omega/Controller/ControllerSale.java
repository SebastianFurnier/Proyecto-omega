package com.omega.Proyecto.omega.Controller;

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
    public void createSale(@RequestBody Sale sale){
        serviceSale.createSale(sale);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Long id){
        serviceSale.deleteSale(id);
    }

    @GetMapping("/getSale/{id}")
    public Sale getSale(@PathVariable Long id){
        return serviceSale.getSale(id);
    }

    @GetMapping("/getAllSale")
    public List<Sale> getAllSale(){
        return serviceSale.getAllSales();
    }

}
