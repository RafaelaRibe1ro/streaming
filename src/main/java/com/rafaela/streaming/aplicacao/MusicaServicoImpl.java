package com.rafaela.streaming.aplicacao;

import com.rafaela.streaming.dominio.musica.Musica;
import com.rafaela.streaming.dominio.musica.MusicaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MusicaServicoImpl {

    private final MusicaRepositorio musicaRepositorio;

    public MusicaServicoImpl(MusicaRepositorio musicaRepositorio) {
        this.musicaRepositorio = musicaRepositorio;
    }

    public Musica registrarMusica(String titulo, String artista, int duracaoEmSegundos) {
        return musicaRepositorio.save(new Musica(titulo, artista, duracaoEmSegundos));
    }

    public List<Musica> listarMusicas() {
        return musicaRepositorio.findAll();
    }

    public Musica buscarPorId(Long id) {
        return musicaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Música não encontrada"));
    }
}
