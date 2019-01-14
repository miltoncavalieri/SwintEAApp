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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "checkpoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checkpoint.findAll", 
                query = "SELECT c FROM Checkpoint c"),
    
    @NamedQuery(name = "Checkpoint.deleteByPckId",             
            query = "DELETE FROM Checkpoint c WHERE c.pckId = :pckId"),    
    @NamedQuery(name = "Checkpoint.findByPbuId", 
            query = "SELECT c FROM Checkpoint c WHERE c.pbuId = :pbuId ORDER BY c.pckDt"),
    
    @NamedQuery(name = "Checkpoint.findByInvestigadoRecuperado", 
            query = "SELECT c FROM Checkpoint c WHERE c.pbuId = :pbuId AND c.cktId IN (3,4)"),
    
    @NamedQuery(name = "Checkpoint.findByPckIdAprovado", 
            query = "SELECT c FROM Checkpoint c WHERE c.pckIdAprovado = :pckIdAprovado"),
    
    @NamedQuery(name = "Checkpoint.findByPckIdStatusAprovado", 
            query = "SELECT c FROM Checkpoint c WHERE c.pckIdAprovado = :pckId AND c.cktId = 9 AND c.pckAtivo = 1"),    
    
    @NamedQuery(name = "Checkpoint.findByPckIdStatusReprovado", 
            query = "SELECT c FROM Checkpoint c WHERE c.pckIdAprovado = :pckId AND c.cktId = 12 AND c.pckAtivo = 1"),    
    
    @NamedQuery(name = "Checkpoint.findByPckId", 
            query = "SELECT c FROM Checkpoint c WHERE c.pckId = :pckId"),    
    
    @NamedQuery(name = "Checkpoint.reverteEncerrado", 
            query = "UPDATE Checkpoint c SET c.pckAtivo = 0 WHERE c.pbuId = :pbuId AND c.cktId.cktId = 13 AND c.pckAtivo = 1"),    
    
    @NamedQuery(name = "Checkpoint.findByPbuIdMobile", 
            query = "SELECT c FROM Checkpoint c WHERE c.pbuId = :pbuId AND c.cktId = 2 ORDER BY c.pckDt"),
    
    @NamedQuery(name = "Checkpoint.updatePckAtivo", 
            query = "UPDATE Checkpoint c SET c.pckAtivo = 0 WHERE (c.pckIdAprovado = :pckIdAprovado) OR (c.pckId = :pckId)"),

    @NamedQuery(name = "Checkpoint.updatePckAtivoRelatorio", 
            query = "UPDATE Checkpoint c SET c.pckAtivo = 0 WHERE (c.pckIdAprovado = :pckId) AND (c.cktId = 5)"),
    
    @NamedQuery(name = "Checkpoint.updatePckAtivoRelatorioAprovado", 
            query = "UPDATE Checkpoint c SET c.pckAtivo = 0 WHERE (c.pckIdAprovado = :pckId) AND (c.cktId = 7)"),
    
    @NamedQuery(name = "Checkpoint.updatePckAtivoRelatorioReprovado", 
            query = "UPDATE Checkpoint c SET c.pckAtivo = 0 WHERE (c.pckIdAprovado = :pckId) AND (c.cktId = 6)"),
    
    @NamedQuery(name = "Checkpoint.updateClearPromdev", 
            query = "UPDATE Checkpoint c SET c.pckAtivo = 0 WHERE (c.pbuId = :pbuId) AND (c.cktId IN(14,15))"),
    @NamedQuery(name = "Checkpoint.findPromessaDevolucaoAtiva", 
            query = "SELECT c FROM Checkpoint c WHERE (c.pbuId = :pbuId) AND (c.cktId IN (14)) AND (c.pckAtivo = 1)"),
    
    @NamedQuery(name = "Checkpoint.findVeiculoDevolvidoAtiva", 
            query = "SELECT c FROM Checkpoint c WHERE (c.pbuId = :pbuId) AND (c.cktId IN (15)) AND (c.pckAtivo = 1)"),
    
    @NamedQuery(name = "Checkpoint.findCheckpointParaVoltar", 
            query = "SELECT c FROM Checkpoint c WHERE c.pckId = (SELECT MAX(c1.pckId) FROM Checkpoint c1 WHERE (c1.pbuId = :pbuId) AND (c1.cktId IN (3,4)) AND (c1.pckAtivo = 1) AND (c1.ageId.agsId IN (2)))"),
    
    @NamedQuery(name = "Checkpoint.updateImagemCheckpoint", 
                query = "UPDATE Checkpoint c SET c.pckImagem = :pckImagem WHERE c.pckIdAprovado.pckId = :pckId"),
        
    @NamedQuery(name = "Checkpoint.updateBatch", 
                query = "UPDATE Checkpoint c SET c.pckDescricao = :pckDescricao, c.pckLegendaFoto = :pckLegendaFoto, c.pckImagem = :pckImagem, c.pckRelatorio = :pckRelatorio WHERE (c.pckIdAprovado.pckId = :pckId) OR (c.pckId = :pckId)"),
        
    @NamedQuery(name = "Checkpoint.updateBatchDeleteFoto", 
                query = "UPDATE Checkpoint c SET c.pckLegendaFoto = NULL, c.pckImagem = NULL, c.pckRelatorio = 0 WHERE (c.pckIdAprovado.pckId = :pckId) OR (c.pckId = :pckId)"),

    @NamedQuery(name = "Checkpoint.deleteBatchSlave", 
                query = "DELETE FROM Checkpoint c WHERE (c.pckIdAprovado.pckId = :pckId)"),
    
    @NamedQuery(name = "Checkpoint.deleteBatchMaster", 
                query = "DELETE FROM Checkpoint c WHERE (c.pckId = :pckId)")

        
        
})
public class Checkpoint implements Serializable {

    @Column(name = "pck_dt_pre")
    @Temporal(TemporalType.DATE)
    private Date pckDtPre;
    @JoinColumn(name = "pbs_id_pre", referencedColumnName = "pbs_id")
    @ManyToOne
    private Pedbusit pbsIdPre;

    @Size(max = 50)
    @Column(name = "pck_recup_cidade")
    private String pckRecupCidade;
    @JoinColumn(name = "est_id_recup", referencedColumnName = "est_id")
    @ManyToOne
    private Estado estIdRecup;
    @JoinColumn(name = "loc_nu_recup", referencedColumnName = "loc_nu")
    @ManyToOne
    private CepLocalidade locNuRecup;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "checkpoint")
    private Pedbusrel pedbusrel;

    @Column(name = "pck_aprovadoms")
    private Short pckAprovadoms;
    @Column(name = "pck_relatoriofeito")
    private Short pckRelatoriofeito;
    @Column(name = "pck_recusado")
    private Short pckRecusado;
    @Column(name = "pck_aprovado")
    private Short pckAprovado;

    @JoinColumn(name = "age_id", referencedColumnName = "age_id")
    @ManyToOne
    private Agente ageId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pckId")
    private List<Recebimento> recebimentoList;
    @OneToMany(mappedBy = "pckId")
    private List<Pagamento> pagamentoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pck_id")
    private Long pckId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pckDt;
    @Lob
    @Size(max = 65535)
    @Column(name = "pck_descricao")
    private String pckDescricao;
    @Size(max = 20)
    @Column(name = "pck_imagem")
    private String pckImagem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_relatorio")
    private short pckRelatorio;
    @Size(max = 50)
    @Column(name = "pck_legenda_foto")
    private String pckLegendaFoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_ativo")
    private short pckAtivo;
    @Column(name = "pck_dt_promdev")
    @Temporal(TemporalType.DATE)
    private Date pckDtPromdev;
//    @JoinColumn(name = "usu_id_agente_novo", referencedColumnName = "usu_id")
//    @ManyToOne
//    private Usuario usuIdAgenteNovo;
    @JoinColumn(name = "ckt_id", referencedColumnName = "ckt_id")
    @ManyToOne(optional = false)
    private Ckptipo cktId;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne(optional = false)
    private Analise pbuId;
//    @JoinColumn(name = "usu_id_agente", referencedColumnName = "usu_id")
//    @ManyToOne
//    private Usuario usuIdAgente;
    @JoinColumn(name = "fas_id", referencedColumnName = "fas_id")
    @ManyToOne
    private Pedbusfase fasId;
    @JoinColumn(name = "smp_id", referencedColumnName = "smp_id")
    @ManyToOne
    private Submotivopadrao smpId;
    @JoinColumn(name = "usu_id_ckp", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuIdCkp;
    @JoinColumn(name = "usu_id_origem", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario usuIdOrigem;
    @JoinColumn(name = "usu_id_destino", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario usuIdDestino;
    @OneToMany(mappedBy = "pckIdAprovado")
    private List<Checkpoint> checkpointList;
    @JoinColumn(name = "pck_id_aprovado", referencedColumnName = "pck_id")
    @ManyToOne
    private Checkpoint pckIdAprovado;
    @JoinColumn(name = "ope_id", referencedColumnName = "ope_id")
    @ManyToOne
    private Operacao opeId;
    @JoinColumn(name = "eui_id", referencedColumnName = "eui_id")
    @ManyToOne
    private Equipe euiId;
    @OneToMany(mappedBy = "pckId")
    private List<Agente> agenteList;

    public Checkpoint() {
    }

    public Checkpoint(Long pckId) {
        this.pckId = pckId;
    }

    public Checkpoint(Long pckId, Date pckDt, short pckRelatorio, short pckAtivo) {
        this.pckId = pckId;
        this.pckDt = pckDt;
        this.pckRelatorio = pckRelatorio;
        this.pckAtivo = pckAtivo;
    }

    public Long getPckId() {
        return pckId;
    }

    public void setPckId(Long pckId) {
        this.pckId = pckId;
    }

    public Date getPckDt() {
        return pckDt;
    }

    public void setPckDt(Date pckDt) {
        this.pckDt = pckDt;
    }

    public String getPckDescricao() {
        return pckDescricao;
    }

    public void setPckDescricao(String pckDescricao) {
        this.pckDescricao = pckDescricao;
    }

    public String getPckImagem() {
        return pckImagem;
    }

    public void setPckImagem(String pckImagem) {
        this.pckImagem = pckImagem;
    }

    public short getPckRelatorio() {
        return pckRelatorio;
    }

    public void setPckRelatorio(short pckRelatorio) {
        this.pckRelatorio = pckRelatorio;
    }

    public String getPckLegendaFoto() {
        return pckLegendaFoto;
    }

    public void setPckLegendaFoto(String pckLegendaFoto) {
        this.pckLegendaFoto = pckLegendaFoto;
    }

    public short getPckAtivo() {
        return pckAtivo;
    }

    public void setPckAtivo(short pckAtivo) {
        this.pckAtivo = pckAtivo;
    }

    public Date getPckDtPromdev() {
        return pckDtPromdev;
    }

    public void setPckDtPromdev(Date pckDtPromdev) {
        this.pckDtPromdev = pckDtPromdev;
    }

//    public Usuario getUsuIdAgenteNovo() {
//        return usuIdAgenteNovo;
//    }

//    public void setUsuIdAgenteNovo(Usuario usuIdAgenteNovo) {
//        this.usuIdAgenteNovo = usuIdAgenteNovo;
//    }

    public Ckptipo getCktId() {
        return cktId;
    }

    public void setCktId(Ckptipo cktId) {
        this.cktId = cktId;
    }

    public Analise getPbuId() {
        return pbuId;
    }

    public void setPbuId(Analise pbuId) {
        this.pbuId = pbuId;
    }

//    public Usuario getUsuIdAgente() {
//        return usuIdAgente;
//    }

//    public void setUsuIdAgente(Usuario usuIdAgente) {
//        this.usuIdAgente = usuIdAgente;
//    }

    public Pedbusfase getFasId() {
        return fasId;
    }

    public void setFasId(Pedbusfase fasId) {
        this.fasId = fasId;
    }

    public Submotivopadrao getSmpId() {
        return smpId;
    }

    public void setSmpId(Submotivopadrao smpId) {
        this.smpId = smpId;
    }

    public Usuario getUsuIdCkp() {
        return usuIdCkp;
    }

    public void setUsuIdCkp(Usuario usuIdCkp) {
        this.usuIdCkp = usuIdCkp;
    }

    public Usuario getUsuIdOrigem() {
        return usuIdOrigem;
    }

    public void setUsuIdOrigem(Usuario usuIdOrigem) {
        this.usuIdOrigem = usuIdOrigem;
    }

    public Usuario getUsuIdDestino() {
        return usuIdDestino;
    }

    public void setUsuIdDestino(Usuario usuIdDestino) {
        this.usuIdDestino = usuIdDestino;
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }

    public Checkpoint getPckIdAprovado() {
        return pckIdAprovado;
    }

    public void setPckIdAprovado(Checkpoint pckIdAprovado) {
        this.pckIdAprovado = pckIdAprovado;
    }

    public Operacao getOpeId() {
        return opeId;
    }

    public void setOpeId(Operacao opeId) {
        this.opeId = opeId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pckId != null ? pckId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Checkpoint)) {
            return false;
        }
        Checkpoint other = (Checkpoint) object;
        if ((this.pckId == null && other.pckId != null) || (this.pckId != null && !this.pckId.equals(other.pckId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Checkpoint[ pckId=" + pckId + " ]";
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

    public Agente getAgeId() {
        return ageId;
    }

    public void setAgeId(Agente ageId) {
        this.ageId = ageId;
    }

    public Short getPckAprovadoms() {
        return pckAprovadoms;
    }

    public void setPckAprovadoms(Short pckAprovadoms) {
        this.pckAprovadoms = pckAprovadoms;
    }

    public Short getPckRelatoriofeito() {
        return pckRelatoriofeito;
    }

    public void setPckRelatoriofeito(Short pckRelatoriofeito) {
        this.pckRelatoriofeito = pckRelatoriofeito;
    }

    public Short getPckRecusado() {
        return pckRecusado;
    }

    public void setPckRecusado(Short pckRecusado) {
        this.pckRecusado = pckRecusado;
    }

    public Short getPckAprovado() {
        return pckAprovado;
    }

    public void setPckAprovado(Short pckAprovado) {
        this.pckAprovado = pckAprovado;
    }

    public Pedbusrel getPedbusrel() {
        return pedbusrel;
    }

    public void setPedbusrel(Pedbusrel pedbusrel) {
        this.pedbusrel = pedbusrel;
    }

    public String getPckRecupCidade() {
        return pckRecupCidade;
    }

    public void setPckRecupCidade(String pckRecupCidade) {
        this.pckRecupCidade = pckRecupCidade;
    }

    public Estado getEstIdRecup() {
        return estIdRecup;
    }

    public void setEstIdRecup(Estado estIdRecup) {
        this.estIdRecup = estIdRecup;
    }

    public CepLocalidade getLocNuRecup() {
        return locNuRecup;
    }

    public void setLocNuRecup(CepLocalidade locNuRecup) {
        this.locNuRecup = locNuRecup;
        this.setPckRecupCidade(locNuRecup.getLocNo());
    }

    public Date getPckDtPre() {
        return pckDtPre;
    }

    public void setPckDtPre(Date pckDtPre) {
        this.pckDtPre = pckDtPre;
    }

    public Pedbusit getPbsIdPre() {
        return pbsIdPre;
    }

    public void setPbsIdPre(Pedbusit pbsIdPre) {
        this.pbsIdPre = pbsIdPre;
    }
    
}
