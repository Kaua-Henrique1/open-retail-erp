package com.mega.erp.modules.stock.service;

import com.mega.erp.modules.stock.domain.Product;
import com.mega.erp.modules.stock.dto.ProductResponseDTO;
import com.mega.erp.modules.stock.exception.ProductNotFoundException;
import com.mega.erp.modules.stock.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findByEan(String ean) {
        Product product = productRepository.findByEan(ean)
                .orElseThrow(() -> new ProductNotFoundException(ean));

        return ProductResponseDTO.from(product);
    }
}
