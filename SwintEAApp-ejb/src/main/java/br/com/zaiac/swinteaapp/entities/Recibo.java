package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "recibo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibo.findAll", query = "SELECT r FROM Recibo r"),
    @NamedQuery(name = "Recibo.updateLopIdToNull", query = "UPDATE Recibo r SET r.lopId = NULL WHERE r.lopId = :lopId"),
    @NamedQuery(name = "Recibo.updateLcbIdToNull", query = "UPDATE Recibo r SET r.lcbId = NULL WHERE r.lcbId = :lcbId")
})
public class Recibo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rec_id")
    private Long recId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rec_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recDt;
    @Size(max = 20)
    @Column(name = "rec_numdoc")
    private String recNumdoc;
    @Column(name = "rec_dt_doc")
    @Temporal(TemporalType.DATE)
    private Date recDtDoc;
    @Size(max = 15)
    @Column(name = "rec_cnpj")
    private String recCnpj;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "rec_valor")
    private BigDecimal recValor;
    @Size(max = 20)
    @Column(name = "rec_img_recibo")
    private String recImgRecibo;
    @Column(name = "rec_dt_aprovacao")
    @Temporal(TemporalType.DATE)
    private Date recDtAprovacao;
    @Size(max = 60)
    @Column(name = "rec_motivo_reprovada")
    private String recMotivoReprovada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "rec_descricao")
    private String recDescricao;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne(optional = false)
    private Pedbus pbuId;
    @JoinColumn(name = "tpp_id", referencedColumnName = "tpp_id")
    @ManyToOne(optional = false)
    private Tipopag tppId;
    @JoinColumn(name = "usu_id_aprovador", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario usuIdAprovador;
    @JoinColumn(name = "lop_id", referencedColumnName = "lop_id")
    @ManyToOne
    private Lotepgto lopId;
    @JoinColumn(name = "lcb_id", referencedColumnName = "lcb_id")
    @ManyToOne
    private Lotercbo lcbId;
    @JoinColumn(name = "usu_id_solicitante", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuIdSolicitante;
    @JoinColumn(name = "roo_id", referencedColumnName = "roo_id")
    @ManyToOne(optional = false)
    private Recibotipo rooId;

    public Recibo() {
    }

    public Recibo(Long recId) {
        this.recId = recId;
    }

    public Recibo(Long recId, Date recDt, BigDecimal recValor, String recDescricao) {
        this.recId = recId;
        this.recDt = recDt;
        this.recValor = recValor;
        this.recDescricao = recDescricao;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Date getRecDt() {
        return recDt;
    }

    public void setRecDt(Date recDt) {
        this.recDt = recDt;
    }

    public String getRecNumdoc() {
        return recNumdoc;
    }

    public void setRecNumdoc(String recNumdoc) {
        this.recNumdoc = recNumdoc;
    }

    public Date getRecDtDoc() {
        return recDtDoc;
    }

    public void setRecDtDoc(Date recDtDoc) {
        this.recDtDoc = recDtDoc;
    }

    public String getRecCnpj() {
        return recCnpj;
    }

    public void setRecCnpj(String recCnpj) {
        this.recCnpj = recCnpj;
    }

    public BigDecimal getRecValor() {
        return recValor;
    }

    public void setRecValor(BigDecimal recValor) {
        this.recValor = recValor;
    }

    public String getRecImgRecibo() {
        return recImgRecibo;
    }

    public void setRecImgRecibo(String recImgRecibo) {
        this.recImgRecibo = recImgRecibo;
    }

    public Date getRecDtAprovacao() {
        return recDtAprovacao;
    }

    public void setRecDtAprovacao(Date recDtAprovacao) {
        this.recDtAprovacao = recDtAprovacao;
    }

    public String getRecMotivoReprovada() {
        return recMotivoReprovada;
    }

    public void setRecMotivoReprovada(String recMotivoReprovada) {
        this.recMotivoReprovada = recMotivoReprovada;
    }

    public String getRecDescricao() {
        return recDescricao;
    }

    public void setRecDescricao(String recDescricao) {
        this.recDescricao = recDescricao;
    }

    public Pedbus getPbuId() {
        return pbuId;
    }

    public void setPbuId(Pedbus pbuId) {
        this.pbuId = pbuId;
    }

    public Tipopag getTppId() {
        return tppId;
    }

    public void setTppId(Tipopag tppId) {
        this.tppId = tppId;
    }

    public Usuario getUsuIdAprovador() {
        return usuIdAprovador;
    }

    public void setUsuIdAprovador(Usuario usuIdAprovador) {
        this.usuIdAprovador = usuIdAprovador;
    }

    public Lotepgto getLopId() {
        return lopId;
    }

    public void setLopId(Lotepgto lopId) {
        this.lopId = lopId;
    }

    public Lotercbo getLcbId() {
        return lcbId;
    }

    public void setLcbId(Lotercbo lcbId) {
        this.lcbId = lcbId;
    }

    public Usuario getUsuIdSolicitante() {
        return usuIdSolicitante;
    }

    public void setUsuIdSolicitante(Usuario usuIdSolicitante) {
        this.usuIdSolicitante = usuIdSolicitante;
    }

    public Recibotipo getRooId() {
        return rooId;
    }

    public void setRooId(Recibotipo rooId) {
        this.rooId = rooId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recId != null ? recId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Recibo)) {
            return false;
        }
        Recibo other = (Recibo) object;
        if ((this.recId == null && other.recId != null) || (this.recId != null && !this.recId.equals(other.recId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Recibo[ recId=" + recId + " ]";
    }
    
}
