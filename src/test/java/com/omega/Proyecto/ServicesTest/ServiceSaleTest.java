package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.DTO.DraftSaleDTO;
import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.*;
import com.omega.Proyecto.omega.Repository.IRepositorySale;
import com.omega.Proyecto.omega.Service.*;
import jakarta.mail.MessagingException;
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

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ServiceSale.class)
@AutoConfigureMockMvc
public class ServiceSaleTest
{
    @MockBean
    private IRepositorySale repositorySale;
    @MockBean
    private ServiceTouristicServPack serviceTouristicServPack;
    @MockBean
    private ServiceClient serviceClient;
    @MockBean
    private ServiceEmployee serviceEmployee;
    @MockBean
    private ServiceTouristicServ serviceTouristicServ;
    @MockBean
    private EmailService emailService;
    @Autowired
    private ServiceSale serviceSale;

    private final Client client = new Client();
    private final Employee employee = new Employee();
    private final TouristicServPack pack = new TouristicServPack();
    private final List<TouristicServ> touristicServList = new ArrayList<>();

    private final Sale newSale = new Sale(1L,  LocalDate.now(), PaymentMethod.DEBIT,
            new Employee(),  new Client(), new TouristicServPack(), true, 1000);

    private final DraftSaleDTO draftSaleDTO = new DraftSaleDTO();

    @Test
    public void createCorrectSale() throws ErrorDataException, ObjectNFException, MessagingException {
        pack.setTouristicServs(touristicServList);

        draftSaleDTO.setDateSale(LocalDate.now());
        draftSaleDTO.setEmployeeId(1L);
        draftSaleDTO.setClientId(2L);
        draftSaleDTO.setPaymentMethod(PaymentMethod.DEBIT);
        draftSaleDTO.setPackId(1L);

        Mockito.when(serviceClient.getClient(2L)).thenReturn(client);
        Mockito.when(serviceEmployee.getEmployee(1L)).thenReturn(employee);
        Mockito.when(serviceTouristicServPack.getActivePackage(1L)).thenReturn(pack);
        Mockito.when(repositorySale.save(newSale)).thenReturn(newSale);

        Sale saleAux = serviceSale.createSale(draftSaleDTO);

        Mockito.verify(repositorySale, Mockito.times(1)).save(any(Sale.class));

    }

    @Test(expected = ErrorDataException.class)
    public void createIncorrectSale() throws ErrorDataException, ObjectNFException, MessagingException {
        serviceSale.createSale(draftSaleDTO);
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