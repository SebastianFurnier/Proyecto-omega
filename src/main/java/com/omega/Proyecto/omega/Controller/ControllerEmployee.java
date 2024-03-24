package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.DTO.EmployeeDTO;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = {"http://localhost:3000", "http://joni-projects.s3-website.eu-north-1.amazonaws.com"})
public class ControllerEmployee {
    @Autowired
    IServiceEmployee IServEmplo;

    private final AuthenticationService authenticationService;

    public ControllerEmployee(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Employee request) throws ErrorDataException, IOException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Employee request) throws IOException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) throws ObjectNFException {
        IServEmplo.deleteEmployee(id);
    }

    @GetMapping("/get/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) throws ObjectNFException {
        return new EmployeeDTO(IServEmplo.getEmployee(id));
    }

    @GetMapping("/getAll")
    public List<EmployeeDTO> getAll() {
        List<Employee> employeeList = IServEmplo.getAllEmployee();
        return employeeList.stream()
                .map(EmployeeDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/getByUsername/{username}")
    public EmployeeDTO getByUsername(@PathVariable String username) throws ObjectNFException {
        return new EmployeeDTO(IServEmplo.findByUsernameFront(username));
    }

    @PutMapping("/modify/{id}")
    public EmployeeDTO modifyEmployee(@PathVariable Long id,
                                      @RequestParam(required = false, name = "newName") String newName,
                                      @RequestParam(required = false, name = "newLastName") String newLastName,
                                      @RequestParam(required = false, name = "newDni") String newDni,
                                      @RequestParam(required = false, name = "newDate") LocalDate newDate,
                                      @RequestParam(required = false, name = "newNationality") String newNationality,
                                      @RequestParam(required = false, name = "newPhoneNumber") String newPhoneNumber,
                                      @RequestParam(required = false, name = "newEmail") String newEmail,
                                      @RequestParam(required = false, name = "newSalary") Long newSalary,
                                      @RequestParam(required = false, name = "flag") boolean flag,
                                      @RequestParam(required = false, name = "rol") Rol newRol)
                                      throws ErrorDataException, ObjectNFException {

        IServEmplo.modifyEmployee(id,newName,newLastName,newDni,newDate,newNationality,newPhoneNumber,newEmail,newSalary,flag,newRol);

        return new EmployeeDTO(IServEmplo.getEmployee(id));
    }

    @GetMapping("/getEmployeesByFlag/{flag}")
    public List<EmployeeDTO> getEmployeesByFlag(@PathVariable boolean flag) {
        List<Employee> employeeList = IServEmplo.getEmployeesByFlag(flag);
        return employeeList.stream()
                .map(EmployeeDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/getEmployeeByFlagAndId/{flag}/{id}")
    public EmployeeDTO getEmployeeByFlagAndId(@PathVariable boolean flag, @PathVariable Long id) throws ObjectNFException, ErrorDataException {
        return new EmployeeDTO(IServEmplo.getEmployeeByFlagAndId(flag, id));
    }
}
