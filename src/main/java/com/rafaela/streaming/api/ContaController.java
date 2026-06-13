package com.rafaela.streaming.api;

import com.rafaela.streaming.aplicacao.ContaServico;
import com.rafaela.streaming.api.dto.AssinaturaRequest;
import com.rafaela.streaming.api.dto.CartaoRequest;
import com.rafaela.streaming.api.dto.CriarContaRequest;
import com.rafaela.streaming.dominio.conta.Conta;
import com.rafaela.streaming.dominio.conta.PlanoAssinatura;
import com.rafaela.streaming.dominio.musica.Musica;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaServico contaServico;

    public ContaController(ContaServico contaServico) {
        this.contaServico = contaServico;
    }

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody CriarContaRequest request) {
        Conta conta = contaServico.criarConta(request.email(), request.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarConta(@PathVariable Long id) {
        return ResponseEntity.ok(contaServico.buscarPorId(id));
    }

    @PostMapping("/{id}/cartao")
    public ResponseEntity<Conta> adicionarCartao(@PathVariable Long id, @RequestBody CartaoRequest request) {
        Conta conta = contaServico.adicionarCartaoCredito(id, request.numero(), request.validade());
        return ResponseEntity.ok(conta);
    }

    @PostMapping("/{id}/assinatura")
    public ResponseEntity<Conta> ativarAssinatura(@PathVariable Long id, @RequestBody AssinaturaRequest request) {
        PlanoAssinatura plano = PlanoAssinatura.valueOf(request.plano().toUpperCase());
        Conta conta = contaServico.ativarAssinatura(id, plano);
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("/{id}/assinatura")
    public ResponseEntity<Conta> cancelarAssinatura(@PathVariable Long id) {
        Conta conta = contaServico.cancelarAssinatura(id);
        return ResponseEntity.ok(conta);
    }

    @PostMapping("/{id}/favoritas/{musicaId}")
    public ResponseEntity<Conta> favoritar(@PathVariable Long id, @PathVariable Long musicaId) {
        Conta conta = contaServico.favoritar(id, musicaId);
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/{id}/favoritas")
    public ResponseEntity<List<Musica>> listarFavoritas(@PathVariable Long id) {
        return ResponseEntity.ok(contaServico.listarFavoritas(id));
    }
}
