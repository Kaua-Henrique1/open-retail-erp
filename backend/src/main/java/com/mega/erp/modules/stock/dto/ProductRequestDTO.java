package com.mega.erp.modules.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "Objeto de requisição para cadastro de novo produto")
public class ProductRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    @Schema(description = "Nome comercial do produto", example = "Camiseta Algodão Premium M")
    private String name;

    @Schema(description = "Código de barras no padrão EAN-13", example = "7891234567890")
    private String barcode;

    @NotNull(message = "O preço de venda é obrigatório")
    @Positive(message = "O preço de venda deve ser maior que zero")
    @Schema(description = "Preço unitário de venda em reais", example = "89.90")
    private BigDecimal price;

    @NotNull(message = "A quantidade inicial em estoque é obrigatória")
    @Schema(description = "Quantidade inicial em estoque", example = "50")
    private Integer quantity;

    @Schema(description = "Quantidade mínima para alerta de reposição", example = "5")
    private Integer minQuantity;

    @Schema(description = "Categoria do produto", example = "Vestuário")
    private String category;
}