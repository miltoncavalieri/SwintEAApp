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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "mobapp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobapp.findAll", query = "SELECT m FROM Mobapp m")})
public class Mobapp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mob_id")
    private Integer mobId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "mob_nome")
    private String mobNome;
    @OneToMany(mappedBy = "mobId")
    private List<Analise> analiseList;

    public Mobapp() {
    }

    public Mobapp(Integer mobId) {
        this.mobId = mobId;
    }

    public Mobapp(Integer mobId, String mobNome) {
        this.mobId = mobId;
        this.mobNome = mobNome;
    }

    public Integer getMobId() {
        return mobId;
    }

    public void setMobId(Integer mobId) {
        this.mobId = mobId;
    }

    public String getMobNome() {
        return mobNome;
    }

    public void setMobNome(String mobNome) {
        this.mobNome = mobNome;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mobId != null ? mobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobapp)) {
            return false;
        }
        Mobapp other = (Mobapp) object;
        if ((this.mobId == null && other.mobId != null) || (this.mobId != null && !this.mobId.equals(other.mobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Mobapp[ mobId=" + mobId + " ]";
    }
    
}
