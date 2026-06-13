package com.rafaela.streaming.aplicacao;

import com.rafaela.streaming.dominio.musica.Musica;
import com.rafaela.streaming.dominio.musica.MusicaRepositorio;
import com.rafaela.streaming.dominio.musica.Playlist;
import com.rafaela.streaming.dominio.musica.PlaylistRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaylistServicoImpl {

    private final PlaylistRepositorio playlistRepositorio;
    private final MusicaRepositorio musicaRepositorio;

    public PlaylistServicoImpl(PlaylistRepositorio playlistRepositorio, MusicaRepositorio musicaRepositorio) {
        this.playlistRepositorio = playlistRepositorio;
        this.musicaRepositorio = musicaRepositorio;
    }

    public Playlist criarPlaylist(Long contaId, String nome) {
        return playlistRepositorio.save(new Playlist(nome, contaId));
    }

    public Playlist adicionarMusicaAPlaylist(Long playlistId, Long musicaId) {
        Playlist playlist = buscarOuLancarErro(playlistId);
        Musica musica = musicaRepositorio.findById(musicaId)
                .orElseThrow(() -> new IllegalArgumentException("Música não encontrada"));
        playlist.adicionarMusica(musica);
        return playlistRepositorio.save(playlist);
    }

    public List<Playlist> listarPlaylists(Long contaId) {
        return playlistRepositorio.findByContaId(contaId);
    }

    public Playlist buscarPorId(Long id) {
        return buscarOuLancarErro(id);
    }

    private Playlist buscarOuLancarErro(Long id) {
        return playlistRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist não encontrada"));
    }
}
