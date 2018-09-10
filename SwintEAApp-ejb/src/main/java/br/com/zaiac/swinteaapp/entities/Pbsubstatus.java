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
 * @author root
 */
@Entity
@Table(name = "pbsubstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pbsubstatus.findAll", query = "SELECT p FROM Pbsubstatus p")})
public class Pbsubstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pus_id")
    private Short pusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pus_nome")
    private String pusNome;
    @OneToMany(mappedBy = "pusId")
    private List<Pedbus> pedbusList;

    public Pbsubstatus() {
    }

    public Pbsubstatus(Short pusId) {
        this.pusId = pusId;
    }

    public Pbsubstatus(Short pusId, String pusNome) {
        this.pusId = pusId;
        this.pusNome = pusNome;
    }

    public Short getPusId() {
        return pusId;
    }

    public void setPusId(Short pusId) {
        this.pusId = pusId;
    }

    public String getPusNome() {
        return pusNome;
    }

    public void setPusNome(String pusNome) {
        this.pusNome = pusNome;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList() {
        return pedbusList;
    }

    public void setPedbusList(List<Pedbus> pedbusList) {
        this.pedbusList = pedbusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pusId != null ? pusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pbsubstatus)) {
            return false;
        }
        Pbsubstatus other = (Pbsubstatus) object;
        if ((this.pusId == null && other.pusId != null) || (this.pusId != null && !this.pusId.equals(other.pusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Pbsubstatus[ pusId=" + pusId + " ]";
    }
    
}
