package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p"),
    @NamedQuery(name = "Pagamento.findByPagId", query = "SELECT p FROM Pagamento p WHERE p.pagId = :pagId"),
    @NamedQuery(name = "Pagamento.deleteByPckId", query = "DELETE FROM Pagamento p WHERE p.pckId = :pckId"),
    
    @NamedQuery(name = "Pagamento.findByAgeId", query = "SELECT p FROM Pagamento p JOIN FETCH p.ageId a WHERE a.ageId = :ageId"),
    @NamedQuery(name = "Pagamento.findByAgeIdPbuId", query = "SELECT p FROM Pagamento p"),

    @NamedQuery(name = "Pagamento.deleteByPagId", query = "DELETE FROM Pagamento p WHERE p.pagId = :pagId"),
    @NamedQuery(name = "Pagamento.updateLopIdToNull", query = "UPDATE Pagamento p SET p.lopId = NULL WHERE p.lopId = :lopId"),
    @NamedQuery(name = "Pagamento.deleteLopIdNull", query = "DELETE FROM Pagamento p WHERE p.lopId = :pLopId"),
    
    @NamedQuery(name = "Pagamento.updatePagValorLopIdRecuperacao", query = "UPDATE Pagamento p SET p.pagValor =  :pagValor WHERE p.lopId = :lopId AND p.tppId = 1"),
    @NamedQuery(name = "Pagamento.updatePagValorLopIdInvestigacao", query = "UPDATE Pagamento p SET p.pagValor =  :pagValor WHERE p.lopId = :lopId AND p.tppId = 2"),
    @NamedQuery(name = "Pagamento.findByLopId", query = "SELECT p FROM Pagamento p WHERE p.lopId = :lopId"),
    @NamedQuery(name = "Pagamento.updatePagValorValorApuradoByPagId", query = "UPDATE Pagamento p SET p.pagValor = :pagValor, p.pagValorApurado = :pagValor WHERE p.pagId = :pagId"),
    
    @NamedQuery(name = "Pagamento.findPagEventual", query = "SELECT p FROM Pagamento p JOIN p.ageId a WHERE a.pbuId IS NULL AND p.lopId = :lopId"),
})
public class Pagamento implements Serializable {

    @JoinColumn(name = "tpp_id_apurado", referencedColumnName = "tpp_id")
    @ManyToOne
    private Tipopag tppIdApurado;

    @Size(max = 35)
    @Column(name = "pag_descricao")
    private String pagDescricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pag_valor_apurado")
    private BigDecimal pagValorApurado;

    @JoinColumn(name = "pag_id_invest", referencedColumnName = "pag_id")
    @ManyToOne
    private Pagamento pagIdInvest;

    @JoinColumn(name = "pck_id", referencedColumnName = "pck_id")
    @ManyToOne
    private Checkpoint pckId;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne
    private Pedbus pbuId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pag_id")
    private Long pagId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pag_relatorio")
    private short pagRelatorio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pag_dt_criacao")
    @Temporal(TemporalType.DATE)
    private Date pagDtCriacao;
    @Column(name = "pag_dt_previsao")
    @Temporal(TemporalType.DATE)
    private Date pagDtPrevisao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "pag_valor")
    private BigDecimal pagValor;
    @Column(name = "pag_ativo")
    private Short pagAtivo;
    
    @JoinTable(name = "pagoinvest", joinColumns = {
        @JoinColumn(name = "pag_id", referencedColumnName = "pag_id")}, inverseJoinColumns = {
        @JoinColumn(name = "pag_id_invest", referencedColumnName = "pag_id")})
    @ManyToMany
    private List<Pagamento> pagamentoList;
    @ManyToMany(mappedBy = "pagamentoList")
    private List<Pagamento> pagamentoList1;
    
    @JoinColumn(name = "age_id", referencedColumnName = "age_id")
    @ManyToOne(optional = false)
    private Agente ageId;
    @JoinColumn(name = "tpp_id", referencedColumnName = "tpp_id")
    @ManyToOne
    private Tipopag tppId;
    @JoinColumn(name = "lop_id", referencedColumnName = "lop_id")
    @ManyToOne
    private Lotepgto lopId;

    public Pagamento() {
    }

    public Pagamento(Long pagId) {
        this.pagId = pagId;
    }

    public Pagamento(Long pagId, short pagRelatorio, Date pagDtCriacao, BigDecimal pagValor) {
        this.pagId = pagId;
        this.pagRelatorio = pagRelatorio;
        this.pagDtCriacao = pagDtCriacao;
        this.pagValor = pagValor;
    }

    public Long getPagId() {
        return pagId;
    }

    public void setPagId(Long pagId) {
        this.pagId = pagId;
    }

    public short getPagRelatorio() {
        return pagRelatorio;
    }

    public void setPagRelatorio(short pagRelatorio) {
        this.pagRelatorio = pagRelatorio;
    }

    public Date getPagDtCriacao() {
        return pagDtCriacao;
    }

    public void setPagDtCriacao(Date pagDtCriacao) {
        this.pagDtCriacao = pagDtCriacao;
    }

    public Date getPagDtPrevisao() {
        return pagDtPrevisao;
    }

    public void setPagDtPrevisao(Date pagDtPrevisao) {
        this.pagDtPrevisao = pagDtPrevisao;
    }

    public BigDecimal getPagValor() {
        return pagValor;
    }

    public void setPagValor(BigDecimal pagValor) {
        this.pagValor = pagValor;
    }

    public Short getPagAtivo() {
        return pagAtivo;
    }

    public void setPagAtivo(Short pagAtivo) {
        this.pagAtivo = pagAtivo;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList1() {
        return pagamentoList1;
    }

    public void setPagamentoList1(List<Pagamento> pagamentoList1) {
        this.pagamentoList1 = pagamentoList1;
    }

    public Agente getAgeId() {
        return ageId;
    }

    public void setAgeId(Agente ageId) {
        this.ageId = ageId;
    }

    public Tipopag getTppId() {
        return tppId;
    }

    public void setTppId(Tipopag tppId) {
        this.tppId = tppId;
    }

    public Lotepgto getLopId() {
        return lopId;
    }

    public void setLopId(Lotepgto lopId) {
        this.lopId = lopId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagId != null ? pagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.pagId == null && other.pagId != null) || (this.pagId != null && !this.pagId.equals(other.pagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Pagamento[ pagId=" + pagId + " ]";
    }

    public Checkpoint getPckId() {
        return pckId;
    }

    public void setPckId(Checkpoint pckId) {
        this.pckId = pckId;
    }

    public Pedbus getPbuId() {
        return pbuId;
    }

    public void setPbuId(Pedbus pbuId) {
        this.pbuId = pbuId;
    }

    public Pagamento getPagIdInvest() {
        return pagIdInvest;
    }

    public void setPagIdInvest(Pagamento pagIdInvest) {
        this.pagIdInvest = pagIdInvest;
    }

    public String getPagDescricao() {
        return pagDescricao;
    }

    public void setPagDescricao(String pagDescricao) {
        this.pagDescricao = pagDescricao;
    }

    public BigDecimal getPagValorApurado() {
        return pagValorApurado;
    }

    public void setPagValorApurado(BigDecimal pagValorApurado) {
        this.pagValorApurado = pagValorApurado;
    }

    public Tipopag getTppIdApurado() {
        return tppIdApurado;
    }

    public void setTppIdApurado(Tipopag tppIdApurado) {
        this.tppIdApurado = tppIdApurado;
    }
    
}
