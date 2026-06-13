package com.rafaela.streaming.aplicacao;

import com.rafaela.streaming.dominio.transacao.Transacao;

import java.math.BigDecimal;
import java.util.List;

public interface TransacaoServico {

    Transacao autorizarTransacao(Long contaId, BigDecimal valor, String comerciante);

    List<Transacao> listarTransacoes(Long contaId);
}
