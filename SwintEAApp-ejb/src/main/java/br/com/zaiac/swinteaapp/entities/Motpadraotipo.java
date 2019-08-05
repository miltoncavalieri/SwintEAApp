/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author milton_cavalieri
 */
@Entity
@Table(name = "motpadraotipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motpadraotipo.findAll", query = "SELECT m FROM Motpadraotipo m")})
public class Motpadraotipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "mdo_id")
    private String mdoId;
    @OneToMany(mappedBy = "mdoId")
    private List<Motivopadrao> motivopadraoList;

    public Motpadraotipo() {
    }

    public Motpadraotipo(String mdoId) {
        this.mdoId = mdoId;
    }

    public String getMdoId() {
        return mdoId;
    }

    public void setMdoId(String mdoId) {
        this.mdoId = mdoId;
    }

    @XmlTransient
    public List<Motivopadrao> getMotivopadraoList() {
        return motivopadraoList;
    }

    public void setMotivopadraoList(List<Motivopadrao> motivopadraoList) {
        this.motivopadraoList = motivopadraoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdoId != null ? mdoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motpadraotipo)) {
            return false;
        }
        Motpadraotipo other = (Motpadraotipo) object;
        if ((this.mdoId == null && other.mdoId != null) || (this.mdoId != null && !this.mdoId.equals(other.mdoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Motpadraotipo[ mdoId=" + mdoId + " ]";
    }
    
}
