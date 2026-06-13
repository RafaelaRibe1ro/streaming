package com.rafaela.streaming.dominio.transacao.regras;

import com.rafaela.streaming.dominio.conta.Conta;
import com.rafaela.streaming.dominio.transacao.RegraAntifraude;
import com.rafaela.streaming.dominio.transacao.Transacao;
import com.rafaela.streaming.dominio.transacao.Violacao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RegraAltaFrequencia implements RegraAntifraude {

    private static final int LIMITE_TRANSACOES = 3;
    private static final int INTERVALO_MINUTOS = 2;

    @Override
    public List<Violacao> verificar(Conta conta, Transacao transacaoSolicitada, List<Transacao> historicoRecente) {
        LocalDateTime limiteInferior = transacaoSolicitada.getDataHora().minusMinutes(INTERVALO_MINUTOS);

        long transacoesNoIntervalo = historicoRecente.stream()
                .filter(t -> t.getDataHora().isAfter(limiteInferior))
                .filter(Transacao::isAprovada)
                .count();

        if (transacoesNoIntervalo >= LIMITE_TRANSACOES) {
            return List.of(Violacao.ALTA_FREQUENCIA_PEQUENO_INTERVALO);
        }
        return List.of();
    }
}
