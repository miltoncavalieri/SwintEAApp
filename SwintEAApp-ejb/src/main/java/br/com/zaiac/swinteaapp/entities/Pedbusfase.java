package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "pedbusfase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedbusfase.findAll", query = "SELECT p FROM Pedbusfase p"),
    @NamedQuery(name = "Pedbusfase.findByFasId", query = "SELECT p FROM Pedbusfase p WHERE p.fasId = :fasId")

})
public class Pedbusfase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fas_id")
    private Short fasId;
    @Size(max = 10)
    @Column(name = "fas_nome")
    private String fasNome;
    @OneToMany(mappedBy = "fasId")
    private List<Checkpoint> checkpointList;

    public Pedbusfase() {
    }

    public Pedbusfase(Short fasId) {
        this.fasId = fasId;
    }

    public Short getFasId() {
        return fasId;
    }

    public void setFasId(Short fasId) {
        this.fasId = fasId;
    }

    public String getFasNome() {
        return fasNome;
    }

    public void setFasNome(String fasNome) {
        this.fasNome = fasNome;
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
        hash += (fasId != null ? fasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pedbusfase)) {
            return false;
        }
        Pedbusfase other = (Pedbusfase) object;
        if ((this.fasId == null && other.fasId != null) || (this.fasId != null && !this.fasId.equals(other.fasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Pedbusfase[ fasId=" + fasId + " ]";
    }
    
}
