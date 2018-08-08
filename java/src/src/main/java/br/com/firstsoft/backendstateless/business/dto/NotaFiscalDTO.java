package br.com.firstsoft.backendstateless.business.dto;

import br.com.firstsoft.backendstateless.business.vo.Emitente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NotaFiscalDTO {

    @JsonProperty("ChavedeAcesso")
    private String chaveAcesso;

    @JsonProperty("DadosBasicos")
    private DadosBasicosDTO dadosBasicos;

    @JsonProperty("Emitente")
    private Emitente emitente;

    @JsonProperty("Itens")
    private List<ItemDTO> itens;

}
