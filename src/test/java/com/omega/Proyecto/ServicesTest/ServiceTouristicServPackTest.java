package com.omega.Proyecto.ServicesTest;
import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.TouristicServPack;
import com.omega.Proyecto.omega.Model.TouristicServ;
import com.omega.Proyecto.omega.Model.TypeService;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServ;
import com.omega.Proyecto.omega.Repository.IRepositoryTouristicServPack;
import com.omega.Proyecto.omega.Service.ServiceTouristicServPack;
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
        classes = ServiceTouristicServPack.class)
@AutoConfigureMockMvc
public class ServiceTouristicServPackTest
{
    @MockBean
    private IRepositoryTouristicServPack repoTouristicServPack;
    @MockBean
    private IRepositoryTouristicServ repositoryTouristicServ;
    @Autowired
    private ServiceTouristicServPack serviceTouristicServPack;

    private final TouristicServPack touristicServPack = new TouristicServPack();
    private final TouristicServ touristicServ = new TouristicServ(1L,1, TypeService.FLY,
            "Pasaje de avion", "Brasil", LocalDate.now().plusDays(7),
            10000, true);


    @Test
    public void createTouristicServPack() throws ErrorDataException {
        touristicServPack.setTouristicServs(List.of(touristicServ));

        Mockito.when(repoTouristicServPack.save(Mockito.any(TouristicServPack.class))).thenReturn(touristicServPack);

        TouristicServPack touristicServPackAux = serviceTouristicServPack.createPackage(List.of(1L));

        Assertions.assertEquals(touristicServPackAux, touristicServPack);
    }

    @Test(expected = ErrorDataException.class)
    public void createTouristicServPackIncorrectData() throws ErrorDataException{
        serviceTouristicServPack.createPackage(new ArrayList<>());
    }

    @Test(expected = ObjectNFException.class)
    public void deleteIncorrectIdTouristicPackage() throws ObjectNFException {
        Long id = 1L;
        Mockito.doNothing().when(repoTouristicServPack).deleteById(id);

        serviceTouristicServPack.deletePackage(id);
    }

    @Test
    public void getTouristicPackageTest() throws ObjectNFException {
        Long id = 1L;
        Mockito.when(repoTouristicServPack.getTouristicServPackByActiveAndIdPack(true, id))
                .thenReturn(Optional.of(touristicServPack));

        TouristicServPack touristicServPackAux = serviceTouristicServPack.getActivePackage(id);

        Assertions.assertEquals(touristicServPackAux, touristicServPack);
    }

    @Test
    public void getAllTouristicPackageTest(){
        List<TouristicServPack> touristicServicesPackagesList = new ArrayList<>();
        touristicServicesPackagesList.add(touristicServPack);
        Mockito.when(repoTouristicServPack.findAll()).thenReturn(touristicServicesPackagesList);

        List<TouristicServPack> touristicServicesPackagesAuxes = serviceTouristicServPack.getAllPackage();

        Assertions.assertEquals(touristicServicesPackagesAuxes, touristicServicesPackagesList);
    }
}