package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "rvintegra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rvintegra.findAll", query = "SELECT r FROM Rvintegra r")})
public class Rvintegra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rvi_id")
    private Long rviId;
    @Column(name = "rvi_dt_integracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rviDtIntegracao;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne(optional = false)
    private Analise pbuId;

    public Rvintegra() {
    }

    public Rvintegra(Long rviId) {
        this.rviId = rviId;
    }

    public Long getRviId() {
        return rviId;
    }

    public void setRviId(Long rviId) {
        this.rviId = rviId;
    }

    public Date getRviDtIntegracao() {
        return rviDtIntegracao;
    }

    public void setRviDtIntegracao(Date rviDtIntegracao) {
        this.rviDtIntegracao = rviDtIntegracao;
    }

    public Analise getPbuId() {
        return pbuId;
    }

    public void setPbuId(Analise pbuId) {
        this.pbuId = pbuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rviId != null ? rviId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rvintegra)) {
            return false;
        }
        Rvintegra other = (Rvintegra) object;
        if ((this.rviId == null && other.rviId != null) || (this.rviId != null && !this.rviId.equals(other.rviId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Rvintegra[ rviId=" + rviId + " ]";
    }
    
}
