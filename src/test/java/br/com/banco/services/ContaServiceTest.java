package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.exceptions.ContaSaldoInsuficienteException;
import br.com.banco.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
class ContaServiceTest {

    private ContaService underTest;
    @Autowired
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp(){
        underTest = new ContaService(contaRepository);

    }
    @Test
    @DisplayName("Deve retornar o saldo com o novo valor após o depósito")
    void itShouldReturnCheckIfNewAmountAfterDeposit() {
        // given
        Long id = 1L;
        Conta conta = Conta.builder()
                .id(id)
                .nomeResponsavel("João")
                .saldo(BigDecimal.valueOf(100.00))
                .build();
        contaRepository.save(conta);


        // when
        Conta expected = underTest.deposito(id, 100.00);

        // then

        Assertions.assertEquals(expected.getSaldo(), new BigDecimal("200.0"));
    }

    @Test
    @DisplayName("Deve retornar o saldo com o novo valor após o saque")
    void itShouldReturnNewAmountAfterWithdraw() {
        // given
        Long id = 1L;
        Conta conta = Conta.builder()
                .id(id)
                .nomeResponsavel("João")
                .saldo(BigDecimal.valueOf(100.00))
                .build();
        contaRepository.save(conta);

        // when
        Conta expected = underTest.saque(id, 50.00);
        //then
        Assertions.assertEquals(expected.getSaldo(), new BigDecimal("50.0"));
    }

    @Test
    @DisplayName("Deve lançar um ContaSaldoInsuficienteException quando o saldo for insuficiente para o saque")
    void itShouldCheckWhenContaSaldoInsuficienteException(){
        // given
        Long id = 1L;
        Conta conta = Conta.builder()
                .id(id)
                .nomeResponsavel("João")
                .saldo(BigDecimal.valueOf(100.00))
                .build();
        contaRepository.save(conta);
        // when and then
        Assertions.assertThrows(ContaSaldoInsuficienteException.class, () -> underTest.saque(id, 150.00));
    }
}