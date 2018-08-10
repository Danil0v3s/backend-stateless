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

    @OneToOne(mappedBy = "itemNotaFiscal", fetch = FetchType.EAGER)
    private Item item;
    private Double quantidade;

    @OneToOne(mappedBy = "itemNotaFiscal", fetch = FetchType.EAGER)
    private ItemValor itemValor;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;

    public ItemNotaFiscal() {
    }

}
