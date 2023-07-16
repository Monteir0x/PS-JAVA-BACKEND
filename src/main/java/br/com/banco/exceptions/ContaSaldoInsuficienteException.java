package br.com.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContaSaldoInsuficienteException extends RuntimeException{
    public ContaSaldoInsuficienteException(String message) {
        super(message);
    }
}
