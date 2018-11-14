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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "tipopes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipopes.findAll", query = "SELECT t FROM Tipopes t")})
public class Tipopes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesId")
    private List<Contagente> contagenteList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pes_id")
    private Short pesId;
    @Size(max = 10)
    @Column(name = "pes_nome")
    private String pesNome;
    @OneToMany(mappedBy = "pesId")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesId")
    private List<Usuario> usuarioList;

    public Tipopes() {
    }

    public Tipopes(Short pesId) {
        this.pesId = pesId;
    }

    public Short getPesId() {
        return pesId;
    }

    public void setPesId(Short pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pesId != null ? pesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tipopes)) {
            return false;
        }
        Tipopes other = (Tipopes) object;
        if ((this.pesId == null && other.pesId != null) || (this.pesId != null && !this.pesId.equals(other.pesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Tipopes[ pesId=" + pesId + " ]";
    }

    @XmlTransient
    public List<Contagente> getContagenteList() {
        return contagenteList;
    }

    public void setContagenteList(List<Contagente> contagenteList) {
        this.contagenteList = contagenteList;
    }
    
}
