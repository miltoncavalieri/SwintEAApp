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
@Table(name = "banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b")})
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "bco_id")
    private Integer bcoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "bco_nome")
    private String bcoNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bcoId")
    private List<Contagente> contagenteList;

    public Banco() {
    }

    public Banco(Integer bcoId) {
        this.bcoId = bcoId;
    }

    public Banco(Integer bcoId, String bcoNome) {
        this.bcoId = bcoId;
        this.bcoNome = bcoNome;
    }

    public Integer getBcoId() {
        return bcoId;
    }

    public void setBcoId(Integer bcoId) {
        this.bcoId = bcoId;
    }

    public String getBcoNome() {
        return bcoNome;
    }

    public void setBcoNome(String bcoNome) {
        this.bcoNome = bcoNome;
    }

    @XmlTransient
    public List<Contagente> getContagenteList() {
        return contagenteList;
    }

    public void setContagenteList(List<Contagente> contagenteList) {
        this.contagenteList = contagenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bcoId != null ? bcoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.bcoId == null && other.bcoId != null) || (this.bcoId != null && !this.bcoId.equals(other.bcoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Banco[ bcoId=" + bcoId + " ]";
    }
    
}
