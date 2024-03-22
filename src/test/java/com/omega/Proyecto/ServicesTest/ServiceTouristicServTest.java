
package com.omega.Proyecto.ServicesTest;
import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Model.TypeService;
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

import java.time.LocalDate;
import java.util.ArrayList;
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

    private final TouristicServ touristicServ = new TouristicServ(1L, 10, TypeService.FLY,
            "Pasaje de avion", "Brasil", LocalDate.now().plusDays(7),
            10000, true);

    @Test
    public void createCorrectTouristicServTest() throws ErrorDataException {

        Mockito.when(repositoryTouristicServ.save(this.touristicServ)).thenReturn(this.touristicServ);

        TouristicServ touristicServAux = serviceTouristicServ.createService(this.touristicServ);

        Assertions.assertEquals(touristicServAux, this.touristicServ);
    }

    @Test(expected = ErrorDataException.class)
    public void createIncorrectTouristicServTest() throws ErrorDataException {
        TouristicServ touristicServAux = new TouristicServ();

        serviceTouristicServ.createService(touristicServAux);
    }

    @Test(expected = ObjectNFException.class)
    public void deleteIncorrectIdTouristicServTest() throws ObjectNFException, ErrorDataException {
        Long id = 1L;
        Mockito.doNothing().when(repositoryTouristicServ).deleteById(id);

        serviceTouristicServ.deleteService(id);
    }

    @Test
    public void getTouristicServTest() throws ObjectNFException {
        Long id = 1L;
        Mockito.when(repositoryTouristicServ.getTouristicServByActiveAndIdServ(true, id))
                .thenReturn(Optional.of(touristicServ));

        TouristicServ touristicServAux = serviceTouristicServ.getActiveService(id);

        Assertions.assertEquals(touristicServAux, touristicServ);
    }

    @Test
    public void getAllTouristicServTest(){
        Mockito.when(repositoryTouristicServ.findAll()).thenReturn(new ArrayList<>());

        serviceTouristicServ.getAllServices();

        Mockito.verify(repositoryTouristicServ, Mockito.times(1)).findAll();

    }

    @Test
    public void editServiceTest() throws ErrorDataException {
        Mockito.when(repositoryTouristicServ.save(touristicServ)).thenReturn(touristicServ);
        TouristicServ touristicServAux;

        touristicServAux = serviceTouristicServ.editService(touristicServ);

        Assertions.assertEquals(touristicServAux, touristicServ);
    }

    @Test(expected = ErrorDataException.class)
    public void editServiceIncorrectDataTest() throws ErrorDataException {
        Mockito.when(repositoryTouristicServ.save(touristicServ)).thenReturn(touristicServ);
        TouristicServ touristicServAux = new TouristicServ();

        serviceTouristicServ.editService(touristicServAux);

    }
}
