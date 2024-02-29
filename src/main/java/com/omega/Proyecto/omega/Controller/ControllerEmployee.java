package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Service.IServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class ControllerEmployee {

    @Autowired
    IServiceEmployee IServEmplo;

    @PostMapping("/create")
    public void createEmployee(@RequestBody Employee employee){
        IServEmplo.createEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id){
        IServEmplo.deleteEmployee(id);
    }

    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return IServEmplo.getEmployee(id);
    }

    @GetMapping("/getAll")
    public List<Employee> getAll(){
        return IServEmplo.getAllEmployee();
    }

    @PutMapping("/modify/{id}")
    public Employee modifyEmployee(@PathVariable Long id,
                                   @RequestParam (required = false ,name = "newId")Long newId ,
                                   @RequestParam (required = false,name = "newName")String newName,
                                   @RequestParam (required = false,name = "newUsername")String newUsername,
                                   @RequestParam (required = false,name = "newDni")String newDni,
                                   @RequestParam (required = false,name = "newDate") LocalDate newDate,
                                   @RequestParam (required = false,name = "newNationality") String newNationality,
                                   @RequestParam (required = false,name = "newPhoneNumbre")String newPhoneNumbre,
                                   @RequestParam (required = false,name = "newEmail")String newEmail,
                                   @RequestParam (required = false,name = "newPosition")String newPosition,
                                   @RequestParam (required = false,name = "newSalary")Long newSalary,
                                   @RequestParam (required = false,name = "flag")boolean flag){

    IServEmplo.modifyEmployee(id,newId,newName,newUsername,newDni,newDate,newNationality,newPhoneNumbre,newEmail,newPosition,newSalary,flag);

    return this.IServEmplo.getEmployee(id);

    }
}
