package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_item_detalhes_nf")
public class ItemDetalhes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itemDetalhesID;

    @JsonProperty("Aliquota")
    private String aliquota;

    @JsonProperty("BasedeCalculo")
    private String baseCalculo;

    @JsonProperty("CFOP")
    private String cfop;

    @JsonProperty("CodigoNCM")
    private String codigoNCM;

    @JsonProperty("Codigodoproduto")
    private String codigoProduto;

    @JsonProperty("UnidadeComercial")
    private String unidadeComercial;

    @JsonProperty("Valor")
    private String valor;

    @JsonProperty("ValorAproximadodosTributos")
    private String valorTributos;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

}
