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
        System.out.println(e.getCause()+ e.getMessage());
        return  new ResponseEntity("O filtro deve possuir no mínimo 3 letras", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<NotFoundException> notFound(NotFoundException e){
        System.out.println("Cheguei aqui");
        return  new ResponseEntity("Teste123", HttpStatus.BAD_REQUEST);
    }
}
