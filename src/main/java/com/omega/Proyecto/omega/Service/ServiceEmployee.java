package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Model.Rol;
import com.omega.Proyecto.omega.Repository.IRepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee implements IServiceEmployee{

    @Autowired
    IRepositoryEmployee IRepoEmplo;

    @Autowired
    IServiceSale IServSale;


    private String checkDataEmployee(Employee emplo){
        Long adult = 18L;

        if(emplo.getUsername().isEmpty()){
            return "Username or username is empty";
        }

        if(emplo.getEmail().isEmpty()){
            return "The field of Email cannot be empty";
        }

        if(emplo.getDni().isEmpty()){
            return "The field of Dni cannot be empty";
        }

        if (emplo.getPhoneNumber().isEmpty()){
            return "The field of Phone cannot be empty";
        }

        if (emplo.getBirthDay().isAfter(LocalDate.now())){
            return "Incorrect date.";
        }

        LocalDate dateNow = LocalDate.now();
        LocalDate birthday = emplo.getBirthDay();
        Long diference = ChronoUnit.YEARS.between(birthday,dateNow);

        if (diference < adult){
            return "The age is not sufficient for register ";
        }

        if (emplo.getSalary() < 0L){
            return "The salary can´t smallest to 0";
        }

        return null;
    }

    @Override
    public Employee createEmployee(Employee emplo) throws ErrorDataException {
        String errorMessage = checkDataEmployee(emplo) ;
        if (errorMessage != null){
            throw new ErrorDataException(errorMessage,new ExceptionDetails(errorMessage,"error",
                    HttpStatus.BAD_REQUEST));
        }
        emplo.setFlag(true);
        return IRepoEmplo.save(emplo);
    }

    @Override
    public void deleteEmployee(Long id) throws ObjectNFException {
        Employee employee = this.getEmployee(id);
        employee.setFlag(false);

        IRepoEmplo.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) throws ObjectNFException {
        Optional<Employee> optionalEmployee= IRepoEmplo.findById(id);
        return optionalEmployee.orElseThrow(()-> new ObjectNFException("ID not found",
                new ExceptionDetails("ID not found","error",HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return IRepoEmplo.findAll();
    }

    @Override
    public void modifyEmployee(Long idOriginal, Long newId, String newName, String newLastName, String newDni, LocalDate newDate, String newNationality, String newPhoneNumber, String newEmail, Long newSalary, boolean flag, Rol newRol) throws ErrorDataException, ObjectNFException {
        Employee emplo = this.getEmployee(idOriginal);
        emplo.setId(newId);
        emplo.setName(newName);
        emplo.setLastName(newLastName);
        emplo.setDni(newDni);
        emplo.setBirthDay(newDate);
        emplo.setNationality(newNationality);
        emplo.setPhoneNumber(newPhoneNumber);
        emplo.setEmail(newEmail);
        emplo.setSalary(newSalary);
        emplo.setFlag(flag);
        emplo.setRol(newRol);

        this.createEmployee(emplo);
    }


    @Override
    public List<Employee> getEmployeesByFlag(boolean flag) {
        return IRepoEmplo.getEmployeesByFlag(flag);
    }

    @Override
    public Employee getEmployeeByFlagAndId(boolean flag, Long id) throws ObjectNFException{
        Optional<Employee> optionalEmployee= IRepoEmplo.getEmployeeByFlagAndId(flag,id);

        return optionalEmployee.orElseThrow(()-> new ObjectNFException("Id is not found.",
                new ExceptionDetails("ID is not found","error", HttpStatus.NOT_FOUND)));
    }

    @Override
    public Boolean existsByUsername(String username) {
        return IRepoEmplo.existsByUsername(username);
    }

    @Override
    public Optional<Employee> findByUsername(String username) {
        return IRepoEmplo.findByUsername(username);
    }

    @Override
    public Employee findByUsernameFront(String username) throws ObjectNFException {
        Optional<Employee> optionalEmployee = IRepoEmplo.findByUsername(username);
            return optionalEmployee.orElseThrow(()-> new ObjectNFException("ID not found",
                    new ExceptionDetails("ID not found","error",HttpStatus.NOT_FOUND)));
        }
}



