package com.mega.erp.modules.stock.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UnidadeMedida {

    UN("Unidade", false),
    PC("Peça", false),
    CX("Caixa", false),
    PCT("Pacote", false),
    FD("Fardo", false),
    KG("Quilograma", true),
    G("Grama", true),
    L("Litro", true),
    ML("Mililitro", true),
    M("Metro", true),
    CM("Centímetro", true);

    private final String descricao;
    private final boolean permiteFracionamento;
}