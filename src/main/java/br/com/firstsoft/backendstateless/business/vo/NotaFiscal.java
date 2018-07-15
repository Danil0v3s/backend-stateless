package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_nota_fiscal")
public class NotaFiscal {

    @Id
    @JsonProperty("ChavedeAcesso")
    private String chaveAcesso;

    @JsonProperty("DadosBasicos")
    @OneToOne(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DadosBasicos dadosBasicos;

    @JsonProperty("Emitente")
    @OneToOne(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Emitente emitente;

    @JsonProperty("Itens")
    @OneToMany(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items;

    public void setParentToChildren() {
        this.dadosBasicos.setNotaFiscal(this);
        this.emitente.setNotaFiscal(this);
        this.items.forEach(item -> {
            item.setNotaFiscal(this);
            item.getItemDetalhes().setItem(item);
        });
    }

}
