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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "clicasolote")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "Clicasolote.findAll", query = "SELECT c FROM Clicasolote c"),
    @NamedQuery(name = "Clicasolote.findLoteAberto", query = "SELECT c FROM Clicasolote c WHERE c.cliId = :cliId AND c.a01Id = :a01Id AND c.a03Concluido = 'false' ORDER BY c.a03Dt DESC"),
    @NamedQuery(name = "Clicasolote.findByA03Id", query = "SELECT c FROM Clicasolote c WHERE c.a03Id = :a03Id")
})
public class Clicasolote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "a03_id")
    private Long a03Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a03_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a03Dt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "a03_concluido")
    private String a03Concluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "a03Id")
    private List<Clicaso> clicasoList;
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
    @ManyToOne(optional = false)
    private Cliente cliId;
    @JoinColumn(name = "a01_id", referencedColumnName = "a01_id")
    @ManyToOne(optional = false)
    private Clicasotipolote a01Id;

    public Clicasolote() {
    }

    public Clicasolote(Long a03Id) {
        this.a03Id = a03Id;
    }

    public Clicasolote(Long a03Id, Date a03Dt, String a03Concluido) {
        this.a03Id = a03Id;
        this.a03Dt = a03Dt;
        this.a03Concluido = a03Concluido;
    }

    public Long getA03Id() {
        return a03Id;
    }

    public void setA03Id(Long a03Id) {
        this.a03Id = a03Id;
    }

    public Date getA03Dt() {
        return a03Dt;
    }

    public void setA03Dt(Date a03Dt) {
        this.a03Dt = a03Dt;
    }

    public String getA03Concluido() {
        return a03Concluido;
    }

    public void setA03Concluido(String a03Concluido) {
        this.a03Concluido = a03Concluido;
    }

    @XmlTransient
    public List<Clicaso> getClicasoList() {
        return clicasoList;
    }

    public void setClicasoList(List<Clicaso> clicasoList) {
        this.clicasoList = clicasoList;
    }

    public Cliente getCliId() {
        return cliId;
    }

    public void setCliId(Cliente cliId) {
        this.cliId = cliId;
    }

    public Clicasotipolote getA01Id() {
        return a01Id;
    }

    public void setA01Id(Clicasotipolote a01Id) {
        this.a01Id = a01Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (a03Id != null ? a03Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clicasolote)) {
            return false;
        }
        Clicasolote other = (Clicasolote) object;
        if ((this.a03Id == null && other.a03Id != null) || (this.a03Id != null && !this.a03Id.equals(other.a03Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clicasolote[ a03Id=" + a03Id + " ]";
    }
    
}
