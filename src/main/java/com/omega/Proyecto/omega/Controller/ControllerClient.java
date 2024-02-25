package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Service.IServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ControllerClient {

    @Autowired
    IServiceClient IServClient;

    @PostMapping("/create")
    public void createClient(@RequestBody Client cli){
        IServClient.createClient(cli);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id){
        IServClient.deleteClient(id);
    }

    @GetMapping("/get/{id}")
    public Client getClient(@PathVariable Long id){
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
                               @RequestParam (required = false,name = "newDate") Date newDate,
                               @RequestParam (required = false,name = "newNationality") String newNationality,
                               @RequestParam (required = false,name = "nnewPhoneNumbre")String newPhoneNumbre,
                               @RequestParam (required = false,name = "newEmail")String newEmail){
        IServClient.modifyClient(id,newId,newName,newUsername,newDni,newDate,newNationality,newPhoneNumbre,newEmail);

        return this.IServClient.getClient(id);
    }
}
