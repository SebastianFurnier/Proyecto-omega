package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.DTO.ClientDTO;
import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Service.IServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = {"http://localhost:3000", "http://joni-projects.s3-website.eu-north-1.amazonaws.com"})

public class ControllerClient {

    @Autowired
    IServiceClient IServClient;

    private List<ClientDTO> makeClientDTOList(List<Client> clientList) {
        return clientList.stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public void createClient(@RequestBody Client cli) throws ErrorDataException {
        IServClient.createClient(cli);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) throws ObjectNFException {
        IServClient.deleteClient(id);
    }

    @GetMapping("/get/{id}")
    public ClientDTO getClient(@PathVariable Long id) throws ObjectNFException {
        return new ClientDTO(IServClient.getClient(id));
    }

    @GetMapping("/getAll")
    public List<ClientDTO> getAllClient() {
        List<Client> clientList = IServClient.getAllClient();
        return makeClientDTOList(clientList);
    }

    @PutMapping("/modify")
    public ClientDTO modifyClient(@RequestBody Client client) throws ErrorDataException, ObjectNFException {
        IServClient.modifyClient(client);

        return new ClientDTO(IServClient.getClient(client.getId()));
    }

    @GetMapping("/getAllFlag/{flag}")
    public List<ClientDTO> getAllFlag(@PathVariable boolean flag) {
        return makeClientDTOList(IServClient.getClientsByFlag(flag));
    }

    @GetMapping("/getClientFlagAndId/{flag}/{id}")
    public ClientDTO getClientByFlagAndById(@PathVariable boolean flag, @PathVariable Long id) throws ObjectNFException {
        return new ClientDTO(IServClient.getClientByFlagAndId(flag, id));
    }

    @PutMapping("activate/{id}")
    public ClientDTO getActiveClient(@PathVariable Long id) throws ObjectNFException {
        return new ClientDTO(IServClient.activateClient(id));
    }

}
