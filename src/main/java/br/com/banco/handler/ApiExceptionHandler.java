package br.com.banco.handler;

import br.com.banco.exceptions.conta.ContaNaoEncontradaException;
import br.com.banco.exceptions.conta.ContaNaoEncontradaExceptionDetails;
import br.com.banco.exceptions.conta.ContaSaldoInsuficienteException;
import br.com.banco.exceptions.conta.ContaSaldoInsuficienteExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ContaNaoEncontradaExceptionDetails> handleContaNaoEncontradaException(ContaNaoEncontradaException ex, HandlerMethod handlerMethod){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                ContaNaoEncontradaExceptionDetails.builder()
                        .message(ex.getMessage())
                        .status(status)
                        .endpoint(handlerMethod.getMethod().getAnnotation(org.springframework.web.bind.annotation.RequestMapping.class).value()[0])
                        .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                        .build(),
                status
        );
    }

    @ExceptionHandler(ContaSaldoInsuficienteException.class)
    public ResponseEntity<ContaSaldoInsuficienteExceptionDetails> handleContaNaoEncontradaException(ContaSaldoInsuficienteException ex, HandlerMethod handlerMethod){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                ContaSaldoInsuficienteExceptionDetails.builder()
                        .message(ex.getMessage())
                        .status(status)
                        .endpoint(handlerMethod.getMethod().getAnnotation(org.springframework.web.bind.annotation.RequestMapping.class).value()[0])
                        .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                        .build(),
                status
        );
    }
}
