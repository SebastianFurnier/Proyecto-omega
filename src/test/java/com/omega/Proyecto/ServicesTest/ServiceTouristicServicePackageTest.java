package com.omega.Proyecto.ServicesTest;
import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServPackage;
import com.omega.Proyecto.omega.Service.ServiceTouristicServicePackage;
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
        classes = ServiceTouristicServicePackage.class)
@AutoConfigureMockMvc
public class ServiceTouristicServicePackageTest
{
    @MockBean
    private IRepositoryTouristicServPackage repositoryTouristicServPackage;
    @Autowired
    private ServiceTouristicServicePackage serviceTouristicServicePackage;
    private final TouristicServicesPackage touristicServicesPackage = new TouristicServicesPackage();
    private final List<TouristicServ> listTouristicServices = new ArrayList<>();

    //Corregir prueba
    @Test
    public void createTouristicServPackage(){
        Mockito.when(repositoryTouristicServPackage.save(touristicServicesPackage)).thenReturn(touristicServicesPackage);

        TouristicServicesPackage touristicServicesPackageAux = serviceTouristicServicePackage.createPackage(listTouristicServices);

        //Assertions.assertTrue(true);
    }

    @Test
    public void deleteTouristicPackage(){
        Long id = 1L;
        Mockito.doNothing().when(repositoryTouristicServPackage).deleteById(id);

        serviceTouristicServicePackage.deletePackage(id);

        Mockito.verify(repositoryTouristicServPackage, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void getTouristicPackageTest(){
        Long id = 1L;
        Mockito.when(repositoryTouristicServPackage.findById(id)).thenReturn(Optional.of(touristicServicesPackage));

        TouristicServicesPackage touristicServicesPackageAux = serviceTouristicServicePackage.getPackage(id);

        Assertions.assertEquals(touristicServicesPackageAux, touristicServicesPackage);
    }

    @Test
    public void getAllTouristicPackageTest(){
        List<TouristicServicesPackage> touristicServicesPackagesList = new ArrayList<>();
        touristicServicesPackagesList.add(touristicServicesPackage);
        Mockito.when(repositoryTouristicServPackage.findAll()).thenReturn(touristicServicesPackagesList);

        List<TouristicServicesPackage> touristicServicesPackagesAux = serviceTouristicServicePackage.getAllPackage();

        Assertions.assertEquals(touristicServicesPackagesAux, touristicServicesPackagesList);
    }
}
