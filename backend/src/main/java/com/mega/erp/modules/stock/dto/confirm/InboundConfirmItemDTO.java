package com.mega.erp.modules.stock.dto.confirm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Item conferido e validado para entrada no estoque")
public class InboundConfirmItemDTO {

    @NotNull(message = "O ID do item cadastrado é obrigatório")
    @Schema(description = "UUID do Item no banco de dados")
    private UUID itemId;

    @NotNull(message = "A quantidade física conferida é obrigatória")
    @Positive(message = "A quantidade conferida deve ser maior que zero")
    @Schema(description = "Quantidade física real contada pelo operador", example = "10.000")
    private BigDecimal quantityConfirmed;

    @NotNull(message = "O valor unitário de compra é obrigatório")
    @Positive(message = "O valor unitário deve ser positivo")
    @Schema(description = "Custo unitário do item nesta operação", example = "45.50")
    private BigDecimal unitValue;

    @Schema(description = "Valor total de impostos rateados para este item (IPI, ICMS ST, etc.)")
    private BigDecimal apportionedTaxes;

    @Schema(description = "UUID do Lote (caso o produto seja controlado por lote)")
    private UUID batchId;
}