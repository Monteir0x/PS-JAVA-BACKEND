package br.com.banco.controllers;

import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService;

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getConta(@PathVariable Long id) {
        var conta = contaService.findById(id);
        return ResponseEntity.ok(conta);
    }
}
