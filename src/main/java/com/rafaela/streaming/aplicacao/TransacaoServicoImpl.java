package com.rafaela.streaming.aplicacao;

import com.rafaela.streaming.dominio.conta.Conta;
import com.rafaela.streaming.dominio.conta.ContaRepositorio;
import com.rafaela.streaming.dominio.transacao.ServicoAntifraude;
import com.rafaela.streaming.dominio.transacao.Transacao;
import com.rafaela.streaming.dominio.transacao.TransacaoRepositorio;
import com.rafaela.streaming.dominio.transacao.Violacao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransacaoServicoImpl implements TransacaoServico {

    private final TransacaoRepositorio transacaoRepositorio;
    private final ContaRepositorio contaRepositorio;
    private final ServicoAntifraude servicoAntifraude;

    public TransacaoServicoImpl(TransacaoRepositorio transacaoRepositorio,
                                 ContaRepositorio contaRepositorio,
                                 ServicoAntifraude servicoAntifraude) {
        this.transacaoRepositorio = transacaoRepositorio;
        this.contaRepositorio = contaRepositorio;
        this.servicoAntifraude = servicoAntifraude;
    }

    @Override
    public Transacao autorizarTransacao(Long contaId, BigDecimal valor, String comerciante) {
        Conta conta = contaRepositorio.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        Transacao transacao = new Transacao(contaId, valor, comerciante);

        List<Transacao> historico = transacaoRepositorio.findByContaId(contaId);
        List<Violacao> violacoes = servicoAntifraude.verificar(conta, transacao, historico);

        if (violacoes.isEmpty()) {
            transacao.aprovar();
        } else {
            transacao.rejeitar(violacoes);
        }

        return transacaoRepositorio.save(transacao);
    }

    @Override
    public List<Transacao> listarTransacoes(Long contaId) {
        return transacaoRepositorio.findByContaId(contaId);
    }
}
