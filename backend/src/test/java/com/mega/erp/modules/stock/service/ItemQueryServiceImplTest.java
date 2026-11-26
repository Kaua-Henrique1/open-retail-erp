package com.mega.erp.modules.stock.service;

import com.mega.erp.modules.stock.domain.Item;
import com.mega.erp.modules.stock.dto.ProductResponseDTO;
import com.mega.erp.modules.stock.repository.ItemQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemQueryServiceImplTest {

    @Mock
    private ItemQueryRepository itemQueryRepository;

    @InjectMocks
    private ItemQueryServiceImpl itemQueryService;

    @Test
    void getProductByEanReturnsMappedProductWhenItemExists() {
        String ean = "7891234567890";
        UUID id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        BigDecimal price = new BigDecimal("19.9000");
        Item item = new Item();
        item.setId(id);
        item.setName("Café torrado 500 g");
        item.setBarcode(ean);
        item.setDefaultSalePrice(price);
        when(itemQueryRepository.findByCodigoBarras(ean)).thenReturn(Optional.of(item));

        ProductResponseDTO result = itemQueryService.getProductByEan(ean);

        assertEquals(new ProductResponseDTO(id, "Café torrado 500 g", ean, price), result);
        verify(itemQueryRepository, times(1)).findByCodigoBarras(ean);
    }

    @Test
    void getProductByEanThrowsWhenItemDoesNotExist() {
        String ean = "7891234567890";
        when(itemQueryRepository.findByCodigoBarras(ean)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> itemQueryService.getProductByEan(ean));
        verify(itemQueryRepository, times(1)).findByCodigoBarras(ean);
    }
}
