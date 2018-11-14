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
@Table(name = "recibotipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibotipo.findAll", query = "SELECT r FROM Recibotipo r")})
public class Recibotipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "roo_id")
    private Short rooId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "roo_nome")
    private String rooNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rooId")
    private List<Recibo> reciboList;

    public Recibotipo() {
    }

    public Recibotipo(Short rooId) {
        this.rooId = rooId;
    }

    public Recibotipo(Short rooId, String rooNome) {
        this.rooId = rooId;
        this.rooNome = rooNome;
    }

    public Short getRooId() {
        return rooId;
    }

    public void setRooId(Short rooId) {
        this.rooId = rooId;
    }

    public String getRooNome() {
        return rooNome;
    }

    public void setRooNome(String rooNome) {
        this.rooNome = rooNome;
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rooId != null ? rooId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Recibotipo)) {
            return false;
        }
        Recibotipo other = (Recibotipo) object;
        if ((this.rooId == null && other.rooId != null) || (this.rooId != null && !this.rooId.equals(other.rooId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Recibotipo[ rooId=" + rooId + " ]";
    }
    
}
