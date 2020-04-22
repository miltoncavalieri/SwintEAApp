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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "motivopadrao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motivopadrao.findAll", query = "SELECT m FROM Motivopadrao m"),
    @NamedQuery(name = "Motivopadrao.findByMopId", query = "SELECT m FROM Motivopadrao m WHERE m.mopId = :mopId")
})
public class Motivopadrao implements Serializable {

    @OneToMany(mappedBy = "mopId")
    private List<Checkpoint> checkpointList;
    @JoinColumn(name = "mdo_id", referencedColumnName = "mdo_id")
    @ManyToOne
    private Motpadraotipo mdoId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mop_id")
    private Integer mopId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mop_descricao")
    private String mopDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mopId")
    private List<Submotivopadrao> submotivopadraoList;

    public Motivopadrao() {
    }

    public Motivopadrao(Integer mopId) {
        this.mopId = mopId;
    }

    public Motivopadrao(Integer mopId, String mopDescricao) {
        this.mopId = mopId;
        this.mopDescricao = mopDescricao;
    }

    public Integer getMopId() {
        return mopId;
    }

    public void setMopId(Integer mopId) {
        this.mopId = mopId;
    }

    public String getMopDescricao() {
        return mopDescricao;
    }

    public void setMopDescricao(String mopDescricao) {
        this.mopDescricao = mopDescricao;
    }

    @XmlTransient
    public List<Submotivopadrao> getSubmotivopadraoList() {
        return submotivopadraoList;
    }

    public void setSubmotivopadraoList(List<Submotivopadrao> submotivopadraoList) {
        this.submotivopadraoList = submotivopadraoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mopId != null ? mopId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Motivopadrao)) {
            return false;
        }
        Motivopadrao other = (Motivopadrao) object;
        if ((this.mopId == null && other.mopId != null) || (this.mopId != null && !this.mopId.equals(other.mopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Motivopadrao[ mopId=" + mopId + " ]";
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }

    public Motpadraotipo getMdoId() {
        return mdoId;
    }

    public void setMdoId(Motpadraotipo mdoId) {
        this.mdoId = mdoId;
    }
    
}
