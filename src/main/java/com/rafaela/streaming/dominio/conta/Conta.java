package com.rafaela.streaming.dominio.conta;

import com.rafaela.streaming.dominio.musica.Musica;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contas")
@Getter
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private CartaoCredito cartaoCredito;

    @Enumerated(EnumType.STRING)
    private PlanoAssinatura planoAssinatura;

    private boolean assinaturaAtiva = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "conta_musica_favorita",
        joinColumns = @JoinColumn(name = "conta_id"),
        inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicasFavoritas = new ArrayList<>();

    public Conta(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public void adicionarCartaoCredito(String numero, String validade) {
        this.cartaoCredito = new CartaoCredito(numero, validade);
    }

    public void ativarAssinatura(PlanoAssinatura plano) {
        if (assinaturaAtiva) {
            throw new IllegalStateException("Conta já possui uma assinatura ativa");
        }
        this.planoAssinatura = plano;
        this.assinaturaAtiva = true;
    }

    public void cancelarAssinatura() {
        this.assinaturaAtiva = false;
    }

    public void favoritar(Musica musica) {
        if (!musicasFavoritas.contains(musica)) {
            musicasFavoritas.add(musica);
        }
    }

    public boolean temCartaoValido() {
        return cartaoCredito != null && cartaoCredito.isAtivo();
    }

    public boolean temAssinaturaAtiva() {
        return assinaturaAtiva;
    }
}
