package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Repository.IRepositorySale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSale implements IServiceSale {
    @Autowired
    private IRepositorySale repositorySale;

    @Override
    public Sale createSale(Sale sale) {
        return repositorySale.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        repositorySale.deleteById(id);
    }

    @Override
    public Sale getSale(Long id) {
        return repositorySale.findById(id).orElse(null);
    }

    @Override
    public Sale getInactiveSale(Long id){
        return repositorySale.getInactiveSaleById(id);
    }

    @Override
    public List<Sale> getAllSales() {
        return repositorySale.findAll();
    }

    @Override
    public List<Sale> getAllInactiveSales(){
        return repositorySale.getInactiveSale();
    }
}
