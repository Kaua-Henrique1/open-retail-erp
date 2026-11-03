package com.mega.erp.modules.stock.dto.preview;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "Resposta do Preview da NF-e para conferência do operador")
public class XmlPreviewResponseDTO {

    @Schema(description = "Chave de acesso da NF-e (44 dígitos)", example = "35231000000000000000550010000000011000000000")
    private String originDocument;

    @Schema(description = "CNPJ do Emitente / Fornecedor", example = "12345678000195")
    private String supplierCnpj;

    @Schema(description = "Razão Social do Fornecedor", example = "Distribuidora de Alimentos LTDA")
    private String supplierName;

    @Schema(description = "Indica se todos os itens da nota foram localizados no banco")
    private boolean readyForConfirmation;

    private List<XmlPreviewItemDTO> items;
}