package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.*;
import com.omega.Proyecto.omega.Repository.IRepositorySale;
import com.omega.Proyecto.omega.Service.ServiceSale;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ServiceSale.class)
@AutoConfigureMockMvc
public class ServiceSaleTest
{
    @MockBean
    private IRepositorySale repositorySale;
    @Autowired
    private ServiceSale serviceSale;
    private final Sale newSale = new Sale(1L,  LocalDate.now(), PaymentMethod.DEBIT,
            new Employee(),  new Client(), new TouristicServPack(), true);

    @Test
    public void createCorrectSale() throws ErrorDataException {
        Mockito.when(repositorySale.save(newSale)).thenReturn(newSale);

        Sale saleAux = serviceSale.createSale(newSale);

        Assertions.assertEquals(saleAux, newSale);
    }

    @Test(expected = ErrorDataException.class)
    public void createIncorrectSale() throws ErrorDataException{
        serviceSale.createSale(new Sale());
    }

    @Test
    public void getSale() throws ObjectNFException {
        Long id = 1L;
        Mockito.when(repositorySale.getSalesByActiveAndIdSale(true, id)).thenReturn(Optional.of(newSale));

        Sale saleAux = serviceSale.getActiveSale(id);

        Assertions.assertEquals(saleAux, newSale);
    }

    @Test(expected = ObjectNFException.class)
    public void getSaleIncorrectID() throws ObjectNFException {
        Long id = 1L;
        Long idAux = 2L;

        Mockito.when(repositorySale.getSalesByActiveAndIdSale(true, id)).thenReturn(Optional.of(newSale));

        serviceSale.getActiveSale(idAux);

    }

    @Test
    public void getAllSale(){
        List<Sale> saleList = new ArrayList<>();
        Mockito.when(repositorySale.findAll()).thenReturn(saleList);

        List<Sale> saleListAux = serviceSale.getAllSales();

        Assertions.assertEquals(saleListAux, saleList);
    }

    @Test(expected = ObjectNFException.class)
    public void deleteIncorrectSale() throws ObjectNFException {
        Long id = 1L;
        Mockito.doNothing().when(repositorySale).deleteById(id);

        serviceSale.deleteSale(id);

    }
}
