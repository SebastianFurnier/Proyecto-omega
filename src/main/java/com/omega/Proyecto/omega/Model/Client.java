package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Client extends Person {
    private boolean flag;
}
