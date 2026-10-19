package com.mega.erp.modules.reports.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mega.erp.modules.reports.domain.ReportPeriod;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SalesSummaryResponseDTO(
        @JsonProperty("periodo") ReportPeriod periodo,
        @JsonProperty("data_inicio") LocalDateTime dataInicio,
        @JsonProperty("data_fim") LocalDateTime dataFim,
        @JsonProperty("indicadores") Indicadores indicadores
) {
    public record Indicadores(
            @JsonProperty("faturamento_total") BigDecimal faturamentoTotal,
            @JsonProperty("total_vendas") Integer totalVendas,
            @JsonProperty("ticket_medio") BigDecimal ticketMedio
    ) {}
}