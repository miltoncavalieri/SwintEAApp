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

/**
 *
 * @author root
 */
@Entity
@Table(name = "modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m")})
public class Modelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mod_id")
    private Integer modId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "mod_nome")
    private String modNome;
    @JoinColumn(name = "fab_id", referencedColumnName = "fab_id")
    @ManyToOne(optional = false)
    private Fabricante fabId;
    @OneToMany(mappedBy = "modId")
    private List<Analise> analiseList;

    public Modelo() {
    }

    public Modelo(Integer modId) {
        this.modId = modId;
    }

    public Modelo(Integer modId, String modNome) {
        this.modId = modId;
        this.modNome = modNome;
    }

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    public String getModNome() {
        return modNome;
    }

    public void setModNome(String modNome) {
        this.modNome = modNome;
    }

    public Fabricante getFabId() {
        return fabId;
    }

    public void setFabId(Fabricante fabId) {
        this.fabId = fabId;
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
        hash += (modId != null ? modId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.modId == null && other.modId != null) || (this.modId != null && !this.modId.equals(other.modId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Modelo[ modId=" + modId + " ]";
    }
    
}
