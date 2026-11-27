package com.mega.erp.modules.stock.domain;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // TODO: Mapear como relacionamento quando a entidade Empresa for criada.
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
    private BigDecimal weightedAverageCost;

    @Column(name = "controla_estoque")
    private Boolean stockControlled;

    @Column(name = "controla_lote", nullable = false)
    private Boolean batchControlled;

    @Column(name = "ncm", length = 10)
    private String ncm;

    @Column(name = "cest", length = 10)
    private String cest;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem", length = 50)
    private OrigemMercadoria origin;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", length = 10)
    private UnidadeMedida unitOfMeasure;

    @Column(name = "peso_bruto", precision = 10, scale = 3)
    private BigDecimal grossWeight;

    @Column(name = "estoque_minimo", precision = 10, scale = 3)
    private BigDecimal minimumStock;

    @Column(name = "estoque_maximo", precision = 10, scale = 3)
    private BigDecimal maximumStock;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "dimensoes", columnDefinition = "jsonb")
    private JsonNode dimensions;
}
