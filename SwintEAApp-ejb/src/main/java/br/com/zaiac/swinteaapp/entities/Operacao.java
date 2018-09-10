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
import javax.persistence.Lob;
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
@Table(name = "operacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacao.findAll", query = "SELECT o FROM Operacao o")})
public class Operacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ope_id")
    private Integer opeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ope_nome")
    private String opeNome;
    @Lob
    @Column(name = "ope_imagem")
    private byte[] opeImagem;
    @OneToMany(mappedBy = "opeId")
    private List<Pedbus> pedbusList;

    public Operacao() {
    }

    public Operacao(Integer opeId) {
        this.opeId = opeId;
    }

    public Operacao(Integer opeId, String opeNome) {
        this.opeId = opeId;
        this.opeNome = opeNome;
    }

    public Integer getOpeId() {
        return opeId;
    }

    public void setOpeId(Integer opeId) {
        this.opeId = opeId;
    }

    public String getOpeNome() {
        return opeNome;
    }

    public void setOpeNome(String opeNome) {
        this.opeNome = opeNome;
    }

    public byte[] getOpeImagem() {
        return opeImagem;
    }

    public void setOpeImagem(byte[] opeImagem) {
        this.opeImagem = opeImagem;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList() {
        return pedbusList;
    }

    public void setPedbusList(List<Pedbus> pedbusList) {
        this.pedbusList = pedbusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opeId != null ? opeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacao)) {
            return false;
        }
        Operacao other = (Operacao) object;
        if ((this.opeId == null && other.opeId != null) || (this.opeId != null && !this.opeId.equals(other.opeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Operacao[ opeId=" + opeId + " ]";
    }
    
}
