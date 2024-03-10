package com.omega.Proyecto.omega.Error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectNFException extends Exception {
    private ExceptionDetails details;

    public ObjectNFException(String message, ExceptionDetails details, Throwable exception){
        super(message, exception);
        this.details = details;
    }

    public ObjectNFException(String message, ExceptionDetails details) {
        super(message);
        this.details = details;
    }
}
