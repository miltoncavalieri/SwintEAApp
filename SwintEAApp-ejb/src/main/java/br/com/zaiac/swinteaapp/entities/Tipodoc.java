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
@Table(name = "tipodoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodoc.findAll", query = "SELECT t FROM Tipodoc t")})
public class Tipodoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_id")
    private Integer docId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "doc_nome")
    private String docNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docId")
    private List<Analisedoc> analisedocList;

    public Tipodoc() {
    }

    public Tipodoc(Integer docId) {
        this.docId = docId;
    }

    public Tipodoc(Integer docId, String docNome) {
        this.docId = docId;
        this.docNome = docNome;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDocNome() {
        return docNome;
    }

    public void setDocNome(String docNome) {
        this.docNome = docNome;
    }

    @XmlTransient
    public List<Analisedoc> getAnalisedocList() {
        return analisedocList;
    }

    public void setAnalisedocList(List<Analisedoc> analisedocList) {
        this.analisedocList = analisedocList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tipodoc)) {
            return false;
        }
        Tipodoc other = (Tipodoc) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Tipodoc[ docId=" + docId + " ]";
    }
    
}
