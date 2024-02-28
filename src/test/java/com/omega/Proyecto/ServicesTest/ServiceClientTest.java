package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Repository.IRepositoryClient;
import com.omega.Proyecto.omega.Service.ServiceClient;
import com.omega.Proyecto.omega.Service.ServiceSale;
import org.aspectj.apache.bcel.generic.InstructionConstants;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
        classes = ServiceClient.class)
@AutoConfigureMockMvc
public class ServiceClientTest
{
    @MockBean
    private IRepositoryClient repoClient;
    @Autowired
    private ServiceClient serviceClient;
    private final Client newClient = new Client();


    @Test
    public void createClient(){
        Mockito.when(repoClient.save(newClient)).thenReturn(newClient);

        Client clientAux = serviceClient.createClient(newClient);

        Assertions.assertEquals(newClient,clientAux);
    }

    @Test
    public void getClient(){
        Long id = 1L;
        Mockito.when(repoClient.findById(id)).thenReturn(Optional.of(newClient));

        Client clientAux = serviceClient.getClient(id);

        Assertions.assertEquals(clientAux,newClient);
    }

    @Test
    public void getAllClient(){
        List<Client> clientList = new ArrayList<>();
        Mockito.when(repoClient.findAll()).thenReturn(clientList);

        List<Client> clientListAux = serviceClient.getAllClient();

        Assertions.assertEquals(clientListAux,clientListAux);
    }

    @Test
    public void deleteClient(){
        Long id = 1L;
        Mockito.doNothing().when(repoClient).deleteById(id);

        serviceClient.deleteClient(id);

        Mockito.verify(repoClient, Mockito.times(1)).deleteById(id);
    }

}
