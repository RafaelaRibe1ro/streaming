package com.rafaela.streaming.dominio.transacao;

import java.util.List;

import com.rafaela.streaming.dominio.conta.Conta;

/**
 * Interface de estratégia para regras de antifraude.
 */
public interface RegraAntifraude {
    List<Violacao> verificar(Conta conta, Transacao transacaoSolicitada, List<Transacao> historicoRecente);
}
