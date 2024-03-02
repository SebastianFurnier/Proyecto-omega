package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.Employee;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IServiceEmployee {
    Employee createEmployee(Employee emplo);
    void deleteEmployee(Long id);
    Employee getEmployee(Long id);
    List<Employee> getAllEmployee();
    void modifyEmployee(Long idOriginal, Long newId , String newName , String newUsername , String newDni ,
                               LocalDate newDate , String newNationality , String newPhoneNumbre , String newEmail , String newPosition , Long newSalary,boolean flag);

    List<Employee> getEmployeesByFlag(boolean flag);

    Optional<Employee> getEmployeeByFlagAndId(boolean flag,Long id);
}
