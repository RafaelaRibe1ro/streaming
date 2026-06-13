package com.rafaela.streaming.dominio.transacao;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Violacao {

    CARTAO_NAO_ATIVO("cartao-nao-ativo"),
    ALTA_FREQUENCIA_PEQUENO_INTERVALO("alta-frequencia-pequeno-intervalo"),
    TRANSACAO_DUPLICADA("transacao-duplicada");

    private final String codigo;

    Violacao(String codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }
}
