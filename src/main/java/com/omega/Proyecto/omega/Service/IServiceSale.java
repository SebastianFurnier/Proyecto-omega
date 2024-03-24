package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.DTO.DraftSaleDTO;
import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Sale;
import jakarta.mail.MessagingException;

import java.util.List;

public interface IServiceSale {
    Sale createSale(DraftSaleDTO saleDto) throws ErrorDataException, ObjectNFException, MessagingException;

    void deleteSale(Long id) throws ObjectNFException;

    Sale getActiveSale(Long id) throws ObjectNFException;

    Sale getInactiveSale(Long id) throws ObjectNFException;

    List<Sale> getAllSales();

    List<Sale> getAllActiveSales();

    List<Sale> getAllInactiveSales();
}
