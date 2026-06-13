package com.rafaela.streaming.aplicacao;

import com.rafaela.streaming.dominio.conta.Conta;
import com.rafaela.streaming.dominio.conta.ContaRepositorio;
import com.rafaela.streaming.dominio.conta.PlanoAssinatura;
import com.rafaela.streaming.dominio.musica.Musica;
import com.rafaela.streaming.dominio.musica.MusicaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContaServicoImpl implements ContaServico {

    private final ContaRepositorio contaRepositorio;
    private final MusicaRepositorio musicaRepositorio;

    public ContaServicoImpl(ContaRepositorio contaRepositorio, MusicaRepositorio musicaRepositorio) {
        this.contaRepositorio = contaRepositorio;
        this.musicaRepositorio = musicaRepositorio;
    }

    @Override
    public Conta criarConta(String email, String nome) {
        contaRepositorio.findByEmail(email).ifPresent(c -> {
            throw new IllegalArgumentException("Já existe uma conta com este email");
        });
        return contaRepositorio.save(new Conta(email, nome));
    }

    @Override
    public Conta adicionarCartaoCredito(Long contaId, String numero, String validade) {
        Conta conta = buscarContaOuLancarErro(contaId);
        conta.adicionarCartaoCredito(numero, validade);
        return contaRepositorio.save(conta);
    }

    @Override
    public Conta ativarAssinatura(Long contaId, PlanoAssinatura plano) {
        Conta conta = buscarContaOuLancarErro(contaId);
        if (!conta.temCartaoValido()) {
            throw new IllegalStateException("Conta não possui cartão de crédito válido para assinar");
        }
        conta.ativarAssinatura(plano);
        return contaRepositorio.save(conta);
    }

    @Override
    public Conta cancelarAssinatura(Long contaId) {
        Conta conta = buscarContaOuLancarErro(contaId);
        conta.cancelarAssinatura();
        return contaRepositorio.save(conta);
    }

    @Override
    public Conta favoritar(Long contaId, Long musicaId) {
        Conta conta = buscarContaOuLancarErro(contaId);
        Musica musica = musicaRepositorio.findById(musicaId)
                .orElseThrow(() -> new IllegalArgumentException("Música não encontrada"));
        conta.favoritar(musica);
        return contaRepositorio.save(conta);
    }

    @Override
    public List<Musica> listarFavoritas(Long contaId) {
        return buscarContaOuLancarErro(contaId).getMusicasFavoritas();
    }

    @Override
    public Conta buscarPorId(Long id) {
        return buscarContaOuLancarErro(id);
    }

    private Conta buscarContaOuLancarErro(Long id) {
        return contaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }
}
