package br.com.banco.entities.dto;

import br.com.banco.entities.TipoTransacao;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class TransferenciaDto {

    private BigDecimal valor;
    private TipoTransacao tipo;
    private String nomeOperadorTransacao;
    private Long contaId;
    private BigDecimal saldo;
}
