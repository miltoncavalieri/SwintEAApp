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
@Table(name = "websrvlogin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Websrvlogin.findAll", query = "SELECT w FROM Websrvlogin w"),
    @NamedQuery(name = "Websrvlogin.findLogin", query = "SELECT w FROM Websrvlogin w WHERE w.ambId = :ambId AND w.wbfId = :wbfId")})
public class Websrvlogin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jsl_id")
    private Integer jslId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "jsl_login")
    private String jslLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "jsl_password")
    private String jslPassword;
    @JoinColumn(name = "amb_id", referencedColumnName = "amb_id")
    @ManyToOne(optional = false)
    private Ambiente ambId;
    @JoinColumn(name = "wbf_id", referencedColumnName = "wbf_id")
    @ManyToOne
    private Websrvfornec wbfId;

    public Websrvlogin() {
    }

    public Websrvlogin(Integer jslId) {
        this.jslId = jslId;
    }

    public Websrvlogin(Integer jslId, String jslLogin, String jslPassword) {
        this.jslId = jslId;
        this.jslLogin = jslLogin;
        this.jslPassword = jslPassword;
    }

    public Integer getJslId() {
        return jslId;
    }

    public void setJslId(Integer jslId) {
        this.jslId = jslId;
    }

    public String getJslLogin() {
        return jslLogin;
    }

    public void setJslLogin(String jslLogin) {
        this.jslLogin = jslLogin;
    }

    public String getJslPassword() {
        return jslPassword;
    }

    public void setJslPassword(String jslPassword) {
        this.jslPassword = jslPassword;
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
        hash += (jslId != null ? jslId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Websrvlogin)) {
            return false;
        }
        Websrvlogin other = (Websrvlogin) object;
        if ((this.jslId == null && other.jslId != null) || (this.jslId != null && !this.jslId.equals(other.jslId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Websrvlogin[ jslId=" + jslId + " ]";
    }
    
}
