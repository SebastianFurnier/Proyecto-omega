package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Repository.IRepositoryClient;
import com.omega.Proyecto.omega.Service.ServiceClient;
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
    public void createClientTest(){
        Mockito.when(repoClient.save(newClient)).thenReturn(newClient);

        Client clientAux = serviceClient.createClient(newClient);

        Assertions.assertEquals(newClient,clientAux);
    }


}
