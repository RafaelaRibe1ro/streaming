package com.rafaela.streaming.dominio.transacao.regras;

import com.rafaela.streaming.dominio.conta.Conta;
import com.rafaela.streaming.dominio.transacao.RegraAntifraude;
import com.rafaela.streaming.dominio.transacao.Transacao;
import com.rafaela.streaming.dominio.transacao.Violacao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RegraTransacaoDuplicada implements RegraAntifraude {

    private static final int LIMITE_SEMELHANTES = 2;
    private static final int INTERVALO_MINUTOS = 2;

    @Override
    public List<Violacao> verificar(Conta conta, Transacao transacaoSolicitada, List<Transacao> historicoRecente) {
        LocalDateTime limiteInferior = transacaoSolicitada.getDataHora().minusMinutes(INTERVALO_MINUTOS);

        long transacoesSemelhantes = historicoRecente.stream()
                .filter(t -> t.getDataHora().isAfter(limiteInferior))
                .filter(Transacao::isAprovada)
                .filter(t -> t.getValor().compareTo(transacaoSolicitada.getValor()) == 0)
                .filter(t -> t.getComerciante().equalsIgnoreCase(transacaoSolicitada.getComerciante()))
                .count();

        if (transacoesSemelhantes >= LIMITE_SEMELHANTES) {
            return List.of(Violacao.TRANSACAO_DUPLICADA);
        }
        return List.of();
    }
}
