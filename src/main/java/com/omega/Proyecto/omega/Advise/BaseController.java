package com.omega.Proyecto.omega.Advise;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(value = ObjectNFException.class)
    public ResponseEntity<ExceptionDetails> handleObjectNotFoundException(ObjectNFException exception) {
        return new ResponseEntity<>(exception.getDetails(), exception.getDetails().getStatus());
    }

    @ExceptionHandler(value = ErrorDataException.class)
    public ResponseEntity<ExceptionDetails> handleErrorDataException(ErrorDataException exception) {
        return new ResponseEntity<>(exception.getDetails(), exception.getDetails().getStatus());
    }
}
