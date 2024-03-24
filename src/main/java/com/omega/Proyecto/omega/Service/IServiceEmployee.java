package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Model.Rol;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IServiceEmployee {
    Employee createEmployee(Employee emplo) throws ErrorDataException;
    void deleteEmployee(Long id) throws ObjectNFException;
    Employee getEmployee(Long id) throws ObjectNFException;
    List<Employee> getAllEmployee();

    List<Employee> getEmployeesByFlag(boolean flag);

    Employee getEmployeeByFlagAndId(boolean flag,Long id) throws ErrorDataException,ObjectNFException;

    Boolean existsByUsername(String username);

    Optional<Employee> findByUsername(String username);

    Employee findByUsernameFront(String username) throws ObjectNFException;

    void modifyEmployee(Long idOriginal, String newName, String newLastName, String newDni, LocalDate newDate,
                        String newNationality, String newPhoneNumber, String newEmail, Long newSalary, boolean flag,
                        Rol newRol) throws ErrorDataException, ObjectNFException;

    Employee activateEmployee(Long id) throws ObjectNFException;
}
