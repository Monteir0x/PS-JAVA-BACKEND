package br.com.banco.exceptions.conta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaNaoEncontradaException extends RuntimeException{
        public ContaNaoEncontradaException(String message) {
            super(message);
        }
    }

