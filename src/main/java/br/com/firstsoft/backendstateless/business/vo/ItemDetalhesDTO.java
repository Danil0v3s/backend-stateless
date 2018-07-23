package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDetalhesDTO {

    @JsonProperty("Aliquota")
    private String aliquota;

    @JsonProperty("BasedeCalculo")
    private String baseCalculo;

    @JsonProperty("CFOP")
    private String cfop;

    @JsonProperty("CodigoNCM")
    private String codigoNCM;

    @JsonProperty("Codigodoproduto")
    private String codigoProduto;

    @JsonProperty("UnidadeComercial")
    private String unidadeComercial;

    @JsonProperty("Valor")
    private String valor;

    @JsonProperty("ValorAproximadodosTributos")
    private String valorTributos;

}
