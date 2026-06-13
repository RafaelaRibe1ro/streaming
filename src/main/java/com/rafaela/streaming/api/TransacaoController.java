package com.rafaela.streaming.api;

import com.rafaela.streaming.aplicacao.TransacaoServico;
import com.rafaela.streaming.api.dto.TransacaoRequest;
import com.rafaela.streaming.dominio.transacao.Transacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoServico transacaoServico;

    public TransacaoController(TransacaoServico transacaoServico) {
        this.transacaoServico = transacaoServico;
    }

    @PostMapping
    public ResponseEntity<Transacao> autorizarTransacao(@RequestBody TransacaoRequest request) {
        Transacao transacao = transacaoServico.autorizarTransacao(
                request.contaId(), request.valor(), request.comerciante());

        HttpStatus status = transacao.isAprovada() ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status).body(transacao);
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTransacoes(@RequestParam Long contaId) {
        return ResponseEntity.ok(transacaoServico.listarTransacoes(contaId));
    }
}
