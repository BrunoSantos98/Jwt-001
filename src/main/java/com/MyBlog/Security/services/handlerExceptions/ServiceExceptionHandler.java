package com.MyBlog.Security.services.handlerExceptions;

import com.MyBlog.Security.exceptions.ObjectConflictException;
import com.MyBlog.Security.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Dado ou objeto não localizado na base de dados",
                e.getMessage(),
                request.getRequestURI()
        );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ObjectConflictException.class)
    public ResponseEntity<StandardError> ObjectConflictException(ObjectConflictException e, HttpServletRequest request){
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "Objeto já existe na base de dados",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
