package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Repository.IRepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceClient implements IServiceClient{
    @Autowired
    IRepositoryClient repositoryClient;

    @Override
    public Client createClient(Client cli) {
       return repositoryClient.save(cli);
    }

    @Override
    public void deleteClient(Long id) {
        repositoryClient.deleteById(id);
    }

    @Override
    public Client getClient(Long id) {
        return repositoryClient.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAllClient() {
        return repositoryClient.findAll();
    }

    @Override
    public void modifyClient(Long idOriginal, Long newId, String newName, String newUsername, String newDni, LocalDate newDate,
                             String newNationality, String newPhoneNumbre, String newEmail) {

        Client cli = this.getClient(idOriginal);
        cli.setId(newId);
        cli.setName(newName);
        cli.setUsername(newUsername);
        cli.setDni(newDni);
        cli.setBirthDay(newDate);
        cli.setNationality(newNationality);
        cli.setPhoneNumber(newPhoneNumbre);
        cli.setEmail(newEmail);

        repositoryClient.save(cli);
    }
}
