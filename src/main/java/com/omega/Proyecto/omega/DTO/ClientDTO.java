package com.omega.Proyecto.omega.DTO;

import com.omega.Proyecto.omega.Model.Client;
import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean active;

    public ClientDTO(Client client) {
        this.setId(client.getId());
        this.setName(client.getName());
        this.setLastName(client.getLastName());
        this.setEmail(client.getEmail());
        this.setPhoneNumber(client.getPhoneNumber());
        this.setActive(client.isFlag());
    }
}
