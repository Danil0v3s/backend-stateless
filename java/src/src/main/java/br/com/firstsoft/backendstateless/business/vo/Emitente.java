package br.com.firstsoft.backendstateless.business.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_emitente_nf")
public class Emitente {

    @JsonProperty("BairroDistrito")
    private String bairroDistrito;

    @JsonProperty("CEP")
    private String cep;

    @JsonProperty("CNAEFiscal")
    private String cnaeFiscal;

    @Id
    @JsonProperty("CNPJ")
    private String cnpj;

    @JsonProperty("Endereco")
    private String endereco;

    @JsonProperty("InscricaoEstadual")
    private String inscricaoEstadual;

    @JsonProperty("InscricaoMunicipal")
    private String inscricaoMunicipal;

    @JsonProperty("Municipio")
    private String municipio;

    @JsonProperty("MunicipiodaOcorrenciadoFatoGeradordoICMS")
    private String municipioOcorrenciaICMS;

    @JsonProperty("NomeFantasia")
    private String nomeFantasia;

    @JsonProperty("NomeRazaoSocial")
    private String nomeRazaoSocial;

    @JsonProperty("Pais")
    private String pais;

    @JsonProperty("RegimeTributario")
    private String regimeTributario;

    @JsonProperty("Telefone")
    private String telefone;

    @JsonProperty("UF")
    private String uf;

    @JsonIgnore
    @OneToMany(mappedBy = "emitente", fetch = FetchType.LAZY)
    private List<NotaFiscal> notaFiscalList;

    public List<NotaFiscal> getNotaFiscalList() {
        if (this.notaFiscalList == null) {
            this.notaFiscalList = new ArrayList<>();
        }

        return this.notaFiscalList;
    }

}
