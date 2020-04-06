package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "agente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agente.findAll", query = "SELECT a FROM Agente a"),
    @NamedQuery(name = "Agente.findByPbuIdAgsIdAtivo", 
            query = "SELECT a FROM Agente a WHERE (a.pbuId = :pbuId) AND (a.agsId = 2) AND a.ageDtDesignacao = (SELECT MAX(a1.ageDtDesignacao) FROM Agente a1 WHERE a1.pbuId = a.pbuId)"),
    @NamedQuery(name = "Agente.findByPckIdAgsIdCheckpoint", query = "SELECT a FROM Agente a WHERE (a.pckId = :pckId) AND (a.agsId IN (2,3))"),
    @NamedQuery(name = "Agente.updateByAgeIdSetPckIdNull", query = "UPDATE Agente a SET a.pckId = null WHERE a.ageId = :ageId"),
    @NamedQuery(name = "Agente.deleteByAgeId", query = "DELETE FROM Agente a WHERE a.ageId = :ageId"),
    @NamedQuery(name = "Agente.findAgenteAtivo", query = "SELECT COUNT(1) FROM Agente a WHERE a.pbuId = :pbuId and a.usuId = :usuId and ags_id in (2,3)"),
    @NamedQuery(name = "Agente.findByAgeId", query = "SELECT a FROM Agente a WHERE a.ageId = :ageId")

})
public class Agente implements Serializable {

    @OneToMany(mappedBy = "ageId")
    private List<Checkpoint> checkpointList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "age_id")
    private Long ageId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age_dt_designacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ageDtDesignacao;
    @JoinColumn(name = "ags_id", referencedColumnName = "ags_id")
    @ManyToOne(optional = false)
    private Agentesit agsId;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuId;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne
    private Pedbus pbuId;
    @JoinColumn(name = "pck_id", referencedColumnName = "pck_id")
    @ManyToOne
    private Checkpoint pckId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ageId")
    private List<Pagamento> pagamentoList;

    public Agente() {
    }

    public Agente(Long ageId) {
        this.ageId = ageId;
    }

    public Agente(Long ageId, Date ageDtDesignacao) {
        this.ageId = ageId;
        this.ageDtDesignacao = ageDtDesignacao;
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }

    public Date getAgeDtDesignacao() {
        return ageDtDesignacao;
    }

    public void setAgeDtDesignacao(Date ageDtDesignacao) {
        this.ageDtDesignacao = ageDtDesignacao;
    }

    public Agentesit getAgsId() {
        return agsId;
    }

    public void setAgsId(Agentesit agsId) {
        this.agsId = agsId;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    public Pedbus getPbuId() {
        return pbuId;
    }

    public void setPbuId(Pedbus pbuId) {
        this.pbuId = pbuId;
    }

    public Checkpoint getPckId() {
        return pckId;
    }

    public void setPckId(Checkpoint pckId) {
        this.pckId = pckId;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ageId != null ? ageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Agente)) {
            return false;
        }
        Agente other = (Agente) object;
        if ((this.ageId == null && other.ageId != null) || (this.ageId != null && !this.ageId.equals(other.ageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Agente[ ageId=" + ageId + " ]";
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }
    
}
