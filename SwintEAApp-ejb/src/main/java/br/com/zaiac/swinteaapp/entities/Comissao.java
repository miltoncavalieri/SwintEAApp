/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
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

/**
 *
 * @author root
 */
@Entity
@Table(name = "comissao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comissao.findAll", query = "SELECT c FROM Comissao c")})
public class Comissao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "com_id")
    private Integer comId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "com_descricao")
    private String comDescricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "com_recuperacao")
    private BigDecimal comRecuperacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "com_investigacao")
    private BigDecimal comInvestigacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "com_prazo")
    private int comPrazo;
    @OneToMany(mappedBy = "comId")
    private List<Usuario> usuarioList;

    public Comissao() {
    }

    public Comissao(Integer comId) {
        this.comId = comId;
    }

    public Comissao(Integer comId, String comDescricao, BigDecimal comRecuperacao, BigDecimal comInvestigacao, int comPrazo) {
        this.comId = comId;
        this.comDescricao = comDescricao;
        this.comRecuperacao = comRecuperacao;
        this.comInvestigacao = comInvestigacao;
        this.comPrazo = comPrazo;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComDescricao() {
        return comDescricao;
    }

    public void setComDescricao(String comDescricao) {
        this.comDescricao = comDescricao;
    }

    public BigDecimal getComRecuperacao() {
        return comRecuperacao;
    }

    public void setComRecuperacao(BigDecimal comRecuperacao) {
        this.comRecuperacao = comRecuperacao;
    }

    public BigDecimal getComInvestigacao() {
        return comInvestigacao;
    }

    public void setComInvestigacao(BigDecimal comInvestigacao) {
        this.comInvestigacao = comInvestigacao;
    }

    public int getComPrazo() {
        return comPrazo;
    }

    public void setComPrazo(int comPrazo) {
        this.comPrazo = comPrazo;
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
        hash += (comId != null ? comId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comissao)) {
            return false;
        }
        Comissao other = (Comissao) object;
        if ((this.comId == null && other.comId != null) || (this.comId != null && !this.comId.equals(other.comId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Comissao[ comId=" + comId + " ]";
    }
    
}
