package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ckptipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ckptipo.findAll", query = "SELECT c FROM Ckptipo c"),
    @NamedQuery(name = "Ckptipo.findByCktId", query = "SELECT c FROM Ckptipo c WHERE c.cktId = :cktId")
})
public class Ckptipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ckt_id")
    private Short cktId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ckt_nome")
    private String cktNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cktId")
    private List<Checkpoint> checkpointList;

    public Ckptipo() {
    }

    public Ckptipo(Short cktId) {
        this.cktId = cktId;
    }

    public Ckptipo(Short cktId, String cktNome) {
        this.cktId = cktId;
        this.cktNome = cktNome;
    }

    public Short getCktId() {
        return cktId;
    }

    public void setCktId(Short cktId) {
        this.cktId = cktId;
    }

    public String getCktNome() {
        return cktNome;
    }

    public void setCktNome(String cktNome) {
        this.cktNome = cktNome;
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cktId != null ? cktId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ckptipo)) {
            return false;
        }
        Ckptipo other = (Ckptipo) object;
        if ((this.cktId == null && other.cktId != null) || (this.cktId != null && !this.cktId.equals(other.cktId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Ckptipo[ cktId=" + cktId + " ]";
    }
    
}
