package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "websrvjson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Websrvjson.findAll", query = "SELECT w FROM Websrvjson w"),
    @NamedQuery(name = "Websrvjson.findService", query = "SELECT w FROM Websrvjson w WHERE w.ambId = :ambId AND w.wbfId = :wbfId AND w.jsoOp = :jsoOp"),
    @NamedQuery(name = "Websrvjson.findServiceProducao", query = "SELECT w FROM Websrvjson w WHERE w.ambId = 'PRD' AND w.wbfId = :wbfId AND w.jsoOp = :jsoOp"),
    @NamedQuery(name = "Websrvjson.findServiceProducaoDatavalid", query = "SELECT w FROM Websrvjson w WHERE w.ambId = 'PRD' AND w.wbfId = 'DATAVALID' AND w.jsoOp = :jsoOp")    
})
public class Websrvjson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jso_id")
    private Integer jsoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "jso_op")
    private String jsoOp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "jso_url")
    private String jsoUrl;
    @JoinColumn(name = "amb_id", referencedColumnName = "amb_id")
    @ManyToOne(optional = false)
    private Ambiente ambId;
    @JoinColumn(name = "wbf_id", referencedColumnName = "wbf_id")
    @ManyToOne
    private Websrvfornec wbfId;

    public Websrvjson() {
    }

    public Websrvjson(Integer jsoId) {
        this.jsoId = jsoId;
    }

    public Websrvjson(Integer jsoId, String jsoOp, String jsoUrl) {
        this.jsoId = jsoId;
        this.jsoOp = jsoOp;
        this.jsoUrl = jsoUrl;
    }

    public Integer getJsoId() {
        return jsoId;
    }

    public void setJsoId(Integer jsoId) {
        this.jsoId = jsoId;
    }

    public String getJsoOp() {
        return jsoOp;
    }

    public void setJsoOp(String jsoOp) {
        this.jsoOp = jsoOp;
    }

    public String getJsoUrl() {
        return jsoUrl;
    }

    public void setJsoUrl(String jsoUrl) {
        this.jsoUrl = jsoUrl;
    }

    public Ambiente getAmbId() {
        return ambId;
    }

    public void setAmbId(Ambiente ambId) {
        this.ambId = ambId;
    }

    public Websrvfornec getWbfId() {
        return wbfId;
    }

    public void setWbfId(Websrvfornec wbfId) {
        this.wbfId = wbfId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jsoId != null ? jsoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Websrvjson)) {
            return false;
        }
        Websrvjson other = (Websrvjson) object;
        if ((this.jsoId == null && other.jsoId != null) || (this.jsoId != null && !this.jsoId.equals(other.jsoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Websrvjson[ jsoId=" + jsoId + " ]";
    }
    
}
