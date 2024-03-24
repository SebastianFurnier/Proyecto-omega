package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Service.IServiceBonusPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = {"http://localhost:3000", "http://joni-projects.s3-website.eu-north-1.amazonaws.com"})
public class ControllerBonusPoint {

    @Autowired
    public IServiceBonusPoint serviceBonusPoint;

    @GetMapping("/dealyEarnings/{today}")
    public String dealyEarnings(@PathVariable LocalDate today) {
        return serviceBonusPoint.getSalesToday(today);
    }

    @GetMapping("/getMonthlySales")
    public String getMonthlySales() {
        return serviceBonusPoint.getMonthlySales();
    }
}