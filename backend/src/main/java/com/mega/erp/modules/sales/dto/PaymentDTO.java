package com.mega.erp.modules.sales.dto;

import com.mega.erp.modules.sales.domain.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PaymentDTO(
        @NotNull(message = "O método de pagamento é obrigatório")
        PaymentMethod method,

        @NotNull(message = "O valor do pagamento é obrigatório")
        @Positive(message = "O valor do pagamento deve ser maior que zero")
        BigDecimal amount
) {}