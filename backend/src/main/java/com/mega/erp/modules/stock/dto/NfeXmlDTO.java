package com.mega.erp.modules.stock.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "nfeProc")
public class NfeXmlDTO {

    @JacksonXmlProperty(localName = "NFe")
    private NfeBodyXmlDto nfeBody;

    @Getter
    @Setter
    public static class NfeBodyXmlDto {
        @JacksonXmlProperty(localName = "infNFe")
        private InfNfeXmlDto infNfe;
    }

    @Getter
    @Setter
    public static class InfNfeXmlDto {
        @JacksonXmlProperty(isAttribute = true, localName = "Id")
        private String chaveNfe;

        @JacksonXmlProperty(localName = "emit")
        private SupplierXmlDto emitente;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "det")
        private List<NfeDetXmlDto> detalhes;
    }

    @Getter
    @Setter
    public static class SupplierXmlDto {
        @JacksonXmlProperty(localName = "CNPJ")
        private String cnpj;

        @JacksonXmlProperty(localName = "xNome")
        private String razaoSocial;
    }

    @Getter
    @Setter
    public static class NfeDetXmlDto {
        @JacksonXmlProperty(localName = "prod")
        private NfeProdXmlDTO produto;
    }
}