package com.ciandt.summit.bootcamp2022.applications.adapters.controllers.exception;

import com.ciandt.summit.bootcamp2022.domain.exceptions.BadRequestException;
import com.ciandt.summit.bootcamp2022.domain.exceptions.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionService extends ResponseEntityExceptionHandler {

    final ErrorResponse errorResponse = new ErrorResponse();

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("O filtro deve possuir no mínimo 3 letras");

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<NotFoundException> notFound(NotFoundException e) {
        errorResponse.setStatus(HttpStatus.NO_CONTENT.value());
        errorResponse.setMessage("Não encontramos as informações solicitadas");

        return new ResponseEntity(errorResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> badRequest(BadRequestException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        errorResponse.setStatus(status.value());
        errorResponse.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<AutenticationException> semAutenticacao(AutenticationException e) {

        System.out.println("semAutenticação");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage("Não Possui acesso, realize a autenticação ");

        return new ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED);
    }


}
