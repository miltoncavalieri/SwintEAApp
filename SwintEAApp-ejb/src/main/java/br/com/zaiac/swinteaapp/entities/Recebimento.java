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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "recebimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recebimento.findAll", query = "SELECT r FROM Recebimento r"),
    @NamedQuery(name = "Recebimento.findByRcbId", query = "SELECT r FROM Recebimento r WHERE r.rcbId = :rcbId"),
    @NamedQuery(name = "Recebimento.findByLcbId", query = "SELECT r FROM Recebimento r WHERE r.lcbId = :lcbId"),
    @NamedQuery(name = "Recebimento.deleteByPckId", query = "DELETE FROM Recebimento r WHERE r.pckId = :pckId"),
    @NamedQuery(name = "Recebimento.updateRcbRelatorioByPckId", query = "UPDATE Recebimento r SET r.rcbRelatorio = 1 WHERE r.pckId = :pckId"),
    @NamedQuery(name = "Recebimento.updateRcbRelatorioByPbuId", query = "UPDATE Recebimento r SET r.rcbRelatorio = 1 WHERE r.pckId = :pckId"),
    
    @NamedQuery(name = "Recebimento.deleteRecEventual", query = "DELETE FROM Recebimento r WHERE r.pbuId IS NULL and r.lcbId = :lcbId"),
    @NamedQuery(name = "Recebimento.updateLcbIdToNull", query = "UPDATE Recebimento r SET r.lcbId = NULL WHERE r.lcbId = :lcbId"),
})
public class Recebimento implements Serializable {

    @JoinColumn(name = "rcb_id_invest", referencedColumnName = "rcb_id")
    @ManyToOne
    private Recebimento rcbIdInvest;

    @JoinColumn(name = "pck_id", referencedColumnName = "pck_id")
    @ManyToOne(optional = false)
    private Checkpoint pckId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rcb_id")
    private Long rcbId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rcb_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rcbDt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rcb_valor")
    private BigDecimal rcbValor;
    @Column(name = "rcb_relatorio")
    private Short rcbRelatorio;
    @JoinTable(name = "recebinvest", joinColumns = {
        @JoinColumn(name = "rcb_id", referencedColumnName = "rcb_id")}, inverseJoinColumns = {
        @JoinColumn(name = "rcb_id_invest", referencedColumnName = "rcb_id")})
    @ManyToMany
    private List<Recebimento> recebimentoList;
    @ManyToMany(mappedBy = "recebimentoList")
    private List<Recebimento> recebimentoList1;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne
    private Pedbus pbuId;
    @JoinColumn(name = "tpp_id", referencedColumnName = "tpp_id")
    @ManyToOne(optional = false)
    private Tipopag tppId;
    @JoinColumn(name = "lcb_id", referencedColumnName = "lcb_id")
    @ManyToOne
    private Lotercbo lcbId;

    public Recebimento() {
    }

    public Recebimento(Long rcbId) {
        this.rcbId = rcbId;
    }

    public Recebimento(Long rcbId, Date rcbDt, BigDecimal rcbValor) {
        this.rcbId = rcbId;
        this.rcbDt = rcbDt;
        this.rcbValor = rcbValor;
    }

    public Long getRcbId() {
        return rcbId;
    }

    public void setRcbId(Long rcbId) {
        this.rcbId = rcbId;
    }

    public Date getRcbDt() {
        return rcbDt;
    }

    public void setRcbDt(Date rcbDt) {
        this.rcbDt = rcbDt;
    }

    public BigDecimal getRcbValor() {
        return rcbValor;
    }

    public void setRcbValor(BigDecimal rcbValor) {
        this.rcbValor = rcbValor;
    }

    public Short getRcbRelatorio() {
        return rcbRelatorio;
    }

    public void setRcbRelatorio(Short rcbRelatorio) {
        this.rcbRelatorio = rcbRelatorio;
    }

    @XmlTransient
    public List<Recebimento> getRecebimentoList() {
        return recebimentoList;
    }

    public void setRecebimentoList(List<Recebimento> recebimentoList) {
        this.recebimentoList = recebimentoList;
    }

    @XmlTransient
    public List<Recebimento> getRecebimentoList1() {
        return recebimentoList1;
    }

    public void setRecebimentoList1(List<Recebimento> recebimentoList1) {
        this.recebimentoList1 = recebimentoList1;
    }

    public Pedbus getPbuId() {
        return pbuId;
    }

    public void setPbuId(Pedbus pbuId) {
        this.pbuId = pbuId;
    }

    public Tipopag getTppId() {
        return tppId;
    }

    public void setTppId(Tipopag tppId) {
        this.tppId = tppId;
    }

    public Lotercbo getLcbId() {
        return lcbId;
    }

    public void setLcbId(Lotercbo lcbId) {
        this.lcbId = lcbId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rcbId != null ? rcbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Recebimento)) {
            return false;
        }
        Recebimento other = (Recebimento) object;
        if ((this.rcbId == null && other.rcbId != null) || (this.rcbId != null && !this.rcbId.equals(other.rcbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Recebimento[ rcbId=" + rcbId + " ]";
    }

    public Checkpoint getPckId() {
        return pckId;
    }

    public void setPckId(Checkpoint pckId) {
        this.pckId = pckId;
    }

    public Recebimento getRcbIdInvest() {
        return rcbIdInvest;
    }

    public void setRcbIdInvest(Recebimento rcbIdInvest) {
        this.rcbIdInvest = rcbIdInvest;
    }
    
}
