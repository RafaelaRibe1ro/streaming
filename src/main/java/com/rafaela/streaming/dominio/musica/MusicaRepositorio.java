package com.rafaela.streaming.dominio.musica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepositorio extends JpaRepository<Musica, Long> {
}
