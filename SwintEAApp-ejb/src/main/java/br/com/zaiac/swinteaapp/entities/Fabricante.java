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
@Table(name = "fabricante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fabricante.findAll", query = "SELECT f FROM Fabricante f")})
public class Fabricante implements Serializable {

    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
    @ManyToOne
    private Cliente cliId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fab_id")
    private Integer fabId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "fab_nome")
    private String fabNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fabId")
    private List<Modelo> modeloList;

    public Fabricante() {
    }

    public Fabricante(Integer fabId) {
        this.fabId = fabId;
    }

    public Fabricante(Integer fabId, String fabNome) {
        this.fabId = fabId;
        this.fabNome = fabNome;
    }

    public Integer getFabId() {
        return fabId;
    }

    public void setFabId(Integer fabId) {
        this.fabId = fabId;
    }

    public String getFabNome() {
        return fabNome;
    }

    public void setFabNome(String fabNome) {
        this.fabNome = fabNome;
    }

    @XmlTransient
    public List<Modelo> getModeloList() {
        return modeloList;
    }

    public void setModeloList(List<Modelo> modeloList) {
        this.modeloList = modeloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fabId != null ? fabId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Fabricante)) {
            return false;
        }
        Fabricante other = (Fabricante) object;
        if ((this.fabId == null && other.fabId != null) || (this.fabId != null && !this.fabId.equals(other.fabId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Fabricante[ fabId=" + fabId + " ]";
    }

    public Cliente getCliId() {
        return cliId;
    }

    public void setCliId(Cliente cliId) {
        this.cliId = cliId;
    }
    
}
