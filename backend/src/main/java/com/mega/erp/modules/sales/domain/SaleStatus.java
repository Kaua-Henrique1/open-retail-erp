package com.mega.erp.modules.sales.domain;

public enum SaleStatus {
    PENDING,  // Aguardando pagamento
    PAID,     // Pago
    CANCELED, // Cancelado
    REFUNDED  // Reembolsado
}