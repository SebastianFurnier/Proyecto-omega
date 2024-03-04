package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Repository.IRepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceClient implements IServiceClient{
    @Autowired
    IRepositoryClient repositoryClient;

    private String checkDataClient(Client client){
        LocalDate hourNow = LocalDate.now();
        int birthday = hourNow.compareTo(client.getBirthDay());
        int adult = 18;


        if(client.getName().isEmpty() && client.getUsername().isEmpty()){
            return "Name or username is empty";
        }

        if(client.getEmail().isEmpty()){
            return "The field of Email cannot be empty";
        }

        if(client.getDni().isEmpty()){
            return "The field of Dni cannot be empty";
        }

        if (client.getPhoneNumber().isEmpty()){
            return "The field of Phone cannot be empty";
        }

        if (client.getBirthDay().isAfter(LocalDate.now())){
         return "Incorrect date.";
        }

        if (birthday < adult){
            return "The age is not sufficient for register ";
        }

        return null;
    }

    @Override
    public Client createClient(Client cli) throws ErrorDataException {
       return repositoryClient.save(cli);
    }

    @Override
    public void deleteClient(Long id) throws ObjectNotFoundException {
        Client cli = this.getClient(id);
        cli.setFlag(false);
        this.createClient(cli);
    }

    @Override
    public Client getClient(Long id) throws ObjectNotFoundException{
        return repositoryClient.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAllClient() {
        return repositoryClient.findAll();
    }

    @Override
    public void modifyClient(Long idOriginal, Long newId, String newName, String newUsername, String newDni, LocalDate newDate,
                             String newNationality, String newPhoneNumbre, String newEmail,boolean flag) throws ErrorDataException{

        Client cli = this.getClient(idOriginal);
        cli.setId(newId);
        cli.setName(newName);
        cli.setUsername(newUsername);
        cli.setDni(newDni);
        cli.setBirthDay(newDate);
        cli.setNationality(newNationality);
        cli.setPhoneNumber(newPhoneNumbre);
        cli.setEmail(newEmail);
        cli.setFlag(flag);

        repositoryClient.save(cli);
    }

    @Override
    public List<Client> getClientsByFlag(boolean flag) {
        return repositoryClient.getClientsByFlag(flag);
    }

    @Override
    public Optional<Client> getClientByFlagAndId(boolean flag, Long id) {
        return repositoryClient.getClientByFlagAndId(flag,id);
    }


}
