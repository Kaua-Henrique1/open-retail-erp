package com.mega.erp.modules.reports.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mega.erp.modules.reports.domain.ReportCategory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SlowMovingProductsResponseDTO(
        @JsonProperty("dias_sem_giro") Integer diasSemGiro,
        @JsonProperty("produtos") List<SlowMovingItem> produtos
) {
    public record SlowMovingItem(
            @JsonProperty("produto_id") String produtoId,
            String nome,
            ReportCategory categoria,
            @JsonProperty("quantidade_estoque") Integer quantidadeEstoque,
            @JsonProperty("ultima_venda_em") LocalDateTime ultimaVendaEm,
            @JsonProperty("valor_imobilizado") BigDecimal valorImobilizado
    ) {}
}