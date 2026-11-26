package com.mega.erp.modules.stock.service;

import com.mega.erp.modules.stock.domain.Item;
import com.mega.erp.modules.stock.dto.ProductResponseDTO;
import com.mega.erp.modules.stock.ports.input.ItemQueryUseCase;
import com.mega.erp.modules.stock.repository.ItemQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemQueryServiceImpl implements ItemQueryUseCase {

    private final ItemQueryRepository itemQueryRepository;

    public ItemQueryServiceImpl(ItemQueryRepository itemQueryRepository) {
        this.itemQueryRepository = itemQueryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductByEan(String ean) {
        Item item = itemQueryRepository.findByCodigoBarras(ean)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return new ProductResponseDTO(
                item.getId(),
                item.getName(),
                item.getBarcode(),
                item.getDefaultSalePrice()
        );
    }
}
