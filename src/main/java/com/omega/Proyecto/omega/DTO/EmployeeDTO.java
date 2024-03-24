package com.omega.Proyecto.omega.DTO;

import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Model.Rol;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeDTO {
    private Long idEmployee;
    private String username;
    private String name;
    private String lastname;
    private Rol rol;
    private String urlImage;
    protected boolean active;

    public EmployeeDTO(Employee employee) {
        this.setIdEmployee(employee.getId());
        this.setUsername(employee.getUsername());
        this.setName(employee.getName());
        this.setLastname(employee.getLastName());
        this.setUrlImage(getUrlImage());
        this.setRol(getRol());
        this.setActive(employee.isFlag());
    }
}
