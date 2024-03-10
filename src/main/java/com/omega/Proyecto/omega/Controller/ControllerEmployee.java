package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Service.IServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class ControllerEmployee {

    @Autowired
    IServiceEmployee IServEmplo;

    @PostMapping("/create")
    public void createEmployee(@RequestBody Employee employee) throws ErrorDataException {
        IServEmplo.createEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) throws ObjectNFException {
        IServEmplo.deleteEmployee(id);
    }

    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable Long id) throws ObjectNFException{
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
                                   @RequestParam (required = false,name = "flag")boolean flag) throws ErrorDataException,ObjectNFException{

    IServEmplo.modifyEmployee(id,newId,newName,newUsername,newDni,newDate,newNationality,newPhoneNumbre,newEmail,newPosition,newSalary,flag);

    return this.IServEmplo.getEmployee(id);

    }

    @GetMapping("/getEmployeesByFlag/{flag}")
    public List<Employee> getEmployeesByFlag(@PathVariable boolean flag){
        return IServEmplo.getEmployeesByFlag(flag);
    }

    @GetMapping("/getEmployeeByFlagAndId/{flag}/{id}")
    public Employee getEmployeeByFlagAndId(@PathVariable boolean flag,@PathVariable Long id) throws ObjectNFException, ErrorDataException {
        return IServEmplo.getEmployeeByFlagAndId(flag, id);
    }
}
