package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.entities.dto.TransferenciaDto;
import br.com.banco.repositories.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;
    private final ContaService contaService;

    public List<Transferencia> listAllByParamsOrNone(

            String nomeOperadorTransacao,
            LocalDate dataInicial,
            LocalDate dataFinal
    ) {
        return transferenciaRepository.findAllTransferenciasByCriteriasOrNone(
                nomeOperadorTransacao,
                dataInicial,
                dataFinal
        );
    }

    @Transactional
    public Transferencia save(TransferenciaDto transferenciaDto){
        var conta = contaService.findById(transferenciaDto.getContaId());
        var transferencia = Transferencia.builder()
                .tipo(transferenciaDto.getTipo())
                .conta(conta)
                .valor(transferenciaDto.getValor())
                .nomeOperadorTransacao(transferenciaDto.getNomeOperadorTransacao())
                .build();
        return transferenciaRepository.save(transferencia);
    }
}