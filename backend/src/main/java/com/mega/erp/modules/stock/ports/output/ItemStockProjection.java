package com.mega.erp.modules.stock.ports.output;

import java.util.UUID;

public record ItemStockProjection(
        UUID id,
        String codigoBarras,
        String nome,
        String sku,
        boolean controlaEstoque
) {}