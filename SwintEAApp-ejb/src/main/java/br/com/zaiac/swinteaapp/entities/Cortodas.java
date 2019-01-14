/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author root
 */
@Entity
@Table(name = "cortodas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cortodas.findAll", query = "SELECT c FROM Cortodas c")})
public class Cortodas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "cto_id")
    private String ctoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "cto_nome")
    private String ctoNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ctoId")
    private List<Cor> corList;

    public Cortodas() {
    }

    public Cortodas(String ctoId) {
        this.ctoId = ctoId;
    }

    public Cortodas(String ctoId, String ctoNome) {
        this.ctoId = ctoId;
        this.ctoNome = ctoNome;
    }

    public String getCtoId() {
        return ctoId;
    }

    public void setCtoId(String ctoId) {
        this.ctoId = ctoId;
    }

    public String getCtoNome() {
        return ctoNome;
    }

    public void setCtoNome(String ctoNome) {
        this.ctoNome = ctoNome;
    }

    @XmlTransient
    public List<Cor> getCorList() {
        return corList;
    }

    public void setCorList(List<Cor> corList) {
        this.corList = corList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctoId != null ? ctoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cortodas)) {
            return false;
        }
        Cortodas other = (Cortodas) object;
        if ((this.ctoId == null && other.ctoId != null) || (this.ctoId != null && !this.ctoId.equals(other.ctoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Cortodas[ ctoId=" + ctoId + " ]";
    }
    
}
