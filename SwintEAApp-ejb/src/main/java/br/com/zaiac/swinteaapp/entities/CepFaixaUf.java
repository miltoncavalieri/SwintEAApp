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
@Table(name = "cep_faixa_uf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CepFaixaUf.findAll", query = "SELECT c FROM CepFaixaUf c")})
public class CepFaixaUf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ufe_sg")
    private String ufeSg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ufe_cep_ini")
    private String ufeCepIni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ufe_cep_fim")
    private String ufeCepFim;
    @OneToMany(mappedBy = "ufeSg")
    private List<CepLocalidade> cepLocalidadeList;

    public CepFaixaUf() {
    }

    public CepFaixaUf(String ufeSg) {
        this.ufeSg = ufeSg;
    }

    public CepFaixaUf(String ufeSg, String ufeCepIni, String ufeCepFim) {
        this.ufeSg = ufeSg;
        this.ufeCepIni = ufeCepIni;
        this.ufeCepFim = ufeCepFim;
    }

    public String getUfeSg() {
        return ufeSg;
    }

    public void setUfeSg(String ufeSg) {
        this.ufeSg = ufeSg;
    }

    public String getUfeCepIni() {
        return ufeCepIni;
    }

    public void setUfeCepIni(String ufeCepIni) {
        this.ufeCepIni = ufeCepIni;
    }

    public String getUfeCepFim() {
        return ufeCepFim;
    }

    public void setUfeCepFim(String ufeCepFim) {
        this.ufeCepFim = ufeCepFim;
    }

    @XmlTransient
    public List<CepLocalidade> getCepLocalidadeList() {
        return cepLocalidadeList;
    }

    public void setCepLocalidadeList(List<CepLocalidade> cepLocalidadeList) {
        this.cepLocalidadeList = cepLocalidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ufeSg != null ? ufeSg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CepFaixaUf)) {
            return false;
        }
        CepFaixaUf other = (CepFaixaUf) object;
        if ((this.ufeSg == null && other.ufeSg != null) || (this.ufeSg != null && !this.ufeSg.equals(other.ufeSg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.CepFaixaUf[ ufeSg=" + ufeSg + " ]";
    }
    
}
