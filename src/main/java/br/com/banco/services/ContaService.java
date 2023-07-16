package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.exceptions.ContaNaoEncontradaException;
import br.com.banco.exceptions.ContaSaldoInsuficienteException;
import br.com.banco.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;

    public Conta findById(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }

    public Conta deposito(Long id, Double valor) {
        Conta conta = findById(id);
        BigDecimal saldo = conta.getSaldo().add(BigDecimal.valueOf(valor));
        Conta contaAfterDeposit = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(saldo)
                .build();
        return contaRepository.save(contaAfterDeposit);
    }

    public Conta saque(Long id, Double valor) {
        Conta conta = findById(id);
        if (conta.getSaldo().compareTo(BigDecimal.valueOf(valor)) < 0) {
            throw new ContaSaldoInsuficienteException("Saldo insuficiente");
        }

        Conta contaAfterSaque = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(conta.getSaldo().subtract(BigDecimal.valueOf(valor)))
                .build();
        return contaRepository.save(contaAfterSaque);
    }

    public Conta transferenciaEntrada(Long id, Double valor) {
        Conta conta = findById(id);
        BigDecimal saldo = conta.getSaldo().add(BigDecimal.valueOf(valor));
        Conta contaAfterTransferenciaEntrada = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(saldo)
                .build();
        return contaRepository.save(contaAfterTransferenciaEntrada);
    }

    public Conta transferenciaSaida(Long id, Double valor) {
        Conta conta = findById(id);
        if (conta.getSaldo().compareTo(BigDecimal.valueOf(valor)) < 0) {
            throw new ContaSaldoInsuficienteException("Saldo insuficiente");
        }

        Conta contaAfterTransferenciaSaida = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(conta.getSaldo().subtract(BigDecimal.valueOf(valor)))
                .build();
        return contaRepository.save(contaAfterTransferenciaSaida);
    }
}
