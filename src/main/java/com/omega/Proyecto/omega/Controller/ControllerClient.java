package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.DTO.PersonDTO;
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

    private List<PersonDTO> makeClientDTOList(List<Client> clientList) {
        return clientList.stream()
                .map(PersonDTO::new)
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
    public PersonDTO getClient(@PathVariable Long id) throws ObjectNFException {
        return new PersonDTO(IServClient.getClient(id));
    }

    @GetMapping("/getAll")
    public List<PersonDTO> getAllClient() {
        List<Client> clientList = IServClient.getAllClient();
        return makeClientDTOList(clientList);
    }

    @PutMapping("/modify/{id}")
    public PersonDTO modifyClient(@PathVariable Long id,
                                  @RequestParam(required = false, name = "newName") String newName,
                                  @RequestParam(required = false, name = "newLastName") String newLastName,
                                  @RequestParam(required = false, name = "newDni") String newDni,
                                  @RequestParam(required = false, name = "newDate") LocalDate newDate,
                                  @RequestParam(required = false, name = "newNationality") String newNationality,
                                  @RequestParam(required = false, name = "newPhoneNumbre") String newPhoneNumbre,
                                  @RequestParam(required = false, name = "newEmail") String newEmail,
                                  @RequestParam(required = false, name = "flag") boolean flag) throws ErrorDataException, ObjectNFException {
        IServClient.modifyClient(id,newName,newLastName,newDni, newDate, newNationality, newPhoneNumbre, newEmail, flag);

        return new PersonDTO(IServClient.getClient(id));
    }

    @GetMapping("/getAllFlag/{flag}")
    public List<PersonDTO> getAllFlag(@PathVariable boolean flag) {
        return makeClientDTOList(IServClient.getClientsByFlag(flag));
    }

    @GetMapping("/getClientFlagAndId/{flag}/{id}")
    public PersonDTO getClientByFlagAndById(@PathVariable boolean flag, @PathVariable Long id) throws ObjectNFException {
        return new PersonDTO(IServClient.getClientByFlagAndId(flag, id));
    }

}
