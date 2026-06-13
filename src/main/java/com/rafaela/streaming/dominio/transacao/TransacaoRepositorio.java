package com.rafaela.streaming.dominio.transacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepositorio extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaId(Long contaId);
}
