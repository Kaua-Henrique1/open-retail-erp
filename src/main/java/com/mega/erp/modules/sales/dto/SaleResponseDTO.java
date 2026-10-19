package com.mega.erp.modules.sales.dto;

import com.mega.erp.modules.sales.domain.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SaleResponseDTO(
        UUID id,
        UUID customerId,
        List<SaleItemDTO> items,
        List<PaymentDTO> payments,
        BigDecimal totalAmount,
        SaleStatus status,
        LocalDateTime createdAt
) {}