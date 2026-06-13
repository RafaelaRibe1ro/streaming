package com.rafaela.streaming.dominio.musica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepositorio extends JpaRepository<Playlist, Long> {
    List<Playlist> findByContaId(Long contaId);
}
