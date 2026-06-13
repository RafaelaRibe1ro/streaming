package com.rafaela.streaming.dominio.transacao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transacoes")
@Getter
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contaId;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String comerciante;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    private boolean aprovada;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "transacao_violacoes", joinColumns = @JoinColumn(name = "transacao_id"))
    @Column(name = "violacao")
    @Enumerated(EnumType.STRING)
    private List<Violacao> violacoes = new ArrayList<>();

    public Transacao(Long contaId, BigDecimal valor, String comerciante) {
        this.contaId = contaId;
        this.valor = valor;
        this.comerciante = comerciante;
        this.dataHora = LocalDateTime.now();
    }

    public void aprovar() {
        this.aprovada = true;
    }

    public void rejeitar(List<Violacao> violacoesEncontradas) {
        this.aprovada = false;
        this.violacoes = new ArrayList<>(violacoesEncontradas);
    }
}
