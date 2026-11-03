package com.mega.erp.modules.stock.ports.output;

import java.util.List;

public interface ItemQueryPort {
    List<ItemStockProjection> findByBarcodesIn(List<String> barcodes);
}