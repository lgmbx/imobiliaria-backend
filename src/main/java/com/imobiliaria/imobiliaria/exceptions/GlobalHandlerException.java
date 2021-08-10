package com.imobiliaria.imobiliaria.exceptions;

import com.imobiliaria.imobiliaria.models.error.DefaultError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
@ResponseBody
public class GlobalHandlerException {

    //dado duplicado
    @ExceptionHandler(DuplicatedDataException.class)
    protected ResponseEntity<DefaultError> duplicatedDataExceptionHandler(
            DuplicatedDataException ex,
            HttpServletRequest request
    ){
        HashMap<String, String> errors = new HashMap<>();
        errors.put("duplicatedData", ex.getMessage());

        DefaultError error = new DefaultError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                errors,
                request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //exceptions do validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<DefaultError> validationExceptionHandler(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){
        HashMap<String, String> validationErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(e -> {
            String error = ((FieldError)e).getField();
            String message = e.getDefaultMessage();
            validationErrors.put(error, message);
        });

        DefaultError error = new DefaultError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "DATA VALIDATION",
                validationErrors,
                request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //dado nao encontrado
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<DefaultError> notFoundExceptionHandler(
            NotFoundException ex,
            HttpServletRequest request
    ){
        HashMap<String, String> errors = new HashMap<>();
        errors.put("Dado não encontrado", ex.getMessage());

        DefaultError error = new DefaultError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                errors,
                request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<DefaultError> SQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException ex,
            HttpServletRequest request
    ){
        HashMap<String, String> errors = new HashMap<>();
        errors.put("Dado em utilização", ex.getMessage());

        DefaultError error = new DefaultError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "NOT AUTHORIZED",
                errors,
                request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
