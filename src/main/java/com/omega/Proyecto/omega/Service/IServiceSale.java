package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Sale;

import java.util.List;

public interface IServiceSale {
    public Sale createSale(Sale sale);
    public void deleteSale(Long id);
    public Sale getSale(Long id);
    Sale getInactiveSale(Long id);
    public List<Sale> getAllSales();
    List<Sale> getAllInactiveSales();
}
