package com.mega.erp.modules.stock.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationReason {

    COMPRA("Entrada por Compra / NF-e", MovementType.ENTRADA),
    DEVOLUCAO_CLIENTE("Devolução de Cliente", MovementType.ENTRADA),
    AJUSTE_INVENTARIO_ENTRADA("Ajuste de Inventário (Positivo)", MovementType.ENTRADA),

    VENDA("Saída por Venda / NFC-e", MovementType.SAIDA),
    PERDA_AVARIA("Perda ou Avaria", MovementType.SAIDA),
    TRANSFERENCIA_SAIDA("Transferência entre Filiais", MovementType.SAIDA),
    AJUSTE_INVENTARIO_SAIDA("Ajuste de Inventário (Negativo)", MovementType.SAIDA);

    private final String description;
    private final MovementType defaultMovementType;

    public boolean isValidFor(MovementType movementType) {
        return this.defaultMovementType == movementType;
    }
}