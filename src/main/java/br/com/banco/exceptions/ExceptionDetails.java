package br.com.banco.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@SuperBuilder
public class ExceptionDetails {
    private String message;
    private HttpStatus status;
    private String endpoint;
    private ZonedDateTime timestamp;
}
