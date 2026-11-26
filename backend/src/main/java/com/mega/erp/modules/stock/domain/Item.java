package com.mega.erp.modules.stock.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item", indexes = {
        @Index(name = "idx_item_codigo_barras", columnList = "codigo_barras")
})
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "empresa_id", nullable = false)
    private UUID companyId;

    @Column(name = "categoria_id")
    private UUID categoryId;

    @Column(name = "tipo", nullable = false, length = 20)
    private String type;

    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "codigo_barras", length = 50)
    private String barcode;

    @Column(name = "nome", nullable = false, length = 150)
    private String name;

    @Column(name = "marca", length = 100)
    private String brand;

    @Column(name = "preco_venda_padrao", nullable = false, precision = 15, scale = 4)
    private BigDecimal defaultSalePrice;

    @Column(name = "custo_medio_ponderado", precision = 15, scale = 4)
    private BigDecimal averageCost;

    @Column(name = "controla_estoque")
    private Boolean controlsStock;

    @Column(name = "ncm", length = 10)
    private String ncmCode;

    @Column(name = "cest", length = 10)
    private String cestCode;

    @Column(name = "origem", length = 50)
    private String origin;

    @Column(name = "unidade_medida", length = 10)
    private String unitOfMeasure;

    @Column(name = "peso_bruto", precision = 10, scale = 3)
    private BigDecimal grossWeight;

    @Column(name = "estoque_minimo", precision = 10, scale = 3)
    private BigDecimal minimumStock;

    @Column(name = "estoque_maximo", precision = 10, scale = 3)
    private BigDecimal maximumStock;

    @Column(name = "dimensoes", columnDefinition = "jsonb")
    private String dimensions;
}
