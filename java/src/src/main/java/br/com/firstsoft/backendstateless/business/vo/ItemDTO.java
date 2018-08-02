package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDTO {

    @JsonProperty("Descricao")
    private String descricao;

    @JsonProperty("Quantidade")
    private String quantidade;

    @JsonProperty("UnidadeComercial")
    private String unidadeComercial;

    @JsonProperty("ValorR")
    private String valor;

    @JsonProperty("Detalhes")
    private ItemDetalhesDTO itemDetalhes;

}
