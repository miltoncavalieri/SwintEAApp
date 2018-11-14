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
@Table(name = "pedbusit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedbusit.findAll", query = "SELECT p FROM Pedbusit p"),
    @NamedQuery(name = "Pedbusit.findByPbsId", query = "SELECT p FROM Pedbusit p WHERE p.pbsId = :pbsId")})
public class Pedbusit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbs_id")
    private Short pbsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pbs_nome")
    private String pbsNome;
    @OneToMany(mappedBy = "pbsIdPre")
    private List<Pedbus> pedbusList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pbsId")
    private List<Pedbus> pedbusList1;

    public Pedbusit() {
    }

    public Pedbusit(Short pbsId) {
        this.pbsId = pbsId;
    }

    public Pedbusit(Short pbsId, String pbsNome) {
        this.pbsId = pbsId;
        this.pbsNome = pbsNome;
    }

    public Short getPbsId() {
        return pbsId;
    }

    public void setPbsId(Short pbsId) {
        this.pbsId = pbsId;
    }

    public String getPbsNome() {
        return pbsNome;
    }

    public void setPbsNome(String pbsNome) {
        this.pbsNome = pbsNome;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList() {
        return pedbusList;
    }

    public void setPedbusList(List<Pedbus> pedbusList) {
        this.pedbusList = pedbusList;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList1() {
        return pedbusList1;
    }

    public void setPedbusList1(List<Pedbus> pedbusList1) {
        this.pedbusList1 = pedbusList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pbsId != null ? pbsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pedbusit)) {
            return false;
        }
        Pedbusit other = (Pedbusit) object;
        if ((this.pbsId == null && other.pbsId != null) || (this.pbsId != null && !this.pbsId.equals(other.pbsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Pedbusit[ pbsId=" + pbsId + " ]";
    }
    
}
