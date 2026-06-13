package com.rafaela.streaming.dominio.musica;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musicas")
@Getter
@NoArgsConstructor
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String artista;

    private int duracaoEmSegundos;

    public Musica(String titulo, String artista, int duracaoEmSegundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoEmSegundos = duracaoEmSegundos;
    }
}
