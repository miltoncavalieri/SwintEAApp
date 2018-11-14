package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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

@Entity
@Table(name = "cep_localidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CepLocalidade.findAll", query = "SELECT c FROM CepLocalidade c"),
    @NamedQuery(name = "CepLocalidade.findByLocNu", query = "SELECT c FROM CepLocalidade c WHERE c.locNu = :locNu")
})
public class CepLocalidade implements Serializable {

    @OneToMany(mappedBy = "locNuRecup")
    private List<Checkpoint> checkpointList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "loc_nu")
    private Long locNu;
    @Size(max = 72)
    @Column(name = "loc_no")
    private String locNo;
    @Size(max = 8)
    @Column(name = "loc_cep")
    private String locCep;
    @Column(name = "loc_in_sit")
    private Character locInSit;
    @Column(name = "loc_in_tipo_loc")
    private Character locInTipoLoc;
    @Column(name = "loc_nu_sub")
    private Integer locNuSub;
    @Size(max = 36)
    @Column(name = "loc_no_abrev")
    private String locNoAbrev;
    @Size(max = 7)
    @Column(name = "mun_nu")
    private String munNu;
    @JoinColumn(name = "ufe_sg", referencedColumnName = "ufe_sg")
    @ManyToOne
    private CepFaixaUf ufeSg;
//    @OneToMany(mappedBy = "locNuRecup")
//    private List<Pedbus> pedbusList;
    @OneToMany(mappedBy = "locNuInvest")
    private List<Pedbus> pedbusList1;
    @OneToMany(mappedBy = "locNuDevolucao")
    private List<Analise> analiseList;
    @OneToMany(mappedBy = "locNuLocacao")
    private List<Analise> analiseList1;

    public CepLocalidade() {
    }

    public CepLocalidade(Long locNu) {
        this.locNu = locNu;
    }

    public Long getLocNu() {
        return locNu;
    }

    public void setLocNu(Long locNu) {
        this.locNu = locNu;
    }

    public String getLocNo() {
        return locNo.toUpperCase();
    }

    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    public String getLocCep() {
        return locCep;
    }

    public void setLocCep(String locCep) {
        this.locCep = locCep;
    }

    public Character getLocInSit() {
        return locInSit;
    }

    public void setLocInSit(Character locInSit) {
        this.locInSit = locInSit;
    }

    public Character getLocInTipoLoc() {
        return locInTipoLoc;
    }

    public void setLocInTipoLoc(Character locInTipoLoc) {
        this.locInTipoLoc = locInTipoLoc;
    }

    public Integer getLocNuSub() {
        return locNuSub;
    }

    public void setLocNuSub(Integer locNuSub) {
        this.locNuSub = locNuSub;
    }

    public String getLocNoAbrev() {
        return locNoAbrev.toUpperCase();
    }

    public void setLocNoAbrev(String locNoAbrev) {
        this.locNoAbrev = locNoAbrev;
    }

    public String getMunNu() {
        return munNu;
    }

    public void setMunNu(String munNu) {
        this.munNu = munNu;
    }

    public CepFaixaUf getUfeSg() {
        return ufeSg;
    }

    public void setUfeSg(CepFaixaUf ufeSg) {
        this.ufeSg = ufeSg;
    }

//    @XmlTransient
//    public List<Pedbus> getPedbusList() {
//        return pedbusList;
//    }

//    public void setPedbusList(List<Pedbus> pedbusList) {
//        this.pedbusList = pedbusList;
//    }

    @XmlTransient
    public List<Pedbus> getPedbusList1() {
        return pedbusList1;
    }

    public void setPedbusList1(List<Pedbus> pedbusList1) {
        this.pedbusList1 = pedbusList1;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @XmlTransient
    public List<Analise> getAnaliseList1() {
        return analiseList1;
    }

    public void setAnaliseList1(List<Analise> analiseList1) {
        this.analiseList1 = analiseList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locNu != null ? locNu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CepLocalidade)) {
            return false;
        }
        CepLocalidade other = (CepLocalidade) object;
        if ((this.locNu == null && other.locNu != null) || (this.locNu != null && !this.locNu.equals(other.locNu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.CepLocalidade[ locNu=" + locNu + " ]";
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }
    
}
