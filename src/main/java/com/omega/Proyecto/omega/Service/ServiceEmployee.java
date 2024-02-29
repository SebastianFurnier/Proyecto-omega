package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Repository.IRepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceEmployee implements IServiceEmployee{

    @Autowired
    IRepositoryEmployee IRepoEmplo;

    @Override
    public Employee createEmployee(Employee emplo) {
        return IRepoEmplo.save(emplo);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = this.getEmployee(id);
        employee.setFlag(false);
        this.createEmployee(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return IRepoEmplo.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return IRepoEmplo.findAll();
    }

    @Override
    public void modifyEmployee(Long idOriginal, Long newId, String newName, String newUsername, String newDni, LocalDate newDate, String newNationality,
                               String newPhoneNumber, String newEmail , String newPosition, Long newSalary,boolean flag) {

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
}
