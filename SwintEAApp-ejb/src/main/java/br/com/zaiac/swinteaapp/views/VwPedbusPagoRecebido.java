package br.com.zaiac.swinteaapp.views;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
//import java.math.BigInteger;
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
@Table(name = "vw_pedbus_pago_recebido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwPedbusPagoRecebido.findAll", query = "SELECT v FROM VwPedbusPagoRecebido v"),
    @NamedQuery(name = "VwPedbusPagoRecebido.findByPbuId", query = "SELECT v FROM VwPedbusPagoRecebido v WHERE v.pbuId = :pbuId"),
    @NamedQuery(name = "VwPedbusPagoRecebido.findByPckId", query = "SELECT v FROM VwPedbusPagoRecebido v WHERE v.pckId = :pckId")})
public class VwPedbusPagoRecebido implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "age_id")
    private long ageId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pag_id")
    private Long pagId;
    @Column(name = "lcb_id")
    private Long lcbId;
    @Column(name = "lcb_dt_pagto")
    @Temporal(TemporalType.DATE)
    private Date lcbDtPagto;
    @Column(name = "lop_id")
    private Long lopId;
    @Column(name = "lop_dt_pagto")
    @Temporal(TemporalType.DATE)
    private Date lopDtPagto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rcb_id")
    private Long rcbId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_id")
    private Long pckId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ckt_id")
    private short cktId;
    @Column(name = "pck_relatoriofeito")
    private Short pckRelatoriofeito;
    @Column(name = "pck_aprovado")
    private Short pckAprovado;
    @Column(name = "pck_recusado")
    private Short pckRecusado;
    @Column(name = "pck_aprovadoms")
    private Short pckAprovadoms;

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
    @Column(name = "pbs_id")
    private short pbsId;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_investigado")
//    private short pbuInvestigado;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_recuperado")
//    private short pbuRecuperado;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_relatorio")
//    private short pbuRelatorio;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_aprovado")
//    private short pbuAprovado;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_recusado")
//    private short pbuRecusado;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_aprovadoms")
//    private short pbuAprovadoms;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "ags_id")
//    private short agsId;
//    @Column(name = "pbu_id_ligado")
//    private Long pbuIdLigado;

    public VwPedbusPagoRecebido() {
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

    public short getPbsId() {
        return pbsId;
    }

    public void setPbsId(short pbsId) {
        this.pbsId = pbsId;
    }

//    public short getPbuInvestigado() {
//        return pbuInvestigado;
//    }

//    public void setPbuInvestigado(short pbuInvestigado) {
//        this.pbuInvestigado = pbuInvestigado;
//    }

//    public short getPbuRecuperado() {
//        return pbuRecuperado;
//    }

//    public void setPbuRecuperado(short pbuRecuperado) {
//        this.pbuRecuperado = pbuRecuperado;
//    }

//    public short getPbuRelatorio() {
//        return pbuRelatorio;
//    }

//    public void setPbuRelatorio(short pbuRelatorio) {
//        this.pbuRelatorio = pbuRelatorio;
//    }

//    public short getPbuAprovado() {
//        return pbuAprovado;
//    }

//    public void setPbuAprovado(short pbuAprovado) {
//        this.pbuAprovado = pbuAprovado;
//    }

//    public short getPbuRecusado() {
//        return pbuRecusado;
//    }

//    public void setPbuRecusado(short pbuRecusado) {
//        this.pbuRecusado = pbuRecusado;
//    }

//    public short getPbuAprovadoms() {
//        return pbuAprovadoms;
//    }

//    public void setPbuAprovadoms(short pbuAprovadoms) {
//        this.pbuAprovadoms = pbuAprovadoms;
//    }

//    public short getAgsId() {
//        return agsId;
//    }

//    public void setAgsId(short agsId) {
//        this.agsId = agsId;
//    }

    public Long getPagId() {
        return pagId;
    }

    public void setPagId(Long pagId) {
        this.pagId = pagId;
    }

    public Long getRcbId() {
        return rcbId;
    }

    public void setRcbId(Long rcbId) {
        this.rcbId = rcbId;
    }

    public Long getLcbId() {
        return lcbId;
    }

    public void setLcbId(Long lcbId) {
        this.lcbId = lcbId;
    }

    public Long getLopId() {
        return lopId;
    }

    public void setLopId(Long lopId) {
        this.lopId = lopId;
    }

//    public Long getPbuIdLigado() {
//        return pbuIdLigado;
//    }

//    public void setPbuIdLigado(Long pbuIdLigado) {
//        this.pbuIdLigado = pbuIdLigado;
//    }

    public Long getPckId() {
        return pckId;
    }

    public void setPckId(Long pckId) {
        this.pckId = pckId;
    }

    public short getCktId() {
        return cktId;
    }

    public void setCktId(short cktId) {
        this.cktId = cktId;
    }

    public Short getPckRelatoriofeito() {
        return pckRelatoriofeito;
    }

    public void setPckRelatoriofeito(Short pckRelatoriofeito) {
        this.pckRelatoriofeito = pckRelatoriofeito;
    }

    public Short getPckAprovado() {
        return pckAprovado;
    }

    public void setPckAprovado(Short pckAprovado) {
        this.pckAprovado = pckAprovado;
    }

    public Short getPckRecusado() {
        return pckRecusado;
    }

    public void setPckRecusado(Short pckRecusado) {
        this.pckRecusado = pckRecusado;
    }

    public Short getPckAprovadoms() {
        return pckAprovadoms;
    }

    public void setPckAprovadoms(Short pckAprovadoms) {
        this.pckAprovadoms = pckAprovadoms;
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }


    public Date getLcbDtPagto() {
        return lcbDtPagto;
    }

    public void setLcbDtPagto(Date lcbDtPagto) {
        this.lcbDtPagto = lcbDtPagto;
    }

    public Date getLopDtPagto() {
        return lopDtPagto;
    }

    public void setLopDtPagto(Date lopDtPagto) {
        this.lopDtPagto = lopDtPagto;
    }
}
