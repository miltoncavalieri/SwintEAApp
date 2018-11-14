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
@Table(name = "contagente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contagente.findAll", query = "SELECT c FROM Contagente c")})
public class Contagente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cta_id")
    private Integer ctaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cta_agencia")
    private String ctaAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cta_conta")
    private String ctaConta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cta_padrao")
    private short ctaPadrao;
    @Size(max = 60)
    @Column(name = "cta_titular")
    private String ctaTitular;
    @Size(max = 15)
    @Column(name = "cta_cpf_cnpj")
    private String ctaCpfCnpj;
    @OneToMany(mappedBy = "ctaId")
    private List<Lotepgto> lotepgtoList;
    @JoinColumn(name = "bco_id", referencedColumnName = "bco_id")
    @ManyToOne(optional = false)
    private Banco bcoId;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuId;
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id")
    @ManyToOne(optional = false)
    private Tipopes pesId;

    public Contagente() {
    }

    public Contagente(Integer ctaId) {
        this.ctaId = ctaId;
    }

    public Contagente(Integer ctaId, String ctaAgencia, String ctaConta, short ctaPadrao) {
        this.ctaId = ctaId;
        this.ctaAgencia = ctaAgencia;
        this.ctaConta = ctaConta;
        this.ctaPadrao = ctaPadrao;
    }

    public Integer getCtaId() {
        return ctaId;
    }

    public void setCtaId(Integer ctaId) {
        this.ctaId = ctaId;
    }

    public String getCtaAgencia() {
        return ctaAgencia;
    }

    public void setCtaAgencia(String ctaAgencia) {
        this.ctaAgencia = ctaAgencia;
    }

    public String getCtaConta() {
        return ctaConta;
    }

    public void setCtaConta(String ctaConta) {
        this.ctaConta = ctaConta;
    }

    public short getCtaPadrao() {
        return ctaPadrao;
    }

    public void setCtaPadrao(short ctaPadrao) {
        this.ctaPadrao = ctaPadrao;
    }

    public String getCtaTitular() {
        return ctaTitular;
    }

    public void setCtaTitular(String ctaTitular) {
        this.ctaTitular = ctaTitular;
    }

    public String getCtaCpfCnpj() {
        return ctaCpfCnpj;
    }

    public void setCtaCpfCnpj(String ctaCpfCnpj) {
        this.ctaCpfCnpj = ctaCpfCnpj;
    }

    @XmlTransient
    public List<Lotepgto> getLotepgtoList() {
        return lotepgtoList;
    }

    public void setLotepgtoList(List<Lotepgto> lotepgtoList) {
        this.lotepgtoList = lotepgtoList;
    }

    public Banco getBcoId() {
        return bcoId;
    }

    public void setBcoId(Banco bcoId) {
        this.bcoId = bcoId;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    public Tipopes getPesId() {
        return pesId;
    }

    public void setPesId(Tipopes pesId) {
        this.pesId = pesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctaId != null ? ctaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contagente)) {
            return false;
        }
        Contagente other = (Contagente) object;
        if ((this.ctaId == null && other.ctaId != null) || (this.ctaId != null && !this.ctaId.equals(other.ctaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Contagente[ ctaId=" + ctaId + " ]";
    }
    
}
