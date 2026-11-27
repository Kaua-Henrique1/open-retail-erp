package com.mega.erp.modules.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "nfeProc")
@Schema(description = "Modelo normalizado de dados relevantes da NF-e")
public class NfeXmlDTO {

    @JacksonXmlProperty(localName = "chaveAcesso")
    @Schema(description = "Chave de acesso da NF-e")
    private String chaveAcesso;

    @JacksonXmlProperty(localName = "cnpjEmitente")
    @Schema(description = "CNPJ do emitente")
    private String cnpjEmitente;

    @JacksonXmlProperty(localName = "nomeEmitente")
    @Schema(description = "Razão social do emitente")
    private String nomeEmitente;

    @Schema(description = "Lista normalizada de produtos da NF-e")
    private List<NfeProdXmlDTO> produtos = new ArrayList<>();

    @JacksonXmlProperty(localName = "NFe")
    @Schema(description = "Dados da NF-e")
    private NfeDTO nfe;

    @JacksonXmlProperty(localName = "protNFe")
    @Schema(description = "Dados do protocolo de autorização")
    private ProtNfeDTO protNfe;

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "Elemento NFe")
    public static class NfeDTO {

        @JacksonXmlProperty(localName = "infNFe")
        private InfNfeDTO infNFe;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "Elemento infNFe")
    public static class InfNfeDTO {

        @JacksonXmlProperty(localName = "Id", isAttribute = true)
        @Schema(description = "Atributo Id da NF-e")
        private String id;

        @JacksonXmlProperty(localName = "emit")
        private EmitenteDTO emitente;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "det")
        @Schema(description = "Lista de itens da NF-e")
        private List<DetDTO> detalhes = new ArrayList<>();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "Dados do emitente da NF-e")
    public static class EmitenteDTO {

        @JacksonXmlProperty(localName = "CNPJ")
        private String cnpj;

        @JacksonXmlProperty(localName = "xNome")
        private String nome;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "Elemento det de cada item")
    public static class DetDTO {

        @JacksonXmlProperty(localName = "prod")
        private NfeProdXmlDTO prod;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "Elemento protNFe")
    public static class ProtNfeDTO {

        @JacksonXmlProperty(localName = "infProt")
        private InfProtDTO infProt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "Informações do protocolo de autorização")
    public static class InfProtDTO {

        @JacksonXmlProperty(localName = "chNFe")
        @Schema(description = "Chave de acesso autorizada")
        private String chNFe;
    }
}