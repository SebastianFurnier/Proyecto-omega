package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Client;

import java.time.LocalDate;
import java.util.List;

public interface IServiceClient {
    Client createClient(Client cli) throws ErrorDataException;

    void deleteClient(Long idClient) throws ObjectNFException;

    Client getClient(Long idClient) throws ObjectNFException;

    List<Client> getAllClient();

    void modifyClient(Client client) throws ErrorDataException, ObjectNFException;

    List<Client> getClientsByFlag(boolean flag);

    Client getClientByFlagAndId(boolean flag, Long idClient) throws ObjectNFException;

    Client activateClient(Long id) throws ObjectNFException;
}
