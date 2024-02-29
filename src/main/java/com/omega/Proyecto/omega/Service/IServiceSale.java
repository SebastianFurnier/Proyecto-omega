package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.Sale;

import java.util.List;

public interface IServiceSale {
    public Sale createSale(Sale sale) throws ErrorDataException;
    public void deleteSale(Long id) throws ObjectNotFoundException;
    public Sale getSale(Long id) throws ObjectNotFoundException;

    Sale getInactiveSale(Long id);

    public List<Sale> getAllSales();
    List<Sale> getAllInactiveSales();
}
