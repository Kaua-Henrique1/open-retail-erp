package com.mega.erp.modules.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Dados do elemento prod de um item da NF-e")
public class NfeProdXmlDTO {

    @JacksonXmlProperty(localName = "cEAN")
    @Schema(description = "Código de barras GTIN do produto")
    private String barcode;

    @JacksonXmlProperty(localName = "xProd")
    @Schema(description = "Descrição comercial do produto")
    private String name;

    @JacksonXmlProperty(localName = "NCM")
    @Schema(description = "Nomenclatura Comum do Mercosul")
    private String ncm;

    @JacksonXmlProperty(localName = "uCom")
    @Schema(description = "Unidade comercial")
    private String unitOfMeasure;

    @JacksonXmlProperty(localName = "qCom")
    @Schema(description = "Quantidade comercial")
    private BigDecimal quantity;

    @JacksonXmlProperty(localName = "vUnCom")
    @Schema(description = "Valor unitário comercial")
    private BigDecimal unitValue;

    @JacksonXmlProperty(localName = "vProd")
    @Schema(description = "Valor total bruto do produto")
    private BigDecimal totalValue;
}