package com.rafaela.streaming.aplicacao;

import com.rafaela.streaming.dominio.conta.Conta;
import com.rafaela.streaming.dominio.conta.PlanoAssinatura;
import com.rafaela.streaming.dominio.musica.Musica;

import java.util.List;

public interface ContaServico {

    Conta criarConta(String email, String nome);

    Conta adicionarCartaoCredito(Long contaId, String numero, String validade);

    Conta ativarAssinatura(Long contaId, PlanoAssinatura plano);

    Conta cancelarAssinatura(Long contaId);

    Conta favoritar(Long contaId, Long musicaId);

    List<Musica> listarFavoritas(Long contaId);

    Conta buscarPorId(Long id);
}
