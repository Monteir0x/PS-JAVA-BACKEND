package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.exceptions.conta.ContaNaoEncontradaException;
import br.com.banco.exceptions.conta.ContaSaldoInsuficienteException;
import br.com.banco.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;

    public Conta findById(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }

    @Transactional
    public void deposito(Long id, Double valor) {
        Conta conta = findById(id);
        BigDecimal saldo = conta.getSaldo().add(BigDecimal.valueOf(valor));
        Conta contaAfterDeposit = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(saldo)
                .build();
        contaRepository.save(contaAfterDeposit);
    }

    @Transactional
    public void saque(Long id, Double valor) {
        Conta conta = findById(id);
        if (conta.getSaldo().compareTo(BigDecimal.valueOf(valor)) < 0) {
            throw new ContaSaldoInsuficienteException("Saldo insuficiente");
        }

        Conta contaAfterSaque = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(conta.getSaldo().subtract(BigDecimal.valueOf(valor)))
                .build();
        contaRepository.save(contaAfterSaque);
    }

    @Transactional
    public void transferenciaEntrada(Long id, Double valor) {
        Conta conta = findById(id);
        BigDecimal saldo = conta.getSaldo().add(BigDecimal.valueOf(valor));
        Conta contaAfterTransferenciaEntrada = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(saldo)
                .build();
        contaRepository.save(contaAfterTransferenciaEntrada);
    }

    @Transactional
    public void transferenciaSaida(Long id, Double valor) {
        Conta conta = findById(id);
        if (conta.getSaldo().compareTo(BigDecimal.valueOf(valor)) < 0) {
            throw new ContaSaldoInsuficienteException("Saldo insuficiente");
        }

        Conta contaAfterTransferenciaSaida = Conta.builder()
                .id(conta.getId())
                .nomeResponsavel(conta.getNomeResponsavel())
                .saldo(conta.getSaldo().subtract(BigDecimal.valueOf(valor)))
                .build();
        contaRepository.save(contaAfterTransferenciaSaida);
    }
}
