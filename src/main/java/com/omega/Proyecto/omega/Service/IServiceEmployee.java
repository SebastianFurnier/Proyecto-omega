package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Employee;


import java.util.Date;
import java.util.List;


public interface IServiceEmployee {
    public void createEmployee(Employee emplo);
    public void deleteEmployee(Long id);
    public Employee getEmployee(Long id);
    public List<Employee> getAllEmployee();

    public void modifyEmployee(Long idOriginal, Long newId ,String newName , String newUsername , String newDni ,
                                   Date newDate , String newNationality , String newPhoneNumbre , String newEmail , String newPosition , Double newSalary);
}
