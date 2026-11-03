package com.mega.erp.modules.stock.dto.preview;

import com.mega.erp.modules.stock.domain.InboundMatchStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@Schema(description = "Item extraído da NF-e cruzado com a base de dados local")
public class XmlPreviewItemDTO {

    @Schema(description = "UUID do Item cadastrado no sistema (null se não encontrado)")
    private UUID systemItemId;

    @Schema(description = "Código de barras lido no XML", example = "7891234567890")
    private String barcode;

    @Schema(description = "Nome do produto conforme consta na NF-e")
    private String xmlProductName;

    @Schema(description = "Nome do produto conforme cadastrado no sistema local")
    private String systemProductName;

    @Schema(description = "Quantidade faturada na nota fiscal")
    private BigDecimal xmlQuantity;

    @Schema(description = "Valor unitário de compra faturado na nota")
    private BigDecimal xmlUnitValue;

    @Schema(description = "Status de localização do item na base local")
    private InboundMatchStatus matchStatus;
}