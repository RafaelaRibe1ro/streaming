package com.rafaela.streaming.dominio.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepositorio extends JpaRepository<Conta, Long> {
    Optional<Conta> findByEmail(String email);
}
