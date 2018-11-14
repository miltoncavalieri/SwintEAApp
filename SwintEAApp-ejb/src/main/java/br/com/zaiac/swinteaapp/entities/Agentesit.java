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
@Table(name = "agentesit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agentesit.findAll", query = "SELECT a FROM Agentesit a"),
    @NamedQuery(name = "Agentesit.findByAgsId", query = "SELECT a FROM Agentesit a WHERE a.agsId = :agsId")
})
public class Agentesit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ags_id")
    private Short agsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "ags_nome")
    private String agsNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agsId")
    private List<Agente> agenteList;

    public Agentesit() {
    }

    public Agentesit(Short agsId) {
        this.agsId = agsId;
    }

    public Agentesit(Short agsId, String agsNome) {
        this.agsId = agsId;
        this.agsNome = agsNome;
    }

    public Short getAgsId() {
        return agsId;
    }

    public void setAgsId(Short agsId) {
        this.agsId = agsId;
    }

    public String getAgsNome() {
        return agsNome;
    }

    public void setAgsNome(String agsNome) {
        this.agsNome = agsNome;
    }

    @XmlTransient
    public List<Agente> getAgenteList() {
        return agenteList;
    }

    public void setAgenteList(List<Agente> agenteList) {
        this.agenteList = agenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agsId != null ? agsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Agentesit)) {
            return false;
        }
        Agentesit other = (Agentesit) object;
        if ((this.agsId == null && other.agsId != null) || (this.agsId != null && !this.agsId.equals(other.agsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Agentesit[ agsId=" + agsId + " ]";
    }
    
}
