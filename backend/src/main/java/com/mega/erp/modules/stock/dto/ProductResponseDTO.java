package com.mega.erp.modules.stock.dto;

import com.mega.erp.modules.stock.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Dados essenciais do produto para o carrinho do PDV")
public record ProductResponseDTO(
        @Schema(example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(example = "Camiseta Algodão Premium M")
        String name,
        @Schema(example = "89.90")
        BigDecimal price,
        @Schema(example = "7891234567890")
        String ean
) {

    public static ProductResponseDTO from(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getEan()
        );
    }
}
