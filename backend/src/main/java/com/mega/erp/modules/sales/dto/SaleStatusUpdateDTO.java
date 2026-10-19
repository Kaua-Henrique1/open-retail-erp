package com.mega.erp.modules.sales.dto;

import com.mega.erp.modules.sales.domain.SaleStatus;
import jakarta.validation.constraints.NotNull;

public record SaleStatusUpdateDTO(
        @NotNull(message = "O novo status é obrigatório")
        SaleStatus status
) {}