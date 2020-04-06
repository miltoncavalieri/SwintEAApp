package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "locrecup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locrecup.findAll", query = "SELECT l FROM Locrecup l"),
    @NamedQuery(name = "Locrecup.findLrpId", query = "SELECT l FROM Locrecup l WHERE l.lrpId = :lrpId" )})
public class Locrecup implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "lrp_patio")
    private short lrpPatio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lrp_delegacia")
    private short lrpDelegacia;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lrp_id")
    private Integer lrpId;
    @Size(max = 50)
    @Column(name = "lrp_nome")
    private String lrpNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locrecup")
    private List<Locrecuploc> locrecuplocList;

    public Locrecup() {
    }

    public Locrecup(Integer lrpId) {
        this.lrpId = lrpId;
    }

    public Integer getLrpId() {
        return lrpId;
    }

    public void setLrpId(Integer lrpId) {
        this.lrpId = lrpId;
    }

    public String getLrpNome() {
        return lrpNome;
    }

    public void setLrpNome(String lrpNome) {
        this.lrpNome = lrpNome;
    }

    @XmlTransient
    public List<Locrecuploc> getLocrecuplocList() {
        return locrecuplocList;
    }

    public void setLocrecuplocList(List<Locrecuploc> locrecuplocList) {
        this.locrecuplocList = locrecuplocList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lrpId != null ? lrpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locrecup)) {
            return false;
        }
        Locrecup other = (Locrecup) object;
        if ((this.lrpId == null && other.lrpId != null) || (this.lrpId != null && !this.lrpId.equals(other.lrpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Locrecup[ lrpId=" + lrpId + " ]";
    }

    public short getLrpPatio() {
        return lrpPatio;
    }

    public void setLrpPatio(short lrpPatio) {
        this.lrpPatio = lrpPatio;
    }

    public short getLrpDelegacia() {
        return lrpDelegacia;
    }

    public void setLrpDelegacia(short lrpDelegacia) {
        this.lrpDelegacia = lrpDelegacia;
    }
    
}
