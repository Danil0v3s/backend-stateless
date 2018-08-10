package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class ItemValor {

    private Emitente emitente;
    private Double valorUnitario;
    private Date generatedAt;

    @JsonIgnore
    private ItemNotaFiscal itemNotaFiscal;

    public ItemValor() {
    }

}
