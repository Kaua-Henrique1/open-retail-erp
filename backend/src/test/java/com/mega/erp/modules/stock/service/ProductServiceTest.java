package com.mega.erp.modules.stock.service;

import com.mega.erp.modules.stock.domain.Product;
import com.mega.erp.modules.stock.dto.ProductResponseDTO;
import com.mega.erp.modules.stock.exception.ProductNotFoundException;
import com.mega.erp.modules.stock.repository.ProductRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductResponseWhenEanExists() {
        String ean = "7891234567890";
        UUID id = UUID.randomUUID();
        Product product = org.mockito.Mockito.mock(Product.class);
        when(product.getId()).thenReturn(id);
        when(product.getName()).thenReturn("Camiseta Algodão Premium M");
        when(product.getPrice()).thenReturn(new BigDecimal("89.9000"));
        when(product.getEan()).thenReturn(ean);
        when(productRepository.findByEan(ean)).thenReturn(Optional.of(product));

        ProductResponseDTO response = productService.findByEan(ean);

        assertEquals(id, response.id());
        assertEquals("Camiseta Algodão Premium M", response.name());
        assertEquals(new BigDecimal("89.9000"), response.price());
        assertEquals(ean, response.ean());
        verify(productRepository).findByEan(ean);
    }

    @Test
    void shouldThrowProductNotFoundWhenEanDoesNotExist() {
        String ean = "7891234567890";
        when(productRepository.findByEan(ean)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findByEan(ean));
        verify(productRepository).findByEan(ean);
    }
}
