package com.rafaela.streaming.dominio.transacao.regras;

import com.rafaela.streaming.dominio.conta.Conta;
import com.rafaela.streaming.dominio.transacao.RegraAntifraude;
import com.rafaela.streaming.dominio.transacao.Transacao;
import com.rafaela.streaming.dominio.transacao.Violacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegraCartaoAtivo implements RegraAntifraude {

    @Override
    public List<Violacao> verificar(Conta conta, Transacao transacaoSolicitada, List<Transacao> historicoRecente) {
        if (!conta.temCartaoValido()) {
            return List.of(Violacao.CARTAO_NAO_ATIVO);
        }
        return List.of();
    }
}
