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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "est_id")
    private Short estId;
    @Size(max = 30)
    @Column(name = "est_nome")
    private String estNome;
    @Size(max = 2)
    @Column(name = "est_uf")
    private String estUf;
    @JoinColumn(name = "reg_id", referencedColumnName = "reg_id")
    @ManyToOne
    private Regiao regId;
    @OneToMany(mappedBy = "estIdInvest")
    private List<Pedbus> pedbusList;
    @OneToMany(mappedBy = "estIdRecup")
    private List<Pedbus> pedbusList1;
    @OneToMany(mappedBy = "estIdLocacao")
    private List<Analise> analiseList;
    @OneToMany(mappedBy = "estIdDevolucao")
    private List<Analise> analiseList1;

    public Estado() {
    }

    public Estado(Short estId) {
        this.estId = estId;
    }

    public Short getEstId() {
        return estId;
    }

    public void setEstId(Short estId) {
        this.estId = estId;
    }

    public String getEstNome() {
        return estNome;
    }

    public void setEstNome(String estNome) {
        this.estNome = estNome;
    }

    public String getEstUf() {
        return estUf;
    }

    public void setEstUf(String estUf) {
        this.estUf = estUf;
    }

    public Regiao getRegId() {
        return regId;
    }

    public void setRegId(Regiao regId) {
        this.regId = regId;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList() {
        return pedbusList;
    }

    public void setPedbusList(List<Pedbus> pedbusList) {
        this.pedbusList = pedbusList;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList1() {
        return pedbusList1;
    }

    public void setPedbusList1(List<Pedbus> pedbusList1) {
        this.pedbusList1 = pedbusList1;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @XmlTransient
    public List<Analise> getAnaliseList1() {
        return analiseList1;
    }

    public void setAnaliseList1(List<Analise> analiseList1) {
        this.analiseList1 = analiseList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estId != null ? estId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.estId == null && other.estId != null) || (this.estId != null && !this.estId.equals(other.estId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Estado[ estId=" + estId + " ]";
    }
    
}
