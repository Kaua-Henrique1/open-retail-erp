package com.mega.erp.modules.stock.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String ean) {
        super("Produto não encontrado para o EAN: " + ean);
    }
}
