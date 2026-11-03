package com.mega.erp.modules.stock.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class NfeProdXmlDTO {

    @JacksonXmlProperty(localName = "cEAN")
    private String barcode;

    @JacksonXmlProperty(localName = "xProd")
    private String name;

    @JacksonXmlProperty(localName = "NCM")
    private String ncm;

    @JacksonXmlProperty(localName = "uCom")
    private String unitOfMeasure;

    @JacksonXmlProperty(localName = "qCom")
    private BigDecimal quantity;

    @JacksonXmlProperty(localName = "vUnCom")
    private BigDecimal unitValue;

    @JacksonXmlProperty(localName = "vProd")
    private BigDecimal totalValue;
}