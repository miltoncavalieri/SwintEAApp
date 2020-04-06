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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "clicaso")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "Clicaso.findAll", query = "SELECT c FROM Clicaso c"),
    @NamedQuery(name = "Clicaso.findByA04IdCaso", query = "SELECT c FROM Clicaso c WHERE c.a04IdCaso = :a04IdCaso"),
    @NamedQuery(name = "Clicaso.deleteByA04IdCaso", query = "DELETE FROM Clicaso c WHERE c.a04IdCaso = :a04IdCaso")
}) 
public class Clicaso implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clicaso")
    private List<Clicasovalidacao> clicasovalidacaoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clicaso")
    private List<Clicasocheckpoint> clicasocheckpointList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "a04_id_caso")
    private Long a04IdCaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a04_id_caso_cliente")
    private long a04IdCasoCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a05_seq_ultimo_status")
    private int a05SeqUltimoStatus;
    @JoinColumn(name = "a03_id", referencedColumnName = "a03_id")
    @ManyToOne(optional = false)
    private Clicasolote a03Id;
    @OneToMany(mappedBy = "a04IdCaso")
    private List<Analise> analiseList;

    public Clicaso() {
    }

    public Clicaso(Long a04IdCaso) {
        this.a04IdCaso = a04IdCaso;
    }

    public Clicaso(Long a04IdCaso, long a04IdCasoCliente, int a05SeqUltimoStatus) {
        this.a04IdCaso = a04IdCaso;
        this.a04IdCasoCliente = a04IdCasoCliente;
        this.a05SeqUltimoStatus = a05SeqUltimoStatus;
    }

    public Long getA04IdCaso() {
        return a04IdCaso;
    }

    public void setA04IdCaso(Long a04IdCaso) {
        this.a04IdCaso = a04IdCaso;
    }

    public long getA04IdCasoCliente() {
        return a04IdCasoCliente;
    }

    public void setA04IdCasoCliente(long a04IdCasoCliente) {
        this.a04IdCasoCliente = a04IdCasoCliente;
    }

    public int getA05SeqUltimoStatus() {
        return a05SeqUltimoStatus;
    }

    public void setA05SeqUltimoStatus(int a05SeqUltimoStatus) {
        this.a05SeqUltimoStatus = a05SeqUltimoStatus;
    }

    public Clicasolote getA03Id() {
        return a03Id;
    }

    public void setA03Id(Clicasolote a03Id) {
        this.a03Id = a03Id;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (a04IdCaso != null ? a04IdCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clicaso)) {
            return false;
        }
        Clicaso other = (Clicaso) object;
        if ((this.a04IdCaso == null && other.a04IdCaso != null) || (this.a04IdCaso != null && !this.a04IdCaso.equals(other.a04IdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clicaso[ a04IdCaso=" + a04IdCaso + " ]";
    }

    @XmlTransient
    public List<Clicasocheckpoint> getClicasocheckpointList() {
        return clicasocheckpointList;
    }

    public void setClicasocheckpointList(List<Clicasocheckpoint> clicasocheckpointList) {
        this.clicasocheckpointList = clicasocheckpointList;
    }

    @XmlTransient
    public List<Clicasovalidacao> getClicasovalidacaoList() {
        return clicasovalidacaoList;
    }

    public void setClicasovalidacaoList(List<Clicasovalidacao> clicasovalidacaoList) {
        this.clicasovalidacaoList = clicasovalidacaoList;
    }
    
}
