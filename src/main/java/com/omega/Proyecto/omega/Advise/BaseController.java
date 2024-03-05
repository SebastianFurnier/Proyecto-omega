package com.omega.Proyecto.omega.Advise;

import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(value = ObjectNFException.class)
    public ResponseEntity<ExceptionDetails> handleObjectNotFoundException(ObjectNFException exception){
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }
}
