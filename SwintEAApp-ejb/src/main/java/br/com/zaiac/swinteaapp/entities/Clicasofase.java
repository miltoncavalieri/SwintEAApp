package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "clicasofase")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "Clicasofase.findAll", query = "SELECT c FROM Clicasofase c"),
    @NamedQuery(name = "Clicasofase.findByA01IdA02Id", query = "SELECT c FROM Clicasofase c WHERE c.clicasofasePK.a01Id = :a01Id AND c.clicasofasePK.a02Id = :a02Id")        
})
public class Clicasofase implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clicasofase")
    private List<Clicasocheckpoint> clicasocheckpointList;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClicasofasePK clicasofasePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "a02_nome")
    private String a02Nome;
    @JoinColumn(name = "a01_id", referencedColumnName = "a01_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clicasotipolote clicasotipolote;

    public Clicasofase() {
    }

    public Clicasofase(ClicasofasePK clicasofasePK) {
        this.clicasofasePK = clicasofasePK;
    }

    public Clicasofase(ClicasofasePK clicasofasePK, String a02Nome) {
        this.clicasofasePK = clicasofasePK;
        this.a02Nome = a02Nome;
    }

    public Clicasofase(short a01Id, short a02Id) {
        this.clicasofasePK = new ClicasofasePK(a01Id, a02Id);
    }

    public ClicasofasePK getClicasofasePK() {
        return clicasofasePK;
    }

    public void setClicasofasePK(ClicasofasePK clicasofasePK) {
        this.clicasofasePK = clicasofasePK;
    }

    public String getA02Nome() {
        return a02Nome;
    }

    public void setA02Nome(String a02Nome) {
        this.a02Nome = a02Nome;
    }

    public Clicasotipolote getClicasotipolote() {
        return clicasotipolote;
    }

    public void setClicasotipolote(Clicasotipolote clicasotipolote) {
        this.clicasotipolote = clicasotipolote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clicasofasePK != null ? clicasofasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clicasofase)) {
            return false;
        }
        Clicasofase other = (Clicasofase) object;
        if ((this.clicasofasePK == null && other.clicasofasePK != null) || (this.clicasofasePK != null && !this.clicasofasePK.equals(other.clicasofasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clicasofase[ clicasofasePK=" + clicasofasePK + " ]";
    }

    @XmlTransient
    public List<Clicasocheckpoint> getClicasocheckpointList() {
        return clicasocheckpointList;
    }

    public void setClicasocheckpointList(List<Clicasocheckpoint> clicasocheckpointList) {
        this.clicasocheckpointList = clicasocheckpointList;
    }
    
}
