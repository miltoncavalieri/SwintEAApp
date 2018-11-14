package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "lotercbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotercbo.findAll", query = "SELECT l FROM Lotercbo l"),
    @NamedQuery(name = "Lotercbo.findByLcbId", query = "SELECT l FROM Lotercbo l WHERE l.lcbId = :lcbId"),
    @NamedQuery(name = "Lotercbo.deleteByLcbId", query = "DELETE FROM Lotercbo l WHERE l.lcbId = :lcbId")

})
public class Lotercbo implements Serializable {

    @OneToMany(mappedBy = "lcbId")
    private List<Recibo> reciboList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lcb_id")
    private Long lcbId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lcb_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lcbDt;
    @Size(max = 60)
    @Column(name = "lcb_descricao")
    private String lcbDescricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lcb_dt_previsao")
    @Temporal(TemporalType.DATE)
    private Date lcbDtPrevisao;
    @Column(name = "lcb_dt_pagto")
    @Temporal(TemporalType.DATE)
    private Date lcbDtPagto;
    @OneToMany(mappedBy = "lcbId")
    private List<Recebimento> recebimentoList;
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
    @ManyToOne(optional = false)
    private Cliente cliId;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuId;

    public Lotercbo() {
    }

    public Lotercbo(Long lcbId) {
        this.lcbId = lcbId;
    }

    public Lotercbo(Long lcbId, Date lcbDt, Date lcbDtPrevisao) {
        this.lcbId = lcbId;
        this.lcbDt = lcbDt;
        this.lcbDtPrevisao = lcbDtPrevisao;
    }

    public Long getLcbId() {
        return lcbId;
    }

    public void setLcbId(Long lcbId) {
        this.lcbId = lcbId;
    }

    public Date getLcbDt() {
        return lcbDt;
    }

    public void setLcbDt(Date lcbDt) {
        this.lcbDt = lcbDt;
    }

    public String getLcbDescricao() {
        return lcbDescricao;
    }

    public void setLcbDescricao(String lcbDescricao) {
        this.lcbDescricao = lcbDescricao;
    }

    public Date getLcbDtPrevisao() {
        return lcbDtPrevisao;
    }

    public void setLcbDtPrevisao(Date lcbDtPrevisao) {
        this.lcbDtPrevisao = lcbDtPrevisao;
    }

    public Date getLcbDtPagto() {
        return lcbDtPagto;
    }

    public void setLcbDtPagto(Date lcbDtPagto) {
        this.lcbDtPagto = lcbDtPagto;
    }

    @XmlTransient
    public List<Recebimento> getRecebimentoList() {
        return recebimentoList;
    }

    public void setRecebimentoList(List<Recebimento> recebimentoList) {
        this.recebimentoList = recebimentoList;
    }

    public Cliente getCliId() {
        return cliId;
    }

    public void setCliId(Cliente cliId) {
        this.cliId = cliId;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lcbId != null ? lcbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Lotercbo)) {
            return false;
        }
        Lotercbo other = (Lotercbo) object;
        if ((this.lcbId == null && other.lcbId != null) || (this.lcbId != null && !this.lcbId.equals(other.lcbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Lotercbo[ lcbId=" + lcbId + " ]";
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }
    
}
