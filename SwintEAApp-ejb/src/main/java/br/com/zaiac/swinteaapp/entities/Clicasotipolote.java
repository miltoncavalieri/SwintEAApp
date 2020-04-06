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
@Table(name = "clicasotipolote")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "Clicasotipolote.findAll", query = "SELECT c FROM Clicasotipolote c"),
    @NamedQuery(name = "Clicasotipolote.findByA01Id", query = "SELECT c FROM Clicasotipolote c WHERE c.a01Id = :a01Id")
})
public class Clicasotipolote implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clicasotipolote")
    private List<Clicasofase> clicasofaseList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "a01_id")
    private Short a01Id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "a01_nome")
    private String a01Nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "a01Id")
    private List<Clicasolote> clicasoloteList;

    public Clicasotipolote() {
    }

    public Clicasotipolote(Short a01Id) {
        this.a01Id = a01Id;
    }

    public Clicasotipolote(Short a01Id, String a01Nome) {
        this.a01Id = a01Id;
        this.a01Nome = a01Nome;
    }

    public Short getA01Id() {
        return a01Id;
    }

    public void setA01Id(Short a01Id) {
        this.a01Id = a01Id;
    }

    public String getA01Nome() {
        return a01Nome;
    }

    public void setA01Nome(String a01Nome) {
        this.a01Nome = a01Nome;
    }

    @XmlTransient
    public List<Clicasolote> getClicasoloteList() {
        return clicasoloteList;
    }

    public void setClicasoloteList(List<Clicasolote> clicasoloteList) {
        this.clicasoloteList = clicasoloteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (a01Id != null ? a01Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clicasotipolote)) {
            return false;
        }
        Clicasotipolote other = (Clicasotipolote) object;
        if ((this.a01Id == null && other.a01Id != null) || (this.a01Id != null && !this.a01Id.equals(other.a01Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clicasotipolote[ a01Id=" + a01Id + " ]";
    }

    @XmlTransient
    public List<Clicasofase> getClicasofaseList() {
        return clicasofaseList;
    }

    public void setClicasofaseList(List<Clicasofase> clicasofaseList) {
        this.clicasofaseList = clicasofaseList;
    }
    
}
