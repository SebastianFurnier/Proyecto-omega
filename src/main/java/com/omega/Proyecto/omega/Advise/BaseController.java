package com.omega.Proyecto.omega.Advise;

import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleObjectNotFoundException(ObjectNotFoundException exception){
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.BAD_REQUEST);
    }
}
