package com.omega.Proyecto.ServicesTest;
import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositorySalePackage;
import com.omega.Proyecto.omega.Service.ServiceTouristicServiceTouristicServicePackage;
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

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ServiceTouristicServiceTouristicServicePackage.class)
@AutoConfigureMockMvc
public class ServiceSalePakageTest
{
    @MockBean
    private IRepositorySalePackage repositorySalePackage;
    @Autowired
    private ServiceTouristicServiceTouristicServicePackage serviceTouristicServicePackage;
    private final TouristicServicesPackage touristicServicesPackage = new TouristicServicesPackage();
    private final List<TouristicServ> listTouristicServices = new ArrayList<>();

    @Test
    public void createSalePackage(){
        Mockito.when(repositorySalePackage.save(touristicServicesPackage)).thenReturn(touristicServicesPackage);

        TouristicServicesPackage touristicServicesPackageAux = serviceTouristicServicePackage.createPackage(listTouristicServices);

        Assertions.assertEquals(touristicServicesPackageAux, touristicServicesPackage);
    }

}
