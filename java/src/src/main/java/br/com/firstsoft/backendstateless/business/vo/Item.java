package br.com.firstsoft.backendstateless.business.vo;

import lombok.Data;

@Data
public class Item {

    private String descricao;
    private String codigoEAN;
    private String codigoNCM;
    private String unidadeComercial;

    public Item() {
    }

}
