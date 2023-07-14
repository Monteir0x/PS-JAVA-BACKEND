package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

public interface TransferenciaRepository
        extends JpaRepository<Transferencia, Long>, JpaSpecificationExecutor<Transferencia> {

    default List<Transferencia> findAllTransferenciasByCriteriasOrNone(
            Long contaId,
            String nomeOperadorTransacao,
            LocalDate dataInicial,
            LocalDate dataFinal
    ) {
        return searchCriteria(contaId, nomeOperadorTransacao, dataInicial, dataFinal);
    }

    private List<Transferencia> searchCriteria(
            Long contaId,
            String nomeOperadorTransacao,
            LocalDate dataInicial,
            LocalDate dataFinal
    ) {
        Specification<Transferencia> specs = Specification.where(null);
        if (contaId != null){
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("conta").get("id"), contaId));
        }
        if (nomeOperadorTransacao != null){
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("nomeOperadorTransacao"), nomeOperadorTransacao));
        }
        if (dataInicial != null){
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dataTransferencia"), dataInicial));
        }
        if (dataInicial != null && dataFinal != null){
            var dataInicialOffSet = dataInicial.atTime(LocalTime.MIN).atOffset(ZoneOffset.UTC);
            var dataFinalOffSet = dataFinal.atTime(LocalTime.MAX).atOffset(ZoneOffset.UTC);
            specs = specs.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("dataTransferencia"), dataInicialOffSet, dataFinalOffSet));
        }
        return findAll(specs);
    }
}
