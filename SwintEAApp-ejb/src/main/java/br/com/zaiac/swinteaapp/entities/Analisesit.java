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
@Table(name = "analisesit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analisesit.findAll", query = "SELECT a FROM Analisesit a")})
public class Analisesit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sit_id")
    private Short sitId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "sit_nome")
    private String sitNome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sit_interna")
    private short sitInterna;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sitId")
    private List<Analise> analiseList;

    public Analisesit() {
    }

    public Analisesit(Short sitId) {
        this.sitId = sitId;
    }

    public Analisesit(Short sitId, String sitNome, short sitInterna) {
        this.sitId = sitId;
        this.sitNome = sitNome;
        this.sitInterna = sitInterna;
    }

    public Short getSitId() {
        return sitId;
    }

    public void setSitId(Short sitId) {
        this.sitId = sitId;
    }

    public String getSitNome() {
        return sitNome;
    }

    public void setSitNome(String sitNome) {
        this.sitNome = sitNome;
    }

    public short getSitInterna() {
        return sitInterna;
    }

    public void setSitInterna(short sitInterna) {
        this.sitInterna = sitInterna;
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
        hash += (sitId != null ? sitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analisesit)) {
            return false;
        }
        Analisesit other = (Analisesit) object;
        if ((this.sitId == null && other.sitId != null) || (this.sitId != null && !this.sitId.equals(other.sitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Analisesit[ sitId=" + sitId + " ]";
    }
    
}
