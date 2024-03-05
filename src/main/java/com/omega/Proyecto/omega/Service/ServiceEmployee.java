package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Repository.IRepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee implements IServiceEmployee{

    @Autowired
    IRepositoryEmployee IRepoEmplo;

    @Override
    public Employee createEmployee(Employee emplo) throws ErrorDataException {
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
        return IRepoEmplo.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return IRepoEmplo.findAll();
    }

    @Override
    public void modifyEmployee(Long idOriginal, Long newId, String newName, String newUsername, String newDni, LocalDate newDate, String newNationality,
                               String newPhoneNumber, String newEmail , String newPosition, Long newSalary,boolean flag)
                                throws ErrorDataException,ObjectNFException{

        Employee emplo = this.getEmployee(idOriginal);

        emplo.setId(newId);
        emplo.setName(newName);
        emplo.setUsername(newUsername);
        emplo.setDni(newDni);
        emplo.setBirthDay(newDate);
        emplo.setNationality(newNationality);
        emplo.setPhoneNumber(newPhoneNumber);
        emplo.setEmail(newEmail);
        emplo.setPosition(newPosition);
        emplo.setSalary(newSalary);
        emplo.setFlag(flag);

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
}
