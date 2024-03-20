package com.omega.Proyecto.omega.DTO;

import com.omega.Proyecto.omega.Model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EmployeeDTO extends PersonDTO{
    private Rol rol;

    public EmployeeDTO(Long id, String username, Rol rol){
        super.setIdPerson(id);
        super.setUsername(username);
        this.rol = rol;
    }
}
