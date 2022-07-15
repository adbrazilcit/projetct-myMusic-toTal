package com.ciandt.summit.bootcamp2022.applications.adapters.controllers.exception;

import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionService {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handler(Exception e){
        ErrorResponse errorResponse  =new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("O filtro deve possuir no mínimo 3 letras");

        return  new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<NotFoundException> notFound(NotFoundException e){
        ErrorResponse errorResponse  =new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NO_CONTENT.value());
        errorResponse.setMessage("Não encontramos as informações solicitadas");

        return  new ResponseEntity(errorResponse, HttpStatus.NO_CONTENT);
    }
}
