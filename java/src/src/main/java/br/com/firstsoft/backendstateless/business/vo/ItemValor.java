package br.com.firstsoft.backendstateless.business.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ItemValor {

    private Emitente emitente;
    private Double valorUnitario;
    private Date generatedAt;

    public ItemValor() {
    }

}
