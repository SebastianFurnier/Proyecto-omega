package com.omega.Proyecto.omega.DTO;

import com.omega.Proyecto.omega.Model.Rol;
import lombok.Data;

@Data
public class DTORegister {
    private String username;
    private String password;
    private Rol rol;
}
