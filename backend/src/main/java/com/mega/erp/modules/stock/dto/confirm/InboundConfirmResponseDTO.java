package com.mega.erp.modules.stock.dto.confirm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "Resumo da efetivação da entrada de estoque")
public class InboundConfirmResponseDTO {

    @Schema(description = "Quantidade de itens processados com sucesso")
    private int totalItemsProcessed;

    @Schema(description = "Documento de origem registrado")
    private String originDocument;

    @Schema(description = "Data e hora exata do lançamento no Kardex")
    private LocalDateTime timestamp;

    @Schema(description = "Mensagem de confirmação")
    private String message;
}