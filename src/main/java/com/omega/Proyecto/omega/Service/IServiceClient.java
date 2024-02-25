package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Client;


import java.util.Date;
import java.util.List;


public interface IServiceClient {
    public void createClient(Client cli);
    public void deleteClient(Long id);
    public Client getClient(Long id);
    public List<Client> getAllClient();
    public void modifyClient(Long idOriginal, Long newId , String newName , String newUsername , String newDni ,
                             Date newDate , String newNationality , String newPhoneNumbre , String newEmail);
}
