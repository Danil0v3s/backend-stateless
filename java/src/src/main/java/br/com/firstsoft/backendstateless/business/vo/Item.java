package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ItemNotaFiscal> itemNotaFiscalList;

    public Item() {
    }

}
