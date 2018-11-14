package br.com.zaiac.swinteaapp.views;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "vw_pagrec_investigado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwPagrecInvestigado.findAll", query = "SELECT v FROM VwPagrecInvestigado v"),
    @NamedQuery(name = "VwPagrecInvestigado.findByPbuId", query = "SELECT v FROM VwPagrecInvestigado v WHERE v.pbuId = :pbuId ORDER BY v.pckDt"),
})
public class VwPagrecInvestigado implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pckDt;

    @Column(name = "pag_id_invest")
    private BigInteger pagIdInvest;
    @Column(name = "lop_id")
    private BigInteger lopId;
    @Column(name = "rcb_id_invest")
    private BigInteger rcbIdInvest;
    @Column(name = "lcb_id")
    private BigInteger lcbId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_id")
    private long pckId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ckt_id")
    private short cktId;
    @Column(name = "rcb_id")
    private long rcbId;

//    @Column(name = "pck_id_pgto")
//    private BigInteger pckIdPgto;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pck_id_rcbo")
//    private long pckIdRcbo;

//    @Column(name = "pbu_id_ligado")
//    private BigInteger pbuIdLigado;

//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbs_id")
//    private short pbsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ags_id")
    private short agsId;

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rownum")
    private String rownum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_id")
    private long pbuId;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 7)
//    @Column(name = "ana_veiculo_placa")
//    private String anaVeiculoPlaca;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 15)
//    @Column(name = "loc_cpf_cnpj")
//    private String locCpfCnpj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pag_id")
    private long pagId;

    public VwPagrecInvestigado() {
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

    public long getPagId() {
        return pagId;
    }

    public void setPagId(long pagId) {
        this.pagId = pagId;
    }

//    public long getRcbId() {
//        return rcbId;
//    }

//    public void setRcbId(long rcbId) {
//        this.rcbId = rcbId;
//    }

    public short getAgsId() {
        return agsId;
    }

    public void setAgsId(short agsId) {
        this.agsId = agsId;
    }

//    public BigInteger getPbuIdLigado() {
//        return pbuIdLigado;
//    }

//    public void setPbuIdLigado(BigInteger pbuIdLigado) {
//        this.pbuIdLigado = pbuIdLigado;
//    }

//    public BigInteger getPckIdPgto() {
//        return pckIdPgto;
//    }

//    public void setPckIdPgto(BigInteger pckIdPgto) {
//        this.pckIdPgto = pckIdPgto;
//    }

//    public long getPckIdRcbo() {
//        return pckIdRcbo;
//    }

//    public void setPckIdRcbo(long pckIdRcbo) {
//        this.pckIdRcbo = pckIdRcbo;
//    }

    public long getPckId() {
        return pckId;
    }

    public void setPckId(long pckId) {
        this.pckId = pckId;
    }

    public short getCktId() {
        return cktId;
    }

    public void setCktId(short cktId) {
        this.cktId = cktId;
    }

    public long getRcbId() {
        return rcbId;
    }

    public void setRcbId(long rcbId) {
        this.rcbId = rcbId;
    }

    public BigInteger getPagIdInvest() {
        return pagIdInvest;
    }

    public void setPagIdInvest(BigInteger pagIdInvest) {
        this.pagIdInvest = pagIdInvest;
    }

    public BigInteger getLopId() {
        return lopId;
    }

    public void setLopId(BigInteger lopId) {
        this.lopId = lopId;
    }

    public BigInteger getRcbIdInvest() {
        return rcbIdInvest;
    }

    public void setRcbIdInvest(BigInteger rcbIdInvest) {
        this.rcbIdInvest = rcbIdInvest;
    }

    public BigInteger getLcbId() {
        return lcbId;
    }

    public void setLcbId(BigInteger lcbId) {
        this.lcbId = lcbId;
    }

    public Date getPckDt() {
        return pckDt;
    }

    public void setPckDt(Date pckDt) {
        this.pckDt = pckDt;
    }
    
}
