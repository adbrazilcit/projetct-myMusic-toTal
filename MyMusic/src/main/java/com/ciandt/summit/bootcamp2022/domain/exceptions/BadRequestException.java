package com.ciandt.summit.bootcamp2022.domain.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
