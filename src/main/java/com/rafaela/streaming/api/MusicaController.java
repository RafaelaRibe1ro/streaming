package com.rafaela.streaming.api;

import com.rafaela.streaming.aplicacao.MusicaServicoImpl;
import com.rafaela.streaming.api.dto.MusicaRequest;
import com.rafaela.streaming.dominio.musica.Musica;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaServicoImpl musicaServico;

    public MusicaController(MusicaServicoImpl musicaServico) {
        this.musicaServico = musicaServico;
    }

    @PostMapping
    public ResponseEntity<Musica> registrarMusica(@RequestBody MusicaRequest request) {
        Musica musica = musicaServico.registrarMusica(request.titulo(), request.artista(), request.duracaoEmSegundos());
        return ResponseEntity.status(HttpStatus.CREATED).body(musica);
    }

    @GetMapping
    public ResponseEntity<List<Musica>> listarMusicas() {
        return ResponseEntity.ok(musicaServico.listarMusicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarMusica(@PathVariable Long id) {
        return ResponseEntity.ok(musicaServico.buscarPorId(id));
    }
}
