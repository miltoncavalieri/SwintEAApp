package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "sysparam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sysparam.findAll", query = "SELECT s FROM Sysparam s"),
    @NamedQuery(name = "Sysparam.findByPrmNome", query = "SELECT s FROM Sysparam s WHERE s.prmNome = :prmNome")})
public class Sysparam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prm_id")
    private Integer prmId;
    @Column(name = "usu_id")
    private Integer usuId;
    @Size(max = 100)
    @Column(name = "prm_nome")
    private String prmNome;
    @Size(max = 100)
    @Column(name = "prm_value")
    private String prmValue;

    public Sysparam() {
    }

    public Sysparam(Integer prmId) {
        this.prmId = prmId;
    }

    public Integer getPrmId() {
        return prmId;
    }

    public void setPrmId(Integer prmId) {
        this.prmId = prmId;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getPrmNome() {
        return prmNome;
    }

    public void setPrmNome(String prmNome) {
        this.prmNome = prmNome;
    }

    public String getPrmValue() {
        return prmValue;
    }

    public void setPrmValue(String prmValue) {
        this.prmValue = prmValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prmId != null ? prmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sysparam)) {
            return false;
        }
        Sysparam other = (Sysparam) object;
        if ((this.prmId == null && other.prmId != null) || (this.prmId != null && !this.prmId.equals(other.prmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Sysparam[ prmId=" + prmId + " ]";
    }
    
}
