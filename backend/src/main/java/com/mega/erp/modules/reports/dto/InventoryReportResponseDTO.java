package com.mega.erp.modules.reports.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mega.erp.modules.reports.domain.ReportCategory;
import com.mega.erp.modules.reports.domain.StockStatus;
import java.util.List;

public record InventoryReportResponseDTO(
        @JsonProperty("filtros_aplicados") FiltrosAplicados filtrosAplicados,
        @JsonProperty("resumo") Resumo resumo,
        @JsonProperty("itens") List<InventoryItem> itens,
        @JsonProperty("paginacao") Paginacao paginacao
) {
    public record FiltrosAplicados(ReportCategory categoria, @JsonProperty("status_estoque") StockStatus statusEstoque) {}
    public record Resumo(@JsonProperty("total_itens_alerta") Integer totalItensAlerta) {}
    public record InventoryItem(
            @JsonProperty("produto_id") String produtoId,
            String nome,
            ReportCategory categoria,
            @JsonProperty("quantidade_atual") Integer quantidadeAtual,
            @JsonProperty("quantidade_minima") Integer quantidadeMinima,
            @JsonProperty("alerta_reposicao") Boolean alertaReposicao
    ) {}
    public record Paginacao(
            @JsonProperty("pagina_atual") Integer paginaAtual,
            @JsonProperty("total_paginas") Integer totalPaginas,
            @JsonProperty("total_registros") Long totalRegistros
    ) {}
}