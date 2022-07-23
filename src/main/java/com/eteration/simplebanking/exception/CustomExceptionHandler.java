package com.eteration.simplebanking.exception;

import com.eteration.simplebanking.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleInsufficientBalanceException(InsufficientBalanceException exception, WebRequest request) {
        Integer code = HttpStatus.BAD_REQUEST.value();
        String message = exception.getMessage();
        String description = request.getDescription(false);

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(code, new Date(), message, description);
        return new ResponseEntity(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        Integer code = HttpStatus.NOT_FOUND.value();
        String message = exception.getMessage();
        String description = request.getDescription(false);

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(code, new Date(), message, description);
        return new ResponseEntity(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        Integer code = HttpStatus.BAD_REQUEST.value();
        String description = request.getDescription(false);
        String message = "";
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            message += fieldError.getField()+": "+ fieldError.getDefaultMessage() + "; ";
        }

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(code, new Date(), message, description);
        return new ResponseEntity(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleAllException(Exception exception, WebRequest request) {
        Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = exception.getMessage();
        String description = request.getDescription(false);

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(code, new Date(), message, description);
        return new ResponseEntity(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
