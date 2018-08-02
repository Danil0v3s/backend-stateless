package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_nota_fiscal")
public class NotaFiscal {

    @Id
    @JsonProperty("ChavedeAcesso")
    private String chaveAcesso;

    @JsonProperty("DadosBasicos")
    @OneToOne(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private DadosBasicos dadosBasicos;

    @JsonProperty("Emitente")
    @OneToOne(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Emitente emitente;

//    @JsonProperty("Itens")
//    @OneToMany(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private List<ItemDTO> items;

    public void setParentToChildren() {
        this.dadosBasicos.setNotaFiscal(this);
        this.dadosBasicos.setPrimaryKey();
        this.emitente.setNotaFiscal(this);
//        this.items.forEach(item -> {
//            item.setNotaFiscal(this);
//            item.getItemDetalhes().setItem(item);
//        });
    }

}
