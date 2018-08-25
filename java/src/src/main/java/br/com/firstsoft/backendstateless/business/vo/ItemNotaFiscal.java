package br.com.firstsoft.backendstateless.business.vo;

import br.com.firstsoft.backendstateless.business.vo.embeddables.ItemNotaFiscalPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_item_nf")
public class ItemNotaFiscal {

    @EmbeddedId
    private ItemNotaFiscalPK itemNotaFiscalPK;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "item_ean")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;

    private Double quantidade;
    private Double valorUnitario;

    public ItemNotaFiscal() {
    }

}
