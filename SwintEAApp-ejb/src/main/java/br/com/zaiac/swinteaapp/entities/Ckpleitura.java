package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ckpleitura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ckpleitura.findAll", query = "SELECT c FROM Ckpleitura c"),
    @NamedQuery(name = "Ckpleitura.updateUltimaLeitura", 
            query = "UPDATE Ckpleitura c SET c.pclDtLida = CURRENT_TIMESTAMP WHERE c.pbuId = :pbuId and c.usuId = :usuId")
    })
public class Ckpleitura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pcl_id")
    private Long pclId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pcl_dt_lida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pclDtLida;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuId;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne(optional = false)
    private Analise pbuId;

    public Ckpleitura() {
    }

    public Ckpleitura(Long pclId) {
        this.pclId = pclId;
    }

    public Ckpleitura(Long pclId, Date pclDtLida) {
        this.pclId = pclId;
        this.pclDtLida = pclDtLida;
    }

    public Long getPclId() {
        return pclId;
    }

    public void setPclId(Long pclId) {
        this.pclId = pclId;
    }

    public Date getPclDtLida() {
        return pclDtLida;
    }

    public void setPclDtLida(Date pclDtLida) {
        this.pclDtLida = pclDtLida;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    public Analise getPbuId() {
        return pbuId;
    }

    public void setPbuId(Analise pbuId) {
        this.pbuId = pbuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pclId != null ? pclId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ckpleitura)) {
            return false;
        }
        Ckpleitura other = (Ckpleitura) object;
        if ((this.pclId == null && other.pclId != null) || (this.pclId != null && !this.pclId.equals(other.pclId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Ckpleitura[ pclId=" + pclId + " ]";
    }
    
}
