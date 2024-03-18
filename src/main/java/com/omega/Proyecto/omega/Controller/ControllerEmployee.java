package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.AuthenticationResponse;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Model.Rol;
import com.omega.Proyecto.omega.Security.AuthenticationService;
import com.omega.Proyecto.omega.Service.IServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class ControllerEmployee {
    @Autowired
    IServiceEmployee IServEmplo;

    private final AuthenticationService authenticationService;

    public ControllerEmployee(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Employee request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Employee request){
         return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/create")
    public void createEmployee(@RequestBody Employee employee) throws ErrorDataException {
        IServEmplo.createEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) throws ObjectNFException {
        IServEmplo.deleteEmployee(id);
    }

    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable Long id) throws ObjectNFException {
        return IServEmplo.getEmployee(id);
    }

    @GetMapping("/getAll")
    public List<Employee> getAll() {
        return IServEmplo.getAllEmployee();
    }

    @PutMapping("/modify/{id}")
    public Employee modifyEmployee(@PathVariable Long id,
                                   @RequestParam(required = false, name = "newId") Long newId,
                                   @RequestParam(required = false, name = "newUsername") String newUsername,
                                   @RequestParam(required = false, name = "newDni") String newDni,
                                   @RequestParam(required = false, name = "newDate") LocalDate newDate,
                                   @RequestParam(required = false, name = "newNationality") String newNationality,
                                   @RequestParam(required = false, name = "newPhoneNumber") String newPhoneNumber,
                                   @RequestParam(required = false, name = "newEmail") String newEmail,
                                   @RequestParam(required = false, name = "newSalary") Long newSalary,
                                   @RequestParam(required = false, name = "flag") boolean flag,
                                   @RequestParam(required = false, name = "rol") Rol newRol) throws ErrorDataException, ObjectNFException {

        IServEmplo.modifyEmployee(id, newId, newUsername, newDni, newDate, newNationality, newPhoneNumber, newEmail, newSalary, flag, newRol);

        return this.IServEmplo.getEmployee(id);

    }

    @GetMapping("/getEmployeesByFlag/{flag}")
    public List<Employee> getEmployeesByFlag(@PathVariable boolean flag) {
        return IServEmplo.getEmployeesByFlag(flag);
    }

    @GetMapping("/getEmployeeByFlagAndId/{flag}/{id}")
    public Employee getEmployeeByFlagAndId(@PathVariable boolean flag, @PathVariable Long id) throws ObjectNFException, ErrorDataException {
        return IServEmplo.getEmployeeByFlagAndId(flag, id);
    }
}
