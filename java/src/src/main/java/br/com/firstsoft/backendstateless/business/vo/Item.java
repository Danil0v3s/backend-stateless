package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    private String codigoEAN;
    private String descricao;
    private String codigoNCM;
    private String unidadeComercial;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_nota_fiscal")
    private ItemNotaFiscal itemNotaFiscal;

    public Item() {
    }

}
