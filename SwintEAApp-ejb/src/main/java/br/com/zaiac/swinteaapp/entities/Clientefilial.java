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
@Table(name = "clientefilial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientefilial.findAll", query = "SELECT c FROM Clientefilial c")})
public class Clientefilial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fil_id")
    private Long filId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fil_agencia")
    private String filAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fil_regional")
    private String filRegional;
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
    @ManyToOne(optional = false)
    private Cliente cliId;
    @JoinColumn(name = "reg_id", referencedColumnName = "reg_id")
    @ManyToOne(optional = false)
    private Regiao regId;
    @OneToMany(mappedBy = "filId")
    private List<Analise> analiseList;

    public Clientefilial() {
    }

    public Clientefilial(Long filId) {
        this.filId = filId;
    }

    public Clientefilial(Long filId, String filAgencia, String filRegional) {
        this.filId = filId;
        this.filAgencia = filAgencia;
        this.filRegional = filRegional;
    }

    public Long getFilId() {
        return filId;
    }

    public void setFilId(Long filId) {
        this.filId = filId;
    }

    public String getFilAgencia() {
        return filAgencia;
    }

    public void setFilAgencia(String filAgencia) {
        this.filAgencia = filAgencia;
    }

    public String getFilRegional() {
        return filRegional;
    }

    public void setFilRegional(String filRegional) {
        this.filRegional = filRegional;
    }

    public Cliente getCliId() {
        return cliId;
    }

    public void setCliId(Cliente cliId) {
        this.cliId = cliId;
    }

    public Regiao getRegId() {
        return regId;
    }

    public void setRegId(Regiao regId) {
        this.regId = regId;
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
        hash += (filId != null ? filId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientefilial)) {
            return false;
        }
        Clientefilial other = (Clientefilial) object;
        if ((this.filId == null && other.filId != null) || (this.filId != null && !this.filId.equals(other.filId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clientefilial[ filId=" + filId + " ]";
    }
    
}
