package com.rafaela.streaming.dominio.conta;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class CartaoCredito {

    private String numero;
    private String validade;
    private boolean ativo;

    public CartaoCredito(String numero, String validade) {
        this.numero = numero;
        this.validade = validade;
        this.ativo = true;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
