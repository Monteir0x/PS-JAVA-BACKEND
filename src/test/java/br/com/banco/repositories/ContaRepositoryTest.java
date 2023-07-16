package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import br.com.banco.exceptions.ContaNaoEncontradaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


@DataJpaTest
class ContaRepositoryTest {

    @Autowired
    private ContaRepository underTest;

    @Test
    @DisplayName("Deve retornar uma Conta quando passado um id existente")
    void itShouldReturnContaWhenIdExists() {
        // given
        Long id = 1L;
        Conta conta = Conta.builder()
                .id(id)
                .nomeResponsavel("João")
                .build();
        underTest.save(conta);
        // when
        Conta expected = underTest.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
        // then
        assertThat(expected).isInstanceOf(Conta.class);
    }


    @Test
    @DisplayName("Deve lançar um ContaNotFoundException quando passado um id inexistente")
    void itShouldCheckWhenContaNotExists(){
        // given
        Long id = 3L;
        Conta conta = Conta.builder()
                .id(1L)
                .nomeResponsavel("João")
                .build();
        underTest.save(conta);
        // when and then
        assertThatExceptionOfType(ContaNaoEncontradaException.class)
                .isThrownBy(() -> underTest.findById(id)
                        .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada")
                                )).withMessage("Conta não encontrada")
                .withNoCause();
    }
}