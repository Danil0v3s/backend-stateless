package br.com.firstsoft.backendstateless.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDetalhesDTO {

    @JsonProperty("Aliquota")
    private String Aliquota;

    @JsonProperty("BasedeCalculo")
    private String BasedeCalculo;

    @JsonProperty("CFOP")
    private String CFOP;

    @JsonProperty("CodificacaoNVE")
    private String CodificacaoNVE;

    @JsonProperty("CodigoEANComercial")
    private String CodigoEANComercial;

    @JsonProperty("CodigoEANTributavel")
    private String CodigoEANTributavel;

    @JsonProperty("CodigoEXdaTIP")
    private String CodigoEXdaTIP;

    @JsonProperty("CodigoNCM")
    private String CodigoNCM;

    @JsonProperty("CodigodeSituacaoTributaria")
    private String CodigodeSituacaoTributaria;

    @JsonProperty("Codigodoproduto")
    private String Codigodoproduto;

    @JsonProperty("Descricao")
    private String Descricao;

    @JsonProperty("Genero")
    private String Genero;

    @JsonProperty("IndicadordeComposicaodoValorTotaldaNFe")
    private String IndicadordeComposicaodoValorTotaldaNFe;

    @JsonProperty("Itemdopedidodecompra")
    private String Itemdopedidodecompra;

    @JsonProperty("NumerodaFCI")
    private String NumerodaFCI;

    @JsonProperty("Numerodopedidodecompra")
    private String Numerodopedidodecompra;

    @JsonProperty("OrigemdaMercadoria")
    private String OrigemdaMercadoria;

    @JsonProperty("OutrasDespesasAcessorias")
    private String OutrasDespesasAcessorias;

    @JsonProperty("QuantidadeComercial")
    private String QuantidadeComercial;

    @JsonProperty("QuantidadeTributavel")
    private String QuantidadeTributavel;

    @JsonProperty("TributacaodoICMS")
    private String TributacaodoICMS;

    @JsonProperty("UnidadeComercial")
    private String UnidadeComercial;

    @JsonProperty("UnidadeTributavel")
    private String UnidadeTributavel;

    @JsonProperty("Valor")
    private String Valor;

    @JsonProperty("ValorAproximadodosTributos")
    private String ValorAproximadodosTributos;

    @JsonProperty("ValorTotaldoFrete")
    private String ValorTotaldoFrete;

    @JsonProperty("ValorUnitariodeComercializacao")
    private String ValorUnitariodeComercializacao;

    @JsonProperty("ValorUnitariodeTributacao")
    private String ValorUnitariodeTributacao;

    @JsonProperty("ValordaBCdoICMSSTretido")
    private String ValordaBCdoICMSSTretido;

    @JsonProperty("ValordoDesconto")
    private String ValordoDesconto;

    @JsonProperty("ValordoICMSSTretido")
    private String ValordoICMSSTretido;

    @JsonProperty("ValordoSeguro")
    private String ValordoSeguro;

    public ItemDetalhesDTO() {
    }

}
