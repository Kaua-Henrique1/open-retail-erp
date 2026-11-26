package com.mega.erp.modules.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Dados mínimos do item retornados na busca do PDV por EAN")
public record ItemResponseDTO(
        @Schema(description = "Identificador do item", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Nome do item", example = "Café torrado 500 g")
        @JsonProperty("nome")
        String name,

        @Schema(description = "Código de barras EAN", example = "7891234567890")
        @JsonProperty("codigoBarras")
        String barcode,

        @Schema(description = "Preço padrão de venda", example = "19.9000")
        @JsonProperty("precoVendaPadrao")
        BigDecimal defaultSalePrice
) {
}
