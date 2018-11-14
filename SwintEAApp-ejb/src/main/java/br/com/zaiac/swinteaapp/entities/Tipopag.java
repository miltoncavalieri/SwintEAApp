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

@Entity
@Table(name = "tipopag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipopag.findAll", query = "SELECT t FROM Tipopag t"),
    @NamedQuery(name = "Tipopag.findByTppId", query = "SELECT t FROM Tipopag t WHERE t.tppId = :tppId")
})
public class Tipopag implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tppId")
    private List<Recibo> reciboList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tpp_id")
    private Short tppId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "tpp_nome")
    private String tppNome;
    @Column(name = "tpp_pagage")
    private Short tppPagage;
    @Column(name = "tpp_pagcli")
    private Short tppPagcli;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tppId")
    private List<Recebimento> recebimentoList;
    @OneToMany(mappedBy = "tppId")
    private List<Pagamento> pagamentoList;

    public Tipopag() {
    }

    public Tipopag(Short tppId) {
        this.tppId = tppId;
    }

    public Tipopag(Short tppId, String tppNome) {
        this.tppId = tppId;
        this.tppNome = tppNome;
    }

    public Short getTppId() {
        return tppId;
    }

    public void setTppId(Short tppId) {
        this.tppId = tppId;
    }

    public String getTppNome() {
        return tppNome;
    }

    public void setTppNome(String tppNome) {
        this.tppNome = tppNome;
    }

    public Short getTppPagage() {
        return tppPagage;
    }

    public void setTppPagage(Short tppPagage) {
        this.tppPagage = tppPagage;
    }

    public Short getTppPagcli() {
        return tppPagcli;
    }

    public void setTppPagcli(Short tppPagcli) {
        this.tppPagcli = tppPagcli;
    }

    @XmlTransient
    public List<Recebimento> getRecebimentoList() {
        return recebimentoList;
    }

    public void setRecebimentoList(List<Recebimento> recebimentoList) {
        this.recebimentoList = recebimentoList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tppId != null ? tppId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tipopag)) {
            return false;
        }
        Tipopag other = (Tipopag) object;
        if ((this.tppId == null && other.tppId != null) || (this.tppId != null && !this.tppId.equals(other.tppId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Tipopag[ tppId=" + tppId + " ]";
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }
    
}
