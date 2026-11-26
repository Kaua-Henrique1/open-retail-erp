package com.mega.erp.modules.stock.ports.input;

import com.mega.erp.modules.stock.dto.ProductResponseDTO;
import jakarta.persistence.EntityNotFoundException;

public interface ItemQueryUseCase {

    /**
     * Busca um item pelo seu código de barras EAN.
     *
     * @throws EntityNotFoundException se nenhum item for encontrado para o EAN informado
     */
    ProductResponseDTO getProductByEan(String ean);
}
