package br.com.firstsoft.backendstateless.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DadosBasicosDTO {

    @JsonProperty("DatadeEmissao")
    private String dataEmissao;
    @JsonProperty("Modelo")
    private String modelo;
    @JsonProperty("Numero")
    private String numero;
    @JsonProperty("Serie")
    private String serie;
    @JsonProperty("ValorTotaldaNotaFiscal")
    private String valorNota;

}
