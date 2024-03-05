package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Employee;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IServiceEmployee {
    Employee createEmployee(Employee emplo) throws ErrorDataException;
    void deleteEmployee(Long id) throws ObjectNFException;
    Employee getEmployee(Long id) throws ObjectNFException;
    List<Employee> getAllEmployee();
    void modifyEmployee(Long idOriginal, Long newId , String newName , String newUsername , String newDni ,
                               LocalDate newDate , String newNationality , String newPhoneNumbre , String newEmail , String newPosition , Long newSalary,boolean flag)
                                throws ErrorDataException,ObjectNFException;

    List<Employee> getEmployeesByFlag(boolean flag);

    Optional<Employee> getEmployeeByFlagAndId(boolean flag,Long id) throws ErrorDataException,ObjectNFException;
}
