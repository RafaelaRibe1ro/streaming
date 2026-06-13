package com.rafaela.streaming.api;

import com.rafaela.streaming.aplicacao.PlaylistServicoImpl;
import com.rafaela.streaming.api.dto.PlaylistRequest;
import com.rafaela.streaming.dominio.musica.Playlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaylistController {

    private final PlaylistServicoImpl playlistServico;

    public PlaylistController(PlaylistServicoImpl playlistServico) {
        this.playlistServico = playlistServico;
    }

    @PostMapping("/contas/{contaId}/playlists")
    public ResponseEntity<Playlist> criarPlaylist(@PathVariable Long contaId, @RequestBody PlaylistRequest request) {
        Playlist playlist = playlistServico.criarPlaylist(contaId, request.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
    }

    @GetMapping("/contas/{contaId}/playlists")
    public ResponseEntity<List<Playlist>> listarPlaylists(@PathVariable Long contaId) {
        return ResponseEntity.ok(playlistServico.listarPlaylists(contaId));
    }

    @PostMapping("/playlists/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Playlist> adicionarMusica(@PathVariable Long playlistId, @PathVariable Long musicaId) {
        Playlist playlist = playlistServico.adicionarMusicaAPlaylist(playlistId, musicaId);
        return ResponseEntity.ok(playlist);
    }
}
