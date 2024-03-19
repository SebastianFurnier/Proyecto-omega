package com.omega.Proyecto.omega.DTO;

import lombok.Data;

@Data
public class DTOAuthResponse {
    private String accesToken;
    private String tokenType = "Bearer ";

    public DTOAuthResponse(String accesToken) {
        this.accesToken = accesToken;
    }
}
