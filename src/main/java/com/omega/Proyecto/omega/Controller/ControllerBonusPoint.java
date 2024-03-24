package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Service.IServiceSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = {"http://localhost:3000", "http://joni-projects.s3-website.eu-north-1.amazonaws.com"})
public class ControllerBonusPoint {

    @Autowired
    public IServiceSale IServSale;

    @GetMapping("/dealyEarnings/{today}")
    public String dealyEarnings(@PathVariable Date today){
        return IServSale.getSalesToday(today);
    }

    @GetMapping("/getMonthlySales")
    public String getMonthlySales(){
        return IServSale.getMonthlySales();
    }
}
