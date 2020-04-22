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
@Table(name = "ambiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambiente.findAll", query = "SELECT a FROM Ambiente a"),
    @NamedQuery(name = "Ambiente.findByAmbId", query = "SELECT a FROM Ambiente a WHERE a.ambId = :ambId")

})
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "amb_id")
    private String ambId;
    @Size(max = 30)
    @Column(name = "amb_descricao")
    private String ambDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambId")
    private List<Websrvjson> websrvjsonList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambId")
    private List<Websrvlogin> websrvloginList;

    public Ambiente() {
    }

    public Ambiente(String ambId) {
        this.ambId = ambId;
    }

    public String getAmbId() {
        return ambId;
    }

    public void setAmbId(String ambId) {
        this.ambId = ambId;
    }

    public String getAmbDescricao() {
        return ambDescricao;
    }

    public void setAmbDescricao(String ambDescricao) {
        this.ambDescricao = ambDescricao;
    }

    @XmlTransient
    public List<Websrvjson> getWebsrvjsonList() {
        return websrvjsonList;
    }

    public void setWebsrvjsonList(List<Websrvjson> websrvjsonList) {
        this.websrvjsonList = websrvjsonList;
    }

    @XmlTransient
    public List<Websrvlogin> getWebsrvloginList() {
        return websrvloginList;
    }

    public void setWebsrvloginList(List<Websrvlogin> websrvloginList) {
        this.websrvloginList = websrvloginList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ambId != null ? ambId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ambiente)) {
            return false;
        }
        Ambiente other = (Ambiente) object;
        if ((this.ambId == null && other.ambId != null) || (this.ambId != null && !this.ambId.equals(other.ambId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Ambiente[ ambId=" + ambId + " ]";
    }
    
}
