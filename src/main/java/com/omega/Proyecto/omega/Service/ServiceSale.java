package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Repository.IRepositorySale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSale implements IServiceSale {
    @Autowired
    private IRepositorySale repositoryVenta; //Cambiar por: repositorySale , para que el codigo sea todo en ingles.

    @Override
    public Sale createSale(Sale sale) {
        return repositoryVenta.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        repositoryVenta.deleteById(id);
    }

    @Override
    public Sale getSale(Long id) {
        return repositoryVenta.findById(id).orElse(null);
    }

    @Override
    public List<Sale> getAllSales() {
        return repositoryVenta.findAll();
    }
}
