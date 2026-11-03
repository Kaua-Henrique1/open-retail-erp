package com.mega.erp.modules.stock.dto.confirm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Payload para efetivação do lançamento de estoque")
public class InboundConfirmRequestDTO {

    @NotNull(message = "A filial de destino é obrigatória")
    @Schema(description = "UUID da Filial onde o estoque será creditado")
    private UUID branchId;

    @Schema(description = "UUID da Pessoa/Fornecedor (Opcional se não houver cadastro)")
    private UUID supplierId;

    @NotBlank(message = "O documento de origem (chave da NF-e) é obrigatório")
    @Schema(description = "Chave da NF-e ou número do documento de compra")
    private String originDocument;

    @NotEmpty(message = "A lista de itens confirmados não pode estar vazia")
    @Valid
    private List<InboundConfirmItemDTO> items;
}