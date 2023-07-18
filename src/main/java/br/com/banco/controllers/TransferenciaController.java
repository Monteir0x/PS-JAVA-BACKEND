package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.entities.dto.TransferenciaDto;
import br.com.banco.services.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencias")
@RequiredArgsConstructor
public class TransferenciaController {
    private final TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<List<Transferencia>> allByParamsOrNone(
            @RequestParam(value = "nomeOperadorTransacao", required = false) String nomeOperadorTransacao,
            @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicial,
            @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFinal
            ){
        var transferencias = transferenciaService.listAllByParamsOrNone(
                nomeOperadorTransacao,
                dataInicial,
                dataFinal
        );
        return ResponseEntity.ok(transferencias);
    }

    @PostMapping
    public ResponseEntity<Transferencia> save(@RequestBody TransferenciaDto transferenciaDto){
        var transferencia = transferenciaService.save(transferenciaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
    }
}
