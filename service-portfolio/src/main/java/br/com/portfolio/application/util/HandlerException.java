package br.com.portfolio.application.util;

import br.com.portfolio.application.shared.response.Response;
import br.com.portfolio.domain.shared.entity.exception.DomainException;
import br.com.portfolio.domain.shared.entity.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DomainException.class})
    public ResponseEntity<Response<Void>> handleDomainException(final DomainException ex) {

        final var messages = Arrays.stream(ex.getMessage().split(",")).map(String::trim).toList();

        return Response.failedResponse(HttpStatus.BAD_REQUEST, messages);

    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Response<Void>> handleNotFoundException(final NotFoundException ex) {

        final var messages = Arrays.stream(ex.getMessage().split(",")).map(String::trim).toList();

        return Response.failedResponse(HttpStatus.NOT_FOUND, messages);

    }

}