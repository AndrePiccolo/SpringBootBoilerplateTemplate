package com.generalTemplate.adapter.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(value = {IOException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage resourceNotFoundException(IOException ex){
        log.error("IOException: ", ex);
        return new ErrorMessage("Resource not found");
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage internalServerError(Exception ex){
        log.error("Exception: ", ex);
        return new ErrorMessage("Internal server error");
    }
}
