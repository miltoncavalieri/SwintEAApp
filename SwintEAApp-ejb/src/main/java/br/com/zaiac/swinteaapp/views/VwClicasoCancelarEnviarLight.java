package br.com.zaiac.swinteaapp.views;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "vw_clicaso_cancelar_enviar_light")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwClicasoCancelarEnviarLight.findAll", query = "SELECT v FROM VwClicasoCancelarEnviarLight v")
})
public class VwClicasoCancelarEnviarLight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 42)
    @Id
    @Column(name = "rownum")
    private String rownum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_id")
    private long pbuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anaDt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_dt_receb")
    @Temporal(TemporalType.DATE)
    private Date anaDtReceb;
    @Column(name = "age_id")
    private BigInteger ageId;
    @Column(name = "usu_id_agente")
    private Integer usuIdAgente;
    @Column(name = "pbu_dt_campo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pbuDtCampo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cli_id")
    private long cliId;
    @Size(max = 20)
    @Column(name = "sitpb")
    private String sitpb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_ativo")
    private short anaAtivo;
    @Column(name = "a01_id")
    private Short a01Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a01_id_criar")
    private short a01IdCriar;
    @Column(name = "a04_id_caso_criar")
    private BigInteger a04IdCasoCriar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a04_id_caso_cliente_criar")
    private long a04IdCasoClienteCriar;
    @Column(name = "a01_id_cancelar")
    private Short a01IdCancelar;
    @Column(name = "a04_id_caso_cancelar")
    private BigInteger a04IdCasoCancelar;
    @Column(name = "a04_id_caso_cliente_cancelar")
    private BigInteger a04IdCasoClienteCancelar;
    @Lob
    @Size(max = 65535)
    @Column(name = "a05_msg")
    private String a05Msg;

    public VwClicasoCancelarEnviarLight() {
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

    public Date getAnaDt() {
        return anaDt;
    }

    public void setAnaDt(Date anaDt) {
        this.anaDt = anaDt;
    }

    public Date getAnaDtReceb() {
        return anaDtReceb;
    }

    public void setAnaDtReceb(Date anaDtReceb) {
        this.anaDtReceb = anaDtReceb;
    }

    public BigInteger getAgeId() {
        return ageId;
    }

    public void setAgeId(BigInteger ageId) {
        this.ageId = ageId;
    }

    public Integer getUsuIdAgente() {
        return usuIdAgente;
    }

    public void setUsuIdAgente(Integer usuIdAgente) {
        this.usuIdAgente = usuIdAgente;
    }

    public Date getPbuDtCampo() {
        return pbuDtCampo;
    }

    public void setPbuDtCampo(Date pbuDtCampo) {
        this.pbuDtCampo = pbuDtCampo;
    }

    public long getCliId() {
        return cliId;
    }

    public void setCliId(long cliId) {
        this.cliId = cliId;
    }

    public String getSitpb() {
        return sitpb;
    }

    public void setSitpb(String sitpb) {
        this.sitpb = sitpb;
    }

    public short getAnaAtivo() {
        return anaAtivo;
    }

    public void setAnaAtivo(short anaAtivo) {
        this.anaAtivo = anaAtivo;
    }

    public Short getA01Id() {
        return a01Id;
    }

    public void setA01Id(Short a01Id) {
        this.a01Id = a01Id;
    }

    public short getA01IdCriar() {
        return a01IdCriar;
    }

    public void setA01IdCriar(short a01IdCriar) {
        this.a01IdCriar = a01IdCriar;
    }

    public BigInteger getA04IdCasoCriar() {
        return a04IdCasoCriar;
    }

    public void setA04IdCasoCriar(BigInteger a04IdCasoCriar) {
        this.a04IdCasoCriar = a04IdCasoCriar;
    }

    public long getA04IdCasoClienteCriar() {
        return a04IdCasoClienteCriar;
    }

    public void setA04IdCasoClienteCriar(long a04IdCasoClienteCriar) {
        this.a04IdCasoClienteCriar = a04IdCasoClienteCriar;
    }

    public Short getA01IdCancelar() {
        return a01IdCancelar;
    }

    public void setA01IdCancelar(Short a01IdCancelar) {
        this.a01IdCancelar = a01IdCancelar;
    }

    public BigInteger getA04IdCasoCancelar() {
        return a04IdCasoCancelar;
    }

    public void setA04IdCasoCancelar(BigInteger a04IdCasoCancelar) {
        this.a04IdCasoCancelar = a04IdCasoCancelar;
    }

    public BigInteger getA04IdCasoClienteCancelar() {
        return a04IdCasoClienteCancelar;
    }

    public void setA04IdCasoClienteCancelar(BigInteger a04IdCasoClienteCancelar) {
        this.a04IdCasoClienteCancelar = a04IdCasoClienteCancelar;
    }

    public String getA05Msg() {
        return a05Msg;
    }

    public void setA05Msg(String a05Msg) {
        this.a05Msg = a05Msg;
    }
    
}
