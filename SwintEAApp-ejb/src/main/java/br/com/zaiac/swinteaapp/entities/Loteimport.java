/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author milton_cavalieri
 */
@Entity
@Table(name = "loteimport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loteimport.findAll", query = "SELECT l FROM Loteimport l")})
public class Loteimport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lrt_id")
    private Long lrtId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lrt_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lrtDt;
    @Size(max = 100)
    @Column(name = "lrt_arquivo")
    private String lrtArquivo;
    @Column(name = "lrt_importado")
    private Short lrtImportado;
    @Column(name = "lrt_processado")
    private Short lrtProcessado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lrt_diretorio")
    private String lrtDiretorio;
    @Column(name = "lrt_erro")
    private Short lrtErro;
    @Column(name = "lrt_integrado")
    private Short lrtIntegrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lrtId")
    private List<Dadosimport> dadosimportList;

    public Loteimport() {
    }

    public Loteimport(Long lrtId) {
        this.lrtId = lrtId;
    }

    public Loteimport(Long lrtId, Date lrtDt, String lrtDiretorio) {
        this.lrtId = lrtId;
        this.lrtDt = lrtDt;
        this.lrtDiretorio = lrtDiretorio;
    }

    public Long getLrtId() {
        return lrtId;
    }

    public void setLrtId(Long lrtId) {
        this.lrtId = lrtId;
    }

    public Date getLrtDt() {
        return lrtDt;
    }

    public void setLrtDt(Date lrtDt) {
        this.lrtDt = lrtDt;
    }

    public String getLrtArquivo() {
        return lrtArquivo;
    }

    public void setLrtArquivo(String lrtArquivo) {
        this.lrtArquivo = lrtArquivo;
    }

    public Short getLrtImportado() {
        return lrtImportado;
    }

    public void setLrtImportado(Short lrtImportado) {
        this.lrtImportado = lrtImportado;
    }

    public Short getLrtProcessado() {
        return lrtProcessado;
    }

    public void setLrtProcessado(Short lrtProcessado) {
        this.lrtProcessado = lrtProcessado;
    }

    public String getLrtDiretorio() {
        return lrtDiretorio;
    }

    public void setLrtDiretorio(String lrtDiretorio) {
        this.lrtDiretorio = lrtDiretorio;
    }

    public Short getLrtErro() {
        return lrtErro;
    }

    public void setLrtErro(Short lrtErro) {
        this.lrtErro = lrtErro;
    }

    public Short getLrtIntegrado() {
        return lrtIntegrado;
    }

    public void setLrtIntegrado(Short lrtIntegrado) {
        this.lrtIntegrado = lrtIntegrado;
    }

    @XmlTransient
    public List<Dadosimport> getDadosimportList() {
        return dadosimportList;
    }

    public void setDadosimportList(List<Dadosimport> dadosimportList) {
        this.dadosimportList = dadosimportList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lrtId != null ? lrtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loteimport)) {
            return false;
        }
        Loteimport other = (Loteimport) object;
        if ((this.lrtId == null && other.lrtId != null) || (this.lrtId != null && !this.lrtId.equals(other.lrtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Loteimport[ lrtId=" + lrtId + " ]";
    }
    
}
