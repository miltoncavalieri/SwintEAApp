package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "clicasocheckpoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clicasocheckpoint.findAll", query = "SELECT c FROM Clicasocheckpoint c"),
    @NamedQuery(name = "Clicasocheckpoint.findA04IdCasoA05Seq", query = "SELECT c FROM Clicasocheckpoint c WHERE c.clicasocheckpointPK.a04IdCaso = :a04IdCaso AND c.clicasocheckpointPK.a05Seq = :a05Seq"),
    @NamedQuery(name = "Clicasocheckpoint.deleteByA04IdCaso", query = "DELETE FROM Clicasocheckpoint c WHERE c.clicasocheckpointPK.a04IdCaso = :a04IdCaso"),
    @NamedQuery(name = "Clicasocheckpoint.findMaxA05Seq", query = "SELECT MAX(c.clicasocheckpointPK.a05Seq) FROM Clicasocheckpoint c WHERE c.clicasocheckpointPK.a04IdCaso = :a04IdCaso")
})
public class Clicasocheckpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClicasocheckpointPK clicasocheckpointPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a05_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a05Dt;
    @Lob
    @Size(max = 65535)
    @Column(name = "a05_msg")
    private String a05Msg;
    @JoinColumn(name = "a04_id_caso", referencedColumnName = "a04_id_caso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clicaso clicaso;
    @JoinColumns({
        @JoinColumn(name = "a01_id", referencedColumnName = "a01_id"),
        @JoinColumn(name = "a02_id", referencedColumnName = "a02_id")})
    @ManyToOne(optional = false)
    private Clicasofase clicasofase;

    public Clicasocheckpoint() {
    }

    public Clicasocheckpoint(ClicasocheckpointPK clicasocheckpointPK) {
        this.clicasocheckpointPK = clicasocheckpointPK;
    }

    public Clicasocheckpoint(ClicasocheckpointPK clicasocheckpointPK, Date a05Dt) {
        this.clicasocheckpointPK = clicasocheckpointPK;
        this.a05Dt = a05Dt;
    }

    public Clicasocheckpoint(long a04IdCaso, int a05Seq) {
        this.clicasocheckpointPK = new ClicasocheckpointPK(a04IdCaso, a05Seq);
    }

    public ClicasocheckpointPK getClicasocheckpointPK() {
        return clicasocheckpointPK;
    }

    public void setClicasocheckpointPK(ClicasocheckpointPK clicasocheckpointPK) {
        this.clicasocheckpointPK = clicasocheckpointPK;
    }

    public Date getA05Dt() {
        return a05Dt;
    }

    public void setA05Dt(Date a05Dt) {
        this.a05Dt = a05Dt;
    }

    public String getA05Msg() {
        return a05Msg;
    }

    public void setA05Msg(String a05Msg) {
        this.a05Msg = a05Msg;
    }

    public Clicaso getClicaso() {
        return clicaso;
    }

    public void setClicaso(Clicaso clicaso) {
        this.clicaso = clicaso;
    }

    public Clicasofase getClicasofase() {
        return clicasofase;
    }

    public void setClicasofase(Clicasofase clicasofase) {
        this.clicasofase = clicasofase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clicasocheckpointPK != null ? clicasocheckpointPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clicasocheckpoint)) {
            return false;
        }
        Clicasocheckpoint other = (Clicasocheckpoint) object;
        if ((this.clicasocheckpointPK == null && other.clicasocheckpointPK != null) || (this.clicasocheckpointPK != null && !this.clicasocheckpointPK.equals(other.clicasocheckpointPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clicasocheckpoint[ clicasocheckpointPK=" + clicasocheckpointPK + " ]";
    }
    
}
