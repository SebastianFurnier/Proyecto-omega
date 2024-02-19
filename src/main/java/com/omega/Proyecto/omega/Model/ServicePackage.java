package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServicePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPackage;
    @ManyToMany
    private List<TouristicServ> touristicServs;
    private float costPackage;
    private boolean discount;

    public void setTouristicServs(List<TouristicServ> services){
        touristicServs = services;
        checkCost();
    }

    private void checkCost(){
        discount = touristicServs.size() > 1;
        costPackage = 0;
        for(TouristicServ serv : touristicServs){
            costPackage = costPackage + serv.getCost();
        }
    }
}
