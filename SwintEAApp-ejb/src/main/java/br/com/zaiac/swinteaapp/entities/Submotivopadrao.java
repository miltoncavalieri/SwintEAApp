package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "submotivopadrao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Submotivopadrao.findAll", query = "SELECT s FROM Submotivopadrao s"),
    @NamedQuery(name = "Submotivopadrao.findBySmpId", query = "SELECT s FROM Submotivopadrao s WHERE s.smpId = :smpId")
})
public class Submotivopadrao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "smp_id")
    private Integer smpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "smp_descricao")
    private String smpDescricao;
    @OneToMany(mappedBy = "smpId")
    private List<Checkpoint> checkpointList;
    @JoinColumn(name = "mop_id", referencedColumnName = "mop_id")
    @ManyToOne(optional = false)
    private Motivopadrao mopId;

    public Submotivopadrao() {
    }

    public Submotivopadrao(Integer smpId) {
        this.smpId = smpId;
    }

    public Submotivopadrao(Integer smpId, String smpDescricao) {
        this.smpId = smpId;
        this.smpDescricao = smpDescricao;
    }

    public Integer getSmpId() {
        return smpId;
    }

    public void setSmpId(Integer smpId) {
        this.smpId = smpId;
    }

    public String getSmpDescricao() {
        return smpDescricao;
    }

    public void setSmpDescricao(String smpDescricao) {
        this.smpDescricao = smpDescricao;
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }

    public Motivopadrao getMopId() {
        return mopId;
    }

    public void setMopId(Motivopadrao mopId) {
        this.mopId = mopId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smpId != null ? smpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Submotivopadrao)) {
            return false;
        }
        Submotivopadrao other = (Submotivopadrao) object;
        if ((this.smpId == null && other.smpId != null) || (this.smpId != null && !this.smpId.equals(other.smpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Submotivopadrao[ smpId=" + smpId + " ]";
    }
    
}
