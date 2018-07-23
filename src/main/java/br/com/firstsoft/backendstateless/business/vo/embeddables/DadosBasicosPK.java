package br.com.firstsoft.backendstateless.business.vo.embeddables;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class DadosBasicosPK {

    private String dataEmissao;
    private String modelo;
    private String numero;
    private String serie;
    private String valorNota;

    public DadosBasicosPK(String dataEmissao, String modelo, String numero, String serie, String valorNota) {
        this.dataEmissao = dataEmissao;
        this.modelo = modelo;
        this.numero = numero;
        this.serie = serie;
        this.valorNota = valorNota;
    }
}
