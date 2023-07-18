package br.com.banco.exceptions.conta;

import br.com.banco.exceptions.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ContaSaldoInsuficienteExceptionDetails extends ExceptionDetails {
}
