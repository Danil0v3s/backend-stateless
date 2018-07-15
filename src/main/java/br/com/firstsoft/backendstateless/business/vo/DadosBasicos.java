package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_dados_basicos_nf")
public class DadosBasicos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID dadosBasicosID;

    @JsonProperty("DataSaidaEntrada")
    private String dataSaidaEntrada;

    @JsonProperty("DatadeEmissao")
    private String dataEmissao;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("Numero")
    private String numero;

    @JsonProperty("Serie")
    private String serie;

    @JsonProperty("ValorTotaldaNotaFiscal")
    private String valorNota;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;
}
