package com.omega.Proyecto.omega.Error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectNotFoundException extends Exception {
    private ExceptionDetails details;

    public  ObjectNotFoundException(String message, ExceptionDetails details, Throwable exception){
        super(message, exception);
        this.details = details;
    }

    public ObjectNotFoundException(String message, ExceptionDetails details) {
        super(message);
        this.details = details;
    }
}
