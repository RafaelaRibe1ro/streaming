package com.rafaela.streaming.dominio.transacao;

import com.rafaela.streaming.dominio.conta.Conta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoAntifraude {

    private final List<RegraAntifraude> regras;

    public ServicoAntifraude(List<RegraAntifraude> regras) {
        this.regras = regras;
    }

    public List<Violacao> verificar(Conta conta, Transacao transacao, List<Transacao> historicoRecente) {
        return regras.stream()
                .flatMap(regra -> regra.verificar(conta, transacao, historicoRecente).stream())
                .collect(Collectors.toList());
    }
}
