package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "pedbus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedbus.findAll", query = "SELECT p FROM Pedbus p"),
    @NamedQuery(name = "Pedbus.findAllPbsId", query = "SELECT p FROM Pedbus p WHERE p.pbsId = :pbsId"),
    @NamedQuery(name = "Pedbus.findByPbuId", query = "SELECT p FROM Pedbus p WHERE p.pbuId = :pbuId"),
    @NamedQuery(name = "Pedbus.updatePbParaCampo", query = "UPDATE Pedbus p SET p.pbsId = 1 WHERE p.pbuId = :pbuId")
})
public class Pedbus implements Serializable {

    @Column(name = "pbu_erro_envio")
    private Short pbuErroEnvio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pbuId")
    private List<Recibo> reciboList;

    @Column(name = "pbu_dt_janela")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pbuDtJanela;

    @OneToMany(mappedBy = "pbuId")
    private List<Pagamento> pagamentoList;

    @OneToMany(mappedBy = "pbuId")
    private List<Agente> agenteList;
    @OneToMany(mappedBy = "pbuId")
    private List<Recebimento> recebimentoList;

    @JoinColumn(name = "eui_id", referencedColumnName = "eui_id")
    @ManyToOne
    private Equipe euiId;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_id")
    private Long pbuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pbuDt;
    @Size(max = 50)
    @Column(name = "pbu_invest_cidade")
    private String pbuInvestCidade;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_checkpoint")
//    private short pbuCheckpoint;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_investigado")
//    private short pbuInvestigado;
//   @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_recuperado")
//    private short pbuRecuperado;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_relatorio")
//    private short pbuRelatorio;
//    @Size(max = 50)
//    @Column(name = "pbu_recup_cidade")
//    private String pbuRecupCidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_urgente")
    private short pbuUrgente;
    @Basic(optional = false)
    @NotNull
//    @Column(name = "pbu_recusado")
//    private short pbuRecusado;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_aprovado")
//    private short pbuAprovado;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "pbu_aprovadoms")
//    private short pbuAprovadoms;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Analise analise;
//    @JoinColumn(name = "loc_nu_recup", referencedColumnName = "loc_nu")
//    @ManyToOne
//    private CepLocalidade locNuRecup;
    @JoinColumn(name = "pbs_id_pre", referencedColumnName = "pbs_id")
    @ManyToOne
    private Pedbusit pbsIdPre;
    @JoinColumn(name = "pus_id", referencedColumnName = "pus_id")
    @ManyToOne
    private Pbsubstatus pusId;
    @JoinColumn(name = "pbs_id", referencedColumnName = "pbs_id")
    @ManyToOne(optional = false)
    private Pedbusit pbsId;
    @JoinColumn(name = "ope_id", referencedColumnName = "ope_id")
    @ManyToOne
    private Operacao opeId;
    @JoinColumn(name = "est_id_invest", referencedColumnName = "est_id")
    @ManyToOne
    private Estado estIdInvest;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario usuId;
//    @JoinColumn(name = "est_id_recup", referencedColumnName = "est_id")
//    @ManyToOne
//    private Estado estIdRecup;
    @OneToMany(mappedBy = "pbuIdLigado")
    private List<Pedbus> pedbusList;
    @JoinColumn(name = "pbu_id_ligado", referencedColumnName = "pbu_id")
    @ManyToOne
    private Pedbus pbuIdLigado;
    @JoinColumn(name = "loc_nu_invest", referencedColumnName = "loc_nu")
    @ManyToOne
    private CepLocalidade locNuInvest;

    public Pedbus() {
    }

    public Pedbus(Long pbuId) {
        this.pbuId = pbuId;
    }

    public Pedbus(Long pbuId, Date pbuDt, short pbuCheckpoint, short pbuInvestigado, short pbuRecuperado, short pbuRelatorio, short pbuUrgente, short pbuRecusado, short pbuAprovado, short pbuAprovadoms) {
        this.pbuId = pbuId;
        this.pbuDt = pbuDt;
//        this.pbuCheckpoint = pbuCheckpoint;
//        this.pbuInvestigado = pbuInvestigado;
//        this.pbuRecuperado = pbuRecuperado;
//        this.pbuRelatorio = pbuRelatorio;
        this.pbuUrgente = pbuUrgente;
//        this.pbuRecusado = pbuRecusado;
//        this.pbuAprovado = pbuAprovado;
//        this.pbuAprovadoms = pbuAprovadoms;
    }

    public Long getPbuId() {
        return pbuId;
    }

    public void setPbuId(Long pbuId) {
        this.pbuId = pbuId;
    }

    public Date getPbuDt() {
        return pbuDt;
    }

    public void setPbuDt(Date pbuDt) {
        this.pbuDt = pbuDt;
    }

    public String getPbuInvestCidade() {
        return pbuInvestCidade;
    }

    public void setPbuInvestCidade(String pbuInvestCidade) {
        this.pbuInvestCidade = pbuInvestCidade;
    }

//    public short getPbuCheckpoint() {
//        return pbuCheckpoint;
//    }

//    public void setPbuCheckpoint(short pbuCheckpoint) {
//        this.pbuCheckpoint = pbuCheckpoint;
//    }

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

//    public String getPbuRecupCidade() {
//        return pbuRecupCidade;
//    }

//    public void setPbuRecupCidade(String pbuRecupCidade) {
//        this.pbuRecupCidade = pbuRecupCidade;
//    }

    public short getPbuUrgente() {
        return pbuUrgente;
    }

    public void setPbuUrgente(short pbuUrgente) {
        this.pbuUrgente = pbuUrgente;
    }

//    public short getPbuRecusado() {
//        return pbuRecusado;
//    }

//    public void setPbuRecusado(short pbuRecusado) {
//        this.pbuRecusado = pbuRecusado;
//    }

//    public short getPbuAprovado() {
//        return pbuAprovado;
//    }

//    public void setPbuAprovado(short pbuAprovado) {
//        this.pbuAprovado = pbuAprovado;
//    }

//    public short getPbuAprovadoms() {
//        return pbuAprovadoms;
//    }

//    public void setPbuAprovadoms(short pbuAprovadoms) {
//        this.pbuAprovadoms = pbuAprovadoms;
//    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

//    public CepLocalidade getLocNuRecup() {
//        return locNuRecup;
//    }

//    public void setLocNuRecup(CepLocalidade locNuRecup) {
//        this.locNuRecup = locNuRecup;
//    }

    public Pedbusit getPbsIdPre() {
        return pbsIdPre;
    }

    public void setPbsIdPre(Pedbusit pbsIdPre) {
        this.pbsIdPre = pbsIdPre;
    }

    public Pbsubstatus getPusId() {
        return pusId;
    }

    public void setPusId(Pbsubstatus pusId) {
        this.pusId = pusId;
    }

    public Pedbusit getPbsId() {
        return pbsId;
    }

    public void setPbsId(Pedbusit pbsId) {
        this.pbsId = pbsId;
    }

    public Operacao getOpeId() {
        return opeId;
    }

    public void setOpeId(Operacao opeId) {
        this.opeId = opeId;
    }

    public Estado getEstIdInvest() {
        return estIdInvest;
    }

    public void setEstIdInvest(Estado estIdInvest) {
        this.estIdInvest = estIdInvest;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

//    public Estado getEstIdRecup() {
//        return estIdRecup;
//    }

//    public void setEstIdRecup(Estado estIdRecup) {
//        this.estIdRecup = estIdRecup;
//    }

    @XmlTransient
    public List<Pedbus> getPedbusList() {
        return pedbusList;
    }

    public void setPedbusList(List<Pedbus> pedbusList) {
        this.pedbusList = pedbusList;
    }

    public Pedbus getPbuIdLigado() {
        return pbuIdLigado;
    }

    public void setPbuIdLigado(Pedbus pbuIdLigado) {
        this.pbuIdLigado = pbuIdLigado;
    }

    public CepLocalidade getLocNuInvest() {
        return locNuInvest;
    }

    public void setLocNuInvest(CepLocalidade locNuInvest) {
        this.locNuInvest = locNuInvest;
    }
    
    public Boolean getPbuErroEnvio() {
        if (pbuErroEnvio == 1) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void setPbuErroEnvio(Boolean pbuErroEnvio) {
        if (Objects.equals(pbuErroEnvio, Boolean.TRUE)) {
            this.pbuErroEnvio = 1;
        } else {
            this.pbuErroEnvio = 0;
        }
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pbuId != null ? pbuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pedbus)) {
            return false;
        }
        Pedbus other = (Pedbus) object;
        if ((this.pbuId == null && other.pbuId != null) || (this.pbuId != null && !this.pbuId.equals(other.pbuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Pedbus[ pbuId=" + pbuId + " ]";
    }

    public Equipe getEuiId() {
        return euiId;
    }

    public void setEuiId(Equipe euiId) {
        this.euiId = euiId;
    }

    @XmlTransient
    public List<Agente> getAgenteList() {
        return agenteList;
    }

    public void setAgenteList(List<Agente> agenteList) {
        this.agenteList = agenteList;
    }

    @XmlTransient
    public List<Recebimento> getRecebimentoList() {
        return recebimentoList;
    }

    public void setRecebimentoList(List<Recebimento> recebimentoList) {
        this.recebimentoList = recebimentoList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    public Date getPbuDtJanela() {
        return pbuDtJanela;
    }

    public void setPbuDtJanela(Date pbuDtJanela) {
        this.pbuDtJanela = pbuDtJanela;
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }
    
}
