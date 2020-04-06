package br.com.zaiac.swinteaapp.views;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "vw_clicaso_anterior_faturar")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "VwClicasoAnteriorFaturar.findAll", query = "SELECT v FROM VwClicasoAnteriorFaturar v"),
    @NamedQuery(name = "VwClicasoAnteriorFaturar.findByA04IdCasoCliId", query = "SELECT v FROM VwClicasoAnteriorFaturar v WHERE v.a04IdCasoCriar = :a04IdCaso AND v.cliId = :cliId")


})
public class VwClicasoAnteriorFaturar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 32)
    @Id
    @Column(name = "rownum")
    private String rownum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_id")
    private long pbuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a03_id")
    private long a03Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a03_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a03Dt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a04_id_caso_criar")
    private long a04IdCasoCriar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a04_id_caso")
    private long a04IdCaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a04_id_caso_cliente")
    private long a04IdCasoCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cli_id")
    private long cliId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a01_id")
    private short a01Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a02_id")
    private short a02Id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "a02_nome")
    private String a02Nome;

    public VwClicasoAnteriorFaturar() {
    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public long getPbuId() {
        return pbuId;
    }

    public void setPbuId(long pbuId) {
        this.pbuId = pbuId;
    }

    public long getA03Id() {
        return a03Id;
    }

    public void setA03Id(long a03Id) {
        this.a03Id = a03Id;
    }

    public Date getA03Dt() {
        return a03Dt;
    }

    public void setA03Dt(Date a03Dt) {
        this.a03Dt = a03Dt;
    }

    public long getA04IdCasoCriar() {
        return a04IdCasoCriar;
    }

    public void setA04IdCasoCriar(long a04IdCasoCriar) {
        this.a04IdCasoCriar = a04IdCasoCriar;
    }

    public long getA04IdCaso() {
        return a04IdCaso;
    }

    public void setA04IdCaso(long a04IdCaso) {
        this.a04IdCaso = a04IdCaso;
    }

    public long getA04IdCasoCliente() {
        return a04IdCasoCliente;
    }

    public void setA04IdCasoCliente(long a04IdCasoCliente) {
        this.a04IdCasoCliente = a04IdCasoCliente;
    }

    public long getCliId() {
        return cliId;
    }

    public void setCliId(long cliId) {
        this.cliId = cliId;
    }

    public short getA01Id() {
        return a01Id;
    }

    public void setA01Id(short a01Id) {
        this.a01Id = a01Id;
    }

    public short getA02Id() {
        return a02Id;
    }

    public void setA02Id(short a02Id) {
        this.a02Id = a02Id;
    }

    public String getA02Nome() {
        return a02Nome;
    }

    public void setA02Nome(String a02Nome) {
        this.a02Nome = a02Nome;
    }
    
}
