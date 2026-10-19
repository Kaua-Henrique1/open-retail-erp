package com.mega.erp.modules.sales.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SaleItemDTO(
        @NotNull(message = "O ID do produto é obrigatório")
        UUID productId,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade mínima é 1")
        Integer quantity
) {}