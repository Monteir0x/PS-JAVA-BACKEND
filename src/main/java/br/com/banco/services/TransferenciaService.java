package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.repositories.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;

    public List<Transferencia> listAllByParamsOrNone(
            Long contaId,
            String nomeOperadorTransacao,
            LocalDate dataInicial,
            LocalDate dataFinal
    ) {
        return transferenciaRepository.findAllTransferenciasByCriteriasOrNone(
                contaId,
                nomeOperadorTransacao,
                dataInicial,
                dataFinal
        );
    }

}
