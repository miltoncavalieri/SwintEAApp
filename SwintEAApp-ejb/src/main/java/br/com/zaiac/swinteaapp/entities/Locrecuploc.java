package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "locrecuploc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locrecuploc.findAll", query = "SELECT l FROM Locrecuploc l"),
    @NamedQuery(name = "Locrecuploc.findByLrpIdLrlId", query = "SELECT l FROM Locrecuploc l WHERE l.locrecuplocPK.lrpId = :lrpId AND l.locrecuplocPK.lrlId = :lrlId")})
public class Locrecuploc implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocrecuplocPK locrecuplocPK;
    @Size(max = 50)
    @Column(name = "lrl_nome")
    private String lrlNome;
    @OneToMany(mappedBy = "locrecuploc")
    private List<Checkpoint> checkpointList;
    @JoinColumn(name = "loc_nu", referencedColumnName = "loc_nu")
    @ManyToOne(optional = false)
    private CepLocalidade locNu;
    @JoinColumn(name = "lrp_id", referencedColumnName = "lrp_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Locrecup locrecup;

    public Locrecuploc() {
    }

    public Locrecuploc(LocrecuplocPK locrecuplocPK) {
        this.locrecuplocPK = locrecuplocPK;
    }

    public Locrecuploc(int lrpId, int lrlId) {
        this.locrecuplocPK = new LocrecuplocPK(lrpId, lrlId);
    }

    public LocrecuplocPK getLocrecuplocPK() {
        return locrecuplocPK;
    }

    public void setLocrecuplocPK(LocrecuplocPK locrecuplocPK) {
        this.locrecuplocPK = locrecuplocPK;
    }

    public String getLrlNome() {
        return lrlNome;
    }

    public void setLrlNome(String lrlNome) {
        this.lrlNome = lrlNome;
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }

    public CepLocalidade getLocNu() {
        return locNu;
    }

    public void setLocNu(CepLocalidade locNu) {
        this.locNu = locNu;
    }

    public Locrecup getLocrecup() {
        return locrecup;
    }

    public void setLocrecup(Locrecup locrecup) {
        this.locrecup = locrecup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locrecuplocPK != null ? locrecuplocPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locrecuploc)) {
            return false;
        }
        Locrecuploc other = (Locrecuploc) object;
        if ((this.locrecuplocPK == null && other.locrecuplocPK != null) || (this.locrecuplocPK != null && !this.locrecuplocPK.equals(other.locrecuplocPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Locrecuploc[ locrecuplocPK=" + locrecuplocPK + " ]";
    }
    
}
