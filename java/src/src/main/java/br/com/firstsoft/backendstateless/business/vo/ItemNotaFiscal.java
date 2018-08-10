package br.com.firstsoft.backendstateless.business.vo;

import lombok.Data;

@Data
public class ItemNotaFiscal {

    private Item item;
    private Double quantidade;
    private ItemValor itemValor;
    private NotaFiscal notaFiscal;

    public ItemNotaFiscal() {
    }

}
