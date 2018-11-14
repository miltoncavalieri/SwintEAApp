package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pedbusrel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedbusrel.findAll", query = "SELECT p FROM Pedbusrel p"),
    @NamedQuery(name = "Pedbusrel.deleteByPckId", query = "DELETE FROM Pedbusrel p WHERE p.pckId = :pckId")
})
public class Pedbusrel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Lob
    @Size(max = 65535)
    @Column(name = "pbr_investigacao")
    private String pbrInvestigacao;
    @Lob
    @Size(max = 65535)
    @Column(name = "pbr_devveiculo")
    private String pbrDevveiculo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_id")
    private Long pckId;
    @JoinColumn(name = "pck_id", referencedColumnName = "pck_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Checkpoint checkpoint;

    public Pedbusrel() {
    }

    public Pedbusrel(Long pckId) {
        this.pckId = pckId;
    }

    public String getPbrInvestigacao() {
        return pbrInvestigacao;
    }

    public void setPbrInvestigacao(String pbrInvestigacao) {
        this.pbrInvestigacao = pbrInvestigacao;
    }

    public String getPbrDevveiculo() {
        return pbrDevveiculo;
    }

    public void setPbrDevveiculo(String pbrDevveiculo) {
        this.pbrDevveiculo = pbrDevveiculo;
    }

    public Long getPckId() {
        return pckId;
    }

    public void setPckId(Long pckId) {
        this.pckId = pckId;
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pckId != null ? pckId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pedbusrel)) {
            return false;
        }
        Pedbusrel other = (Pedbusrel) object;
        if ((this.pckId == null && other.pckId != null) || (this.pckId != null && !this.pckId.equals(other.pckId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Pedbusrel[ pckId=" + pckId + " ]";
    }
    
}
