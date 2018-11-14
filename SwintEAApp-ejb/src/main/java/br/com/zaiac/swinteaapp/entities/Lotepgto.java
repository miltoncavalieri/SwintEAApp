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
@Table(name = "lotepgto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotepgto.findAll", query = "SELECT l FROM Lotepgto l"),
    @NamedQuery(name = "Lotepgto.findByLopId", query = "SELECT l FROM Lotepgto l WHERE l.lopId = :lopId"),
    @NamedQuery(name = "Lotepgto.deleteByLopId", query = "DELETE FROM Lotepgto l WHERE l.lopId = :lopId")
})
public class Lotepgto implements Serializable {

    @OneToMany(mappedBy = "lopId")
    private List<Recibo> reciboList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lop_id")
    private Long lopId;
    @Column(name = "lop_dt_pagto")
    @Temporal(TemporalType.DATE)
    private Date lopDtPagto;
    @Size(max = 20)
    @Column(name = "lop_comprovante")
    private String lopComprovante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "lop_descricao")
    private String lopDescricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lop_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lopDt;
    @Column(name = "lop_dt_previsao")
    @Temporal(TemporalType.DATE)
    private Date lopDtPrevisao;
    @JoinColumn(name = "cta_id", referencedColumnName = "cta_id")
    @ManyToOne
    private Contagente ctaId;
    @JoinColumn(name = "usu_id_aprovador", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario usuIdAprovador;
    @JoinColumn(name = "usu_id_agente", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuIdAgente;
    @OneToMany(mappedBy = "lopId")
    private List<Pagamento> pagamentoList;

    public Lotepgto() {
    }

    public Lotepgto(Long lopId) {
        this.lopId = lopId;
    }

    public Lotepgto(Long lopId, String lopDescricao, Date lopDt) {
        this.lopId = lopId;
        this.lopDescricao = lopDescricao;
        this.lopDt = lopDt;
    }

    public Long getLopId() {
        return lopId;
    }

    public void setLopId(Long lopId) {
        this.lopId = lopId;
    }

    public Date getLopDtPagto() {
        return lopDtPagto;
    }

    public void setLopDtPagto(Date lopDtPagto) {
        this.lopDtPagto = lopDtPagto;
    }

    public String getLopComprovante() {
        return lopComprovante;
    }

    public void setLopComprovante(String lopComprovante) {
        this.lopComprovante = lopComprovante;
    }

    public String getLopDescricao() {
        return lopDescricao;
    }

    public void setLopDescricao(String lopDescricao) {
        this.lopDescricao = lopDescricao;
    }

    public Date getLopDt() {
        return lopDt;
    }

    public void setLopDt(Date lopDt) {
        this.lopDt = lopDt;
    }

    public Date getLopDtPrevisao() {
        return lopDtPrevisao;
    }

    public void setLopDtPrevisao(Date lopDtPrevisao) {
        this.lopDtPrevisao = lopDtPrevisao;
    }

    public Contagente getCtaId() {
        return ctaId;
    }

    public void setCtaId(Contagente ctaId) {
        this.ctaId = ctaId;
    }

    public Usuario getUsuIdAprovador() {
        return usuIdAprovador;
    }

    public void setUsuIdAprovador(Usuario usuIdAprovador) {
        this.usuIdAprovador = usuIdAprovador;
    }

    public Usuario getUsuIdAgente() {
        return usuIdAgente;
    }

    public void setUsuIdAgente(Usuario usuIdAgente) {
        this.usuIdAgente = usuIdAgente;
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
        hash += (lopId != null ? lopId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Lotepgto)) {
            return false;
        }
        Lotepgto other = (Lotepgto) object;
        if ((this.lopId == null && other.lopId != null) || (this.lopId != null && !this.lopId.equals(other.lopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Lotepgto[ lopId=" + lopId + " ]";
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }
    
}
