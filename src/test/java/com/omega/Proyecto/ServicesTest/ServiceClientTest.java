package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Repository.IRepositoryClient;
import com.omega.Proyecto.omega.Service.ServiceClient;
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
    public void createClient() throws ErrorDataException {
        Mockito.when(repoClient.save(newClient)).thenReturn(newClient);

        Client clientAux = serviceClient.createClient(newClient);

        Assertions.assertEquals(newClient,clientAux);
    }

    @Test
    public void getClient() throws ObjectNFException {
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
    public void getClientsByFlag(){
        List<Client> clientList = serviceClient.getClientsByFlag(true);
        Mockito.when(repoClient.findAll()).thenReturn(clientList);

        List<Client> clientListAux = serviceClient.getClientsByFlag(true);

        Assertions.assertEquals(clientList,clientListAux);
    }

    @Test
    public void deleteClientTest() throws ObjectNFException {
        Client cli = new Client();
        cli.setId(1L);
        cli.setFlag(true);
        Mockito.when(repoClient.findById(cli.getId())).thenReturn(Optional.of(cli));

        serviceClient.deleteClient(cli.getId());

        Assertions.assertFalse(cli.isFlag());
    }

    @Test (expected = ObjectNFException.class)
    public void deleteClientExceptionTest() throws ObjectNFException{
        Client cli = new Client();
        cli.setId(1L);
        cli.setFlag(true);
        Mockito.when(repoClient.findById(cli.getId())).thenReturn(Optional.of(cli));

        serviceClient.getClientByFlagAndId(false,1L);

        Assertions.assertTrue(cli.isFlag());
    }

    @Test (expected = ObjectNFException.class)
    public void getClientByFlagAndIdTest() throws ObjectNFException {
        Long idOne = 1L;
        Long idTwo = 2L;

        Mockito.when(repoClient.getClientByFlagAndId(true,idOne)).thenReturn(Optional.of(newClient));

        Assertions.assertEquals(serviceClient.getClientByFlagAndId(true,idTwo),newClient);
    }


}