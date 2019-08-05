package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "equipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipe.findAll", query = "SELECT e FROM Equipe e"),
    @NamedQuery(name = "Equipe.findByEuiId", query = "SELECT e FROM Equipe e WHERE e.euiId = :euiId")})
public class Equipe implements Serializable {

    @OneToMany(mappedBy = "euiId")
    private List<Analise> analiseList;

    @OneToMany(mappedBy = "euiId")
    private List<Checkpoint> checkpointList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eui_id")
    private Integer euiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "eui_nome")
    private String euiNome;
    @OneToMany(mappedBy = "euiId")
    private List<Pedbus> pedbusList;

    public Equipe() {
    }

    public Equipe(Integer euiId) {
        this.euiId = euiId;
    }

    public Equipe(Integer euiId, String euiNome) {
        this.euiId = euiId;
        this.euiNome = euiNome;
    }

    public Integer getEuiId() {
        return euiId;
    }

    public void setEuiId(Integer euiId) {
        this.euiId = euiId;
    }

    public String getEuiNome() {
        return euiNome;
    }

    public void setEuiNome(String euiNome) {
        this.euiNome = euiNome;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList() {
        return pedbusList;
    }

    public void setPedbusList(List<Pedbus> pedbusList) {
        this.pedbusList = pedbusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (euiId != null ? euiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.euiId == null && other.euiId != null) || (this.euiId != null && !this.euiId.equals(other.euiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Equipe[ euiId=" + euiId + " ]";
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }
    
}
