package com.worldline.fdm.exception;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
@CommonsLog
public class RestResponseEntityExceptionHandler {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {
        String errorCode = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());



        ErrorResponse  response =  ErrorResponse.builder().errors(List.of(ErrorResponse.Error
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ErrorResponse.getErrorMsg(errorCode))
                .code(errorCode)
                .build())).build();

        log.info(String.format("Error Response. Response: %s", response.getErrors()));

        return response;

    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(FdmException exception) {
        String errorCode = exception.getMessage();

        ErrorResponse  response =  ErrorResponse.builder().errors(List.of(ErrorResponse.Error
                        .builder()
                        .status(exception.getCode().value())
                        .message(ErrorResponse.getErrorMsg(errorCode))
                        .code(errorCode)
                        .build()))
                .build();

        log.info(String.format("Error Response. Response: %s", response.getErrors()));

        return new ResponseEntity<ErrorResponse>(response, exception.getCode());
    }


}
