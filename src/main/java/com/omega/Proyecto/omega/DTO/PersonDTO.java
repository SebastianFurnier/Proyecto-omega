package com.omega.Proyecto.omega.DTO;

import com.omega.Proyecto.omega.Model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long idPerson;
    private String username;
    private String name;
    private String lastname;

    public PersonDTO(Person person) {
        this.idPerson = person.getId();
        this.username = person.getUsername();
        this.name = person.getName();
        this.lastname = person.getLastName();
    }
}
