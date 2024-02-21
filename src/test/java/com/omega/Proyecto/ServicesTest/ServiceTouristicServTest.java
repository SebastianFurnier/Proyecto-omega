package com.omega.Proyecto.repository;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import com.omega.Proyecto.omega.Service.ServiceTouristicServ;
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
        classes = ServiceTouristicServ.class)
@AutoConfigureMockMvc
public class ServiceTouristicServTest
{
    @MockBean
    private IRepositoryTouristicServ repositoryTouristicServ;
    @Autowired
    private ServiceTouristicServ serviceTouristicServ;
    private final TouristicServ touristicServ = new TouristicServ();

    @Test
    public void createTouristicServTest(){
        Mockito.when(repositoryTouristicServ.save(touristicServ)).thenReturn(touristicServ);

        TouristicServ touristicServAux = serviceTouristicServ.createService(touristicServ);

        Assertions.assertEquals(touristicServAux, touristicServ);
    }

    @Test
    public void deleteTourtisticServTest(){
        Long id = 1L;
        Mockito.doNothing().when(repositoryTouristicServ).deleteById(id);

        serviceTouristicServ.deleteService(id);

        Mockito.verify(repositoryTouristicServ, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void getTouristicServTest(){
        Long id = 1L;
        Mockito.when(repositoryTouristicServ.findById(id)).thenReturn(Optional.of(touristicServ));

        TouristicServ touristicServAux = serviceTouristicServ.getService(id);

        Assertions.assertEquals(touristicServAux, touristicServ);
    }

    @Test
    public void getAllTouristicServTest(){
        List<TouristicServ> touristicServices = new ArrayList<>();
        touristicServices.add(touristicServ);
        Mockito.when(repositoryTouristicServ.findAll()).thenReturn(touristicServices);

        List<TouristicServ> touristicServicesAux = serviceTouristicServ.getAllServices();

        Assertions.assertEquals(touristicServicesAux, touristicServices);
    }

    @Test
    public void editServiceTest(){
        Mockito.when(repositoryTouristicServ.save(touristicServ)).thenReturn(touristicServ);
        TouristicServ touristicServAux;

        touristicServAux = serviceTouristicServ.editService(touristicServ);

        Assertions.assertEquals(touristicServAux, touristicServ);
    }
}

