package com.rafaela.streaming.api.dto;

import java.math.BigDecimal;

public record TransacaoRequest(Long contaId, BigDecimal valor, String comerciante) {}
