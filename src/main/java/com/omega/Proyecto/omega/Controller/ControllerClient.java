package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Service.IServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ControllerClient {

    @Autowired
    IServiceClient IServClient;

    @PostMapping("/create")
    public void createClient(@RequestBody Client cli) throws ErrorDataException {
         IServClient.createClient(cli);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) throws ObjectNFException {
        IServClient.deleteClient(id);
    }

    @GetMapping("/get/{id}")
    public Client getClient(@PathVariable Long id) throws ObjectNFException{
        return IServClient.getClient(id);
    }

    @GetMapping("/getAll")
    public List<Client> getAllClient(){
        return IServClient.getAllClient();
    }

    @PutMapping("/modify/{id}")
    public Client modifyClient(@PathVariable Long id,
                               @RequestParam (required = false ,name = "newId")Long newId ,
                               @RequestParam (required = false,name = "newName")String newName,
                               @RequestParam (required = false,name = "newUsername")String newUsername,
                               @RequestParam (required = false,name = "newDni")String newDni,
                               @RequestParam (required = false,name = "newDate") LocalDate newDate,
                               @RequestParam (required = false,name = "newNationality") String newNationality,
                               @RequestParam (required = false,name = "newPhoneNumbre")String newPhoneNumbre,
                               @RequestParam (required = false,name = "newEmail")String newEmail,
                               @RequestParam (required = false,name = "flag") boolean flag) throws ErrorDataException,ObjectNFException{
        IServClient.modifyClient(id,newId,newName,newUsername,newDni,newDate,newNationality,newPhoneNumbre,newEmail,flag);

        return this.IServClient.getClient(id);
    }

    @GetMapping("/getAllFlag/{flag}")
    public List<Client> getAllFlag(@PathVariable boolean flag){
        return IServClient.getClientsByFlag(flag);
    }

    @GetMapping("/getClientFlagAndId/{flag}/{id}")
    public Client getClientByFlagAndById(@PathVariable boolean flag,@PathVariable Long id) throws ObjectNFException{
        return IServClient.getClientByFlagAndId(flag,id);
    }

}
