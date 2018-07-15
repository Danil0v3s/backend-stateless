package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_item_nf")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itemID;

    @JsonProperty("Descricao")
    private String descricao;

    @JsonProperty("Quantidade")
    private String quantidade;

    @JsonProperty("UnidadeComercial")
    private String unidadeComercial;

    @JsonProperty("ValorR")
    private String valor;

    @JsonProperty("Detalhes")
    @OneToOne(mappedBy = "item", fetch = FetchType.EAGER)
    private ItemDetalhes itemDetalhes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;

}
