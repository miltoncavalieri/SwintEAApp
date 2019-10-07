/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.zaiac.swinteaapp.entities;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Cor;
import br.com.zaiac.swinteaapp.entities.Modelo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author milton_cavalieri
 */
@Entity
@Table(name = "veiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v")})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "vei_placa")
    private String veiPlaca;
    @Size(max = 11)
    @Column(name = "vei_renavam")
    private String veiRenavam;
    @Size(max = 17)
    @Column(name = "vei_chassis")
    private String veiChassis;
    @JoinColumn(name = "cor_id", referencedColumnName = "cor_id")
    @ManyToOne
    private Cor corId;
    @JoinColumn(name = "mod_id", referencedColumnName = "mod_id")
    @ManyToOne
    private Modelo modId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anaVeiculoPlaca")
    private List<Analise> analiseList;

    public Veiculo() {
    }

    public Veiculo(String veiPlaca) {
        this.veiPlaca = veiPlaca;
    }

    public String getVeiPlaca() {
        return veiPlaca;
    }

    public void setVeiPlaca(String veiPlaca) {
        this.veiPlaca = veiPlaca;
    }

    public String getVeiRenavam() {
        return veiRenavam;
    }

    public void setVeiRenavam(String veiRenavam) {
        this.veiRenavam = veiRenavam;
    }

    public String getVeiChassis() {
        return veiChassis;
    }

    public void setVeiChassis(String veiChassis) {
        this.veiChassis = veiChassis;
    }

    public Cor getCorId() {
        return corId;
    }

    public void setCorId(Cor corId) {
        this.corId = corId;
    }

    public Modelo getModId() {
        return modId;
    }

    public void setModId(Modelo modId) {
        this.modId = modId;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veiPlaca != null ? veiPlaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.veiPlaca == null && other.veiPlaca != null) || (this.veiPlaca != null && !this.veiPlaca.equals(other.veiPlaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.facade.Veiculo[ veiPlaca=" + veiPlaca + " ]";
    }
    
}
