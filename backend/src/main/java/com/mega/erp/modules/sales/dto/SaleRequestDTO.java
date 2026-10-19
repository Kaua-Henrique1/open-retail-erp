package com.mega.erp.modules.sales.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record SaleRequestDTO(
        UUID customerId, // Removi a obrigatoriedade (pode ser venda balcão sem cliente)

        @NotEmpty(message = "A venda deve conter pelo menos um produto")
        @Valid
        List<SaleItemDTO> items,

        @NotEmpty(message = "A venda deve conter pelo menos um pagamento")
        @Valid // Exige que o Spring valide os itens da lista de pagamento (valor positivo, etc)
        List<PaymentDTO> payments
) {}