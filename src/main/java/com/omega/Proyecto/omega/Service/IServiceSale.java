package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Sale;

import java.util.List;

public interface IServiceSale {
    public Sale createSale(Sale sale) throws ErrorDataException;
    public void deleteSale(Long id) throws ObjectNFException;
    public Sale getActiveSale(Long id) throws ObjectNFException;

    Sale getInactiveSale(Long id) throws ObjectNFException;

    public List<Sale> getAllSales();

    List<Sale> getAllActiveSales();

    List<Sale> getAllInactiveSales();
}
