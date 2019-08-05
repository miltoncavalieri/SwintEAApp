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
@Table(name = "sla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sla.findAll", query = "SELECT s FROM Sla s")})
public class Sla implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "sla_dias_analise")
    private short slaDiasAnalise;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sla_id")
    private Integer slaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sla_nome")
    private String slaNome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sla_dias")
    private short slaDias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sla_ckp_dias")
    private short slaCkpDias;
    @Column(name = "sla_ckp_aviso")
    private Short slaCkpAviso;
    @OneToMany(mappedBy = "slaId")
    private List<Usuario> usuarioList;

    public Sla() {
    }

    public Sla(Integer slaId) {
        this.slaId = slaId;
    }

    public Sla(Integer slaId, String slaNome, short slaDias, short slaCkpDias) {
        this.slaId = slaId;
        this.slaNome = slaNome;
        this.slaDias = slaDias;
        this.slaCkpDias = slaCkpDias;
    }

    public Integer getSlaId() {
        return slaId;
    }

    public void setSlaId(Integer slaId) {
        this.slaId = slaId;
    }

    public String getSlaNome() {
        return slaNome;
    }

    public void setSlaNome(String slaNome) {
        this.slaNome = slaNome;
    }

    public short getSlaDias() {
        return slaDias;
    }

    public void setSlaDias(short slaDias) {
        this.slaDias = slaDias;
    }

    public short getSlaCkpDias() {
        return slaCkpDias;
    }

    public void setSlaCkpDias(short slaCkpDias) {
        this.slaCkpDias = slaCkpDias;
    }

    public Short getSlaCkpAviso() {
        return slaCkpAviso;
    }

    public void setSlaCkpAviso(Short slaCkpAviso) {
        this.slaCkpAviso = slaCkpAviso;
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
        hash += (slaId != null ? slaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sla)) {
            return false;
        }
        Sla other = (Sla) object;
        if ((this.slaId == null && other.slaId != null) || (this.slaId != null && !this.slaId.equals(other.slaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Sla[ slaId=" + slaId + " ]";
    }

    public short getSlaDiasAnalise() {
        return slaDiasAnalise;
    }

    public void setSlaDiasAnalise(short slaDiasAnalise) {
        this.slaDiasAnalise = slaDiasAnalise;
    }
    
}
