package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "clicasovalidacao")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "Clicasovalidacao.findAll", query = "SELECT c FROM Clicasovalidacao c"),
    @NamedQuery(name = "Clicasovalidacao.deleteByA04IdCaso", query = "DELETE FROM Clicasovalidacao c WHERE c.clicasovalidacaoPK.a04IdCaso = :a04IdCaso")
})
public class Clicasovalidacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClicasovalidacaoPK clicasovalidacaoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a08_tipo_servico")
    private short a08TipoServico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a08_tipo_ocorrencia")
    private short a08TipoOcorrencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "a08_retorno_vencido")
    private String a08RetornoVencido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a08_classificacao")
    private short a08Classificacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "a08_houve_investigacao")
    private String a08HouveInvestigacao;
    @Column(name = "a08_locentr_tipo")
    private Short a08LocentrTipo;
    @Size(max = 100)
    @Column(name = "a08_locentr_descricao")
    private String a08LocentrDescricao;
    @Size(max = 60)
    @Column(name = "a08_locentr_nomeatend")
    private String a08LocentrNomeatend;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "a08_valor")
    private BigDecimal a08Valor;
    @Lob
    @Size(max = 65535)
    @Column(name = "a08_observacao")
    private String a08Observacao;
    @JoinColumn(name = "a04_id_caso", referencedColumnName = "a04_id_caso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clicaso clicaso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clicasovalidacao")
    private List<Clicasovalidacaoanexo> clicasovalidacaoanexoList;

    public Clicasovalidacao() {
    }

    public Clicasovalidacao(ClicasovalidacaoPK clicasovalidacaoPK) {
        this.clicasovalidacaoPK = clicasovalidacaoPK;
    }

    public Clicasovalidacao(ClicasovalidacaoPK clicasovalidacaoPK, short a08TipoServico, short a08TipoOcorrencia, String a08RetornoVencido, short a08Classificacao, String a08HouveInvestigacao, BigDecimal a08Valor) {
        this.clicasovalidacaoPK = clicasovalidacaoPK;
        this.a08TipoServico = a08TipoServico;
        this.a08TipoOcorrencia = a08TipoOcorrencia;
        this.a08RetornoVencido = a08RetornoVencido;
        this.a08Classificacao = a08Classificacao;
        this.a08HouveInvestigacao = a08HouveInvestigacao;
        this.a08Valor = a08Valor;
    }

    public Clicasovalidacao(long a04IdCaso, int a08Seq) {
        this.clicasovalidacaoPK = new ClicasovalidacaoPK(a04IdCaso, a08Seq);
    }

    public ClicasovalidacaoPK getClicasovalidacaoPK() {
        return clicasovalidacaoPK;
    }

    public void setClicasovalidacaoPK(ClicasovalidacaoPK clicasovalidacaoPK) {
        this.clicasovalidacaoPK = clicasovalidacaoPK;
    }

    public short getA08TipoServico() {
        return a08TipoServico;
    }

    public void setA08TipoServico(short a08TipoServico) {
        this.a08TipoServico = a08TipoServico;
    }

    public short getA08TipoOcorrencia() {
        return a08TipoOcorrencia;
    }

    public void setA08TipoOcorrencia(short a08TipoOcorrencia) {
        this.a08TipoOcorrencia = a08TipoOcorrencia;
    }

    public String getA08RetornoVencido() {
        return a08RetornoVencido;
    }

    public void setA08RetornoVencido(String a08RetornoVencido) {
        this.a08RetornoVencido = a08RetornoVencido;
    }

    public short getA08Classificacao() {
        return a08Classificacao;
    }

    public void setA08Classificacao(short a08Classificacao) {
        this.a08Classificacao = a08Classificacao;
    }

    public String getA08HouveInvestigacao() {
        return a08HouveInvestigacao;
    }

    public void setA08HouveInvestigacao(String a08HouveInvestigacao) {
        this.a08HouveInvestigacao = a08HouveInvestigacao;
    }

    public Short getA08LocentrTipo() {
        return a08LocentrTipo;
    }

    public void setA08LocentrTipo(Short a08LocentrTipo) {
        this.a08LocentrTipo = a08LocentrTipo;
    }

    public String getA08LocentrDescricao() {
        return a08LocentrDescricao;
    }

    public void setA08LocentrDescricao(String a08LocentrDescricao) {
        this.a08LocentrDescricao = a08LocentrDescricao;
    }

    public String getA08LocentrNomeatend() {
        return a08LocentrNomeatend;
    }

    public void setA08LocentrNomeatend(String a08LocentrNomeatend) {
        this.a08LocentrNomeatend = a08LocentrNomeatend;
    }

    public BigDecimal getA08Valor() {
        return a08Valor;
    }

    public void setA08Valor(BigDecimal a08Valor) {
        this.a08Valor = a08Valor;
    }

    public String getA08Observacao() {
        return a08Observacao;
    }

    public void setA08Observacao(String a08Observacao) {
        this.a08Observacao = a08Observacao;
    }

    public Clicaso getClicaso() {
        return clicaso;
    }

    public void setClicaso(Clicaso clicaso) {
        this.clicaso = clicaso;
    }

    @XmlTransient
    public List<Clicasovalidacaoanexo> getClicasovalidacaoanexoList() {
        return clicasovalidacaoanexoList;
    }

    public void setClicasovalidacaoanexoList(List<Clicasovalidacaoanexo> clicasovalidacaoanexoList) {
        this.clicasovalidacaoanexoList = clicasovalidacaoanexoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clicasovalidacaoPK != null ? clicasovalidacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clicasovalidacao)) {
            return false;
        }
        Clicasovalidacao other = (Clicasovalidacao) object;
        if ((this.clicasovalidacaoPK == null && other.clicasovalidacaoPK != null) || (this.clicasovalidacaoPK != null && !this.clicasovalidacaoPK.equals(other.clicasovalidacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clicasovalidacao[ clicasovalidacaoPK=" + clicasovalidacaoPK + " ]";
    }
    
}
