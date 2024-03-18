package com.omega.Proyecto.omega.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Client extends Person{
    private boolean flag;
}
