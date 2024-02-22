package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Client;


import java.time.LocalDate;
import java.util.List;


public interface IServiceClient {
    void createClient(Client cli);
    void deleteClient(Long id);
    Client getClient(Long id);
    List<Client> getAllClient();
    void modifyClient(Long idOriginal, Long newId , String newName , String newUsername , String newDni ,
                             LocalDate newDate , String newNationality , String newPhoneNumbre , String newEmail);
}
