package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.Sale;
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
    private final Sale newSale = new Sale();

    @Test
    public void createCorrectSale() throws ErrorDataException {
        Mockito.when(repositorySale.save(newSale)).thenReturn(newSale);

        Sale saleAux = serviceSale.createSale(newSale);

        Assertions.assertEquals(saleAux, newSale);
    }

    @Test
    public void getSale() throws ObjectNotFoundException{
        Long id = 1L;
        Mockito.when(repositorySale.findById(id)).thenReturn(Optional.of(newSale));

        Sale saleAux = serviceSale.getSale(id);

        Assertions.assertEquals(saleAux, newSale);
    }

    @Test
    public void getAllSale(){
        List<Sale> saleList = new ArrayList<>();
        Mockito.when(repositorySale.findAll()).thenReturn(saleList);

        List<Sale> saleListAux = serviceSale.getAllSales();

        Assertions.assertEquals(saleListAux, saleList);
    }

    @Test
    public void deleteSale() throws ObjectNotFoundException {
        Long id = 1L;
        Mockito.doNothing().when(repositorySale).deleteById(id);

        serviceSale.deleteSale(id);

        Mockito.verify(repositorySale, Mockito.times(1)).deleteById(id);
    }
}
