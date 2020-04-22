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
@Table(name = "websrvfornec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Websrvfornec.findAll", query = "SELECT w FROM Websrvfornec w"),
    @NamedQuery(name = "Websrvfornec.findByWbfId", query = "SELECT w FROM Websrvfornec w WHERE w.wbfId = :wbfId")
})
public class Websrvfornec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "wbf_id")
    private String wbfId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wbf_ativo")
    private short wbfAtivo;
    @OneToMany(mappedBy = "wbfId")
    private List<Websrvjson> websrvjsonList;
    @OneToMany(mappedBy = "wbfId")
    private List<Websrvlogin> websrvloginList;

    public Websrvfornec() {
    }

    public Websrvfornec(String wbfId) {
        this.wbfId = wbfId;
    }

    public Websrvfornec(String wbfId, short wbfAtivo) {
        this.wbfId = wbfId;
        this.wbfAtivo = wbfAtivo;
    }

    public String getWbfId() {
        return wbfId;
    }

    public void setWbfId(String wbfId) {
        this.wbfId = wbfId;
    }

    public short getWbfAtivo() {
        return wbfAtivo;
    }

    public void setWbfAtivo(short wbfAtivo) {
        this.wbfAtivo = wbfAtivo;
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
        hash += (wbfId != null ? wbfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Websrvfornec)) {
            return false;
        }
        Websrvfornec other = (Websrvfornec) object;
        if ((this.wbfId == null && other.wbfId != null) || (this.wbfId != null && !this.wbfId.equals(other.wbfId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Websrvfornec[ wbfId=" + wbfId + " ]";
    }
    
}
