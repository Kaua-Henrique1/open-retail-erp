package com.mega.erp.modules.stock.controller;

import com.mega.erp.modules.stock.dto.ProductResponseDTO;
import com.mega.erp.modules.stock.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void shouldReturnProductResponseWhenEanIsFound() {
        String ean = "7891234567890";
        ProductResponseDTO response = new ProductResponseDTO(
                UUID.randomUUID(),
                "Camiseta Algodão Premium M",
                new BigDecimal("89.90"),
                ean
        );
        when(productService.findByEan(ean)).thenReturn(response);

        var result = productController.getByEan(ean);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(productService).findByEan(ean);
    }
}
