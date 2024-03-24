package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Repository.IRepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceClient implements IServiceClient {
    @Autowired
    IRepositoryClient repositoryClient;

    private String checkDataClient(Client client) {
        LocalDate dateNow = LocalDate.now();
        LocalDate birthday = client.getBirthDay();
        long diference = ChronoUnit.YEARS.between(birthday, dateNow);
        long adult = 18L;


        if (client.getEmail().isEmpty()) {
            return "Email is empty";
        }

        if (client.getEmail().isEmpty()) {
            return "The field of Email cannot be empty";
        }

        if (client.getDni().isEmpty()) {
            return "The field of Dni cannot be empty";
        }

        if (client.getPhoneNumber().isEmpty()) {
            return "The field of Phone cannot be empty";
        }

        if (client.getBirthDay().isAfter(LocalDate.now())) {
            return "Incorrect date.";
        }

        if (diference < adult) {
            return "The age is not sufficient for register ";
        }

        return null;
    }


    @Override
    public Client createClient(Client cli) throws ErrorDataException {
        String errorMessage = checkDataClient(cli);
        if (errorMessage != null) {
            throw new ErrorDataException(errorMessage, new ExceptionDetails(errorMessage, "error", HttpStatus.BAD_REQUEST));
        }
        cli.setFlag(true);
        return repositoryClient.save(cli);
    }

    @Override
    public void deleteClient(Long id) throws ObjectNFException {
        Client cli = this.getClient(id);
        cli.setFlag(false);

        repositoryClient.save(cli);
    }

    @Override
    public Client getClient(Long id) throws ObjectNFException {
        Optional<Client> optionalClient = repositoryClient.findById(id);

        return optionalClient.orElseThrow(() -> new ObjectNFException("ID not found",
                new ExceptionDetails("ID not found", "error", HttpStatus.NOT_FOUND)));
    }


    @Override
    public List<Client> getAllClient() {
        return repositoryClient.findAll();
    }

    @Override
    public void modifyClient(Long idOriginal, String newName, String newLastName, String newDni,
                             LocalDate newDate, String newNationality, String newPhoneNumbre, String newEmail,
                             boolean flag) throws ObjectNFException {
        Client cli = this.getClient(idOriginal);
        if (newName != null) {
            cli.setName(newName);
        }
        if (newLastName != null) {
            cli.setLastName(newLastName);
        }
        if (newDni != null) {
            cli.setDni(newDni);
        }
        if (newDate != null) {
            cli.setBirthDay(newDate);
        }
        if (newNationality != null) {
            cli.setNationality(newNationality);
        }
        if (newPhoneNumbre != null) {
            cli.setPhoneNumber(newPhoneNumbre);
        }
        if (newEmail != null) {
            cli.setEmail(newEmail);
        }

        cli.setFlag(true);

        repositoryClient.save(cli);
    }

    @Override
    public List<Client> getClientsByFlag(boolean flag) {
        return repositoryClient.getClientsByFlag(flag);
    }

    @Override
    public Client getClientByFlagAndId(boolean flag, Long idClient) throws ObjectNFException {
        Optional<Client> optionalClient = repositoryClient.getClientByFlagAndId(flag, idClient);

        return optionalClient.orElseThrow(() -> new ObjectNFException("Id is not found"
                , new ExceptionDetails("Id is not found", "error", HttpStatus.NOT_FOUND)));
    }

    @Override
    public Client activateClient(Long id) throws ObjectNFException {
        Client client = this.getClient(id);
        client.setFlag(true);
        return client;
    }
}
