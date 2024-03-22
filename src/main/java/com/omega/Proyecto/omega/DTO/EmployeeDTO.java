package com.omega.Proyecto.omega.DTO;

import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Model.Rol;
import lombok.Data;

@Data
public class EmployeeDTO extends PersonDTO{
    private Rol rol;

    public EmployeeDTO(Employee employee){
        super.setIdPerson(employee.getId());
        super.setUsername(employee.getUsername());
        super.setName(employee.getName());
        super.setLastname(employee.getLastName());
        this.rol = employee.getRol();

    }
}
