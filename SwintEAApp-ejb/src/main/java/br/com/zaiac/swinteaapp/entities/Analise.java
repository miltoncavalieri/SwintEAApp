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
@Table(name = "analise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analise.findAll", query = "SELECT a FROM Analise a"),
    @NamedQuery(name = "Analise.findByPbuId", query = "SELECT a FROM Analise a WHERE a.pbuId = :pbuId"),
    @NamedQuery(name = "Analise.findPbEmCampoPorOperacao", query = "SELECT a FROM Analise a JOIN a.pedbus p WHERE a.anaAtivo = 1 and p.pbsId IN (1,4) and p.opeId = :opeId")
})
public class Analise implements Serializable {

    @JoinColumn(name = "ana_veiculo_placa", referencedColumnName = "vei_placa")
    @ManyToOne(optional = false)
    private Veiculo anaVeiculoPlaca;

    @JoinColumn(name = "eui_id", referencedColumnName = "eui_id")
    @ManyToOne
    private Equipe euiId;
    @JoinColumn(name = "drt_id", referencedColumnName = "drt_id")
    @ManyToOne
    private Dadosimport drtId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_dt_receb")
    @Temporal(TemporalType.DATE)
    private Date anaDtReceb;

    @OneToMany(mappedBy = "pbuId")
    private List<Pagamento> pagamentoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pbuId")
    private List<Checkpoint> checkpointList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pbuId")
    private List<Ckpleitura> ckpleituraList;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_ativo")
    private short anaAtivo;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anaDt;
    @Size(max = 20)
    @Column(name = "ana_nro_contrato")
    private String anaNroContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_bo")
    private short anaBo;
    @Column(name = "ana_dt_locacao")
    @Temporal(TemporalType.DATE)
    private Date anaDtLocacao;
    @Size(max = 50)
    @Column(name = "ana_cidade_locacao")
    private String anaCidadeLocacao;
    @Column(name = "ana_dt_devolucao")
    @Temporal(TemporalType.DATE)
    private Date anaDtDevolucao;
    @Size(max = 50)
    @Column(name = "ana_cidade_devolucao")
    private String anaCidadeDevolucao;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 7)
//    @Column(name = "ana_veiculo_placa")
//    private String anaVeiculoPlaca;
    @Size(max = 11)
    @Column(name = "ana_veiculo_renavam")
    private String anaVeiculoRenavam;
    @Size(max = 17)
    @Column(name = "ana_veiculo_chassis")
    private String anaVeiculoChassis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_locatario")
    private short anaLocatario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_condutores")
    private short anaCondutores;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_endloca")
    private short anaEndloca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_telloca")
    private short anaTelloca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_rastreado")
    private short anaRastreado;
    @Lob
    @Size(max = 65535)
    @Column(name = "ana_inflevantada")
    private String anaInflevantada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_rastreador_removido")
    private short anaRastreadorRemovido;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pbu_id")
    private Long pbuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_priloc")
    private short anaPriloc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pbuId")
    private List<Analisedoc> analisedocList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "analise")
    private Pedbus pedbus;
    @JoinColumn(name = "mob_id", referencedColumnName = "mob_id")
    @ManyToOne
    private Mobapp mobId;
    @JoinColumn(name = "sit_id", referencedColumnName = "sit_id")
    @ManyToOne(optional = false)
    private Analisesit sitId;
    @JoinColumn(name = "loc_nu_devolucao", referencedColumnName = "loc_nu")
    @ManyToOne
    private CepLocalidade locNuDevolucao;
    @JoinColumn(name = "fil_id", referencedColumnName = "fil_id")
    @ManyToOne
    private Clientefilial filId;
    @JoinColumn(name = "mod_id", referencedColumnName = "mod_id")
    @ManyToOne
    private Modelo modId;
    @JoinColumn(name = "cor_id", referencedColumnName = "cor_id")
    @ManyToOne
    private Cor corId;
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
    @ManyToOne
    private Cliente cliId;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuario usuId;
    @JoinColumn(name = "est_id_locacao", referencedColumnName = "est_id")
    @ManyToOne
    private Estado estIdLocacao;
    @JoinColumn(name = "est_id_devolucao", referencedColumnName = "est_id")
    @ManyToOne
    private Estado estIdDevolucao;
    @JoinColumn(name = "loc_nu_locacao", referencedColumnName = "loc_nu")
    @ManyToOne
    private CepLocalidade locNuLocacao;

    public Analise() {
    }

    public Analise(Long pbuId) {
        this.pbuId = pbuId;
    }

    public Analise(Long pbuId, Date anaDt, short anaBo, short anaLocatario, short anaCondutores, short anaEndloca, short anaTelloca, short anaRastreado, short anaRastreadorRemovido, short anaPriloc) {
        this.pbuId = pbuId;
        this.anaDt = anaDt;
        this.anaBo = anaBo;
//        this.anaVeiculoPlaca = anaVeiculoPlaca;
        this.anaLocatario = anaLocatario;
        this.anaCondutores = anaCondutores;
        this.anaEndloca = anaEndloca;
        this.anaTelloca = anaTelloca;
        this.anaRastreado = anaRastreado;
        this.anaRastreadorRemovido = anaRastreadorRemovido;
        this.anaPriloc = anaPriloc;
    }

    public Date getAnaDt() {
        return anaDt;
    }

    public void setAnaDt(Date anaDt) {
        this.anaDt = anaDt;
    }

    public String getAnaNroContrato() {
        return anaNroContrato;
    }

    public void setAnaNroContrato(String anaNroContrato) {
        this.anaNroContrato = anaNroContrato;
    }

    public short getAnaBo() {
        return anaBo;
    }

    public void setAnaBo(short anaBo) {
        this.anaBo = anaBo;
    }

    public Date getAnaDtLocacao() {
        return anaDtLocacao;
    }

    public void setAnaDtLocacao(Date anaDtLocacao) {
        this.anaDtLocacao = anaDtLocacao;
    }

    public String getAnaCidadeLocacao() {
        return anaCidadeLocacao;
    }

    public void setAnaCidadeLocacao(String anaCidadeLocacao) {
        this.anaCidadeLocacao = anaCidadeLocacao;
    }

    public Date getAnaDtDevolucao() {
        return anaDtDevolucao;
    }

    public void setAnaDtDevolucao(Date anaDtDevolucao) {
        this.anaDtDevolucao = anaDtDevolucao;
    }

    public String getAnaCidadeDevolucao() {
        return anaCidadeDevolucao;
    }

    public void setAnaCidadeDevolucao(String anaCidadeDevolucao) {
        this.anaCidadeDevolucao = anaCidadeDevolucao;
    }

//    public String getAnaVeiculoPlaca() {
//        return anaVeiculoPlaca;
//    }

//    public void setAnaVeiculoPlaca(String anaVeiculoPlaca) {
//        this.anaVeiculoPlaca = anaVeiculoPlaca;
//    }

    public String getAnaVeiculoRenavam() {
        return anaVeiculoRenavam;
    }

    public void setAnaVeiculoRenavam(String anaVeiculoRenavam) {
        this.anaVeiculoRenavam = anaVeiculoRenavam;
    }

    public String getAnaVeiculoChassis() {
        return anaVeiculoChassis;
    }

    public void setAnaVeiculoChassis(String anaVeiculoChassis) {
        this.anaVeiculoChassis = anaVeiculoChassis;
    }

    public short getAnaLocatario() {
        return anaLocatario;
    }

    public void setAnaLocatario(short anaLocatario) {
        this.anaLocatario = anaLocatario;
    }

    public short getAnaCondutores() {
        return anaCondutores;
    }

    public void setAnaCondutores(short anaCondutores) {
        this.anaCondutores = anaCondutores;
    }

    public short getAnaEndloca() {
        return anaEndloca;
    }

    public void setAnaEndloca(short anaEndloca) {
        this.anaEndloca = anaEndloca;
    }

    public short getAnaTelloca() {
        return anaTelloca;
    }

    public void setAnaTelloca(short anaTelloca) {
        this.anaTelloca = anaTelloca;
    }

    public short getAnaRastreado() {
        return anaRastreado;
    }

    public void setAnaRastreado(short anaRastreado) {
        this.anaRastreado = anaRastreado;
    }

    public String getAnaInflevantada() {
        return anaInflevantada;
    }

    public void setAnaInflevantada(String anaInflevantada) {
        this.anaInflevantada = anaInflevantada;
    }

    public short getAnaRastreadorRemovido() {
        return anaRastreadorRemovido;
    }

    public void setAnaRastreadorRemovido(short anaRastreadorRemovido) {
        this.anaRastreadorRemovido = anaRastreadorRemovido;
    }

    public Long getPbuId() {
        return pbuId;
    }

    public void setPbuId(Long pbuId) {
        this.pbuId = pbuId;
    }

    public short getAnaPriloc() {
        return anaPriloc;
    }

    public void setAnaPriloc(short anaPriloc) {
        this.anaPriloc = anaPriloc;
    }

    @XmlTransient
    public List<Analisedoc> getAnalisedocList() {
        return analisedocList;
    }

    public void setAnalisedocList(List<Analisedoc> analisedocList) {
        this.analisedocList = analisedocList;
    }

    public Pedbus getPedbus() {
        return pedbus;
    }

    public void setPedbus(Pedbus pedbus) {
        this.pedbus = pedbus;
    }

    public Mobapp getMobId() {
        return mobId;
    }

    public void setMobId(Mobapp mobId) {
        this.mobId = mobId;
    }

    public Analisesit getSitId() {
        return sitId;
    }

    public void setSitId(Analisesit sitId) {
        this.sitId = sitId;
    }

    public CepLocalidade getLocNuDevolucao() {
        return locNuDevolucao;
    }

    public void setLocNuDevolucao(CepLocalidade locNuDevolucao) {
        this.locNuDevolucao = locNuDevolucao;
    }

    public Clientefilial getFilId() {
        return filId;
    }

    public void setFilId(Clientefilial filId) {
        this.filId = filId;
    }

    public Modelo getModId() {
        return modId;
    }

    public void setModId(Modelo modId) {
        this.modId = modId;
    }

    public Cor getCorId() {
        return corId;
    }

    public void setCorId(Cor corId) {
        this.corId = corId;
    }

    public Cliente getCliId() {
        return cliId;
    }

    public void setCliId(Cliente cliId) {
        this.cliId = cliId;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    public Estado getEstIdLocacao() {
        return estIdLocacao;
    }

    public void setEstIdLocacao(Estado estIdLocacao) {
        this.estIdLocacao = estIdLocacao;
    }

    public Estado getEstIdDevolucao() {
        return estIdDevolucao;
    }

    public void setEstIdDevolucao(Estado estIdDevolucao) {
        this.estIdDevolucao = estIdDevolucao;
    }

    public CepLocalidade getLocNuLocacao() {
        return locNuLocacao;
    }

    public void setLocNuLocacao(CepLocalidade locNuLocacao) {
        this.locNuLocacao = locNuLocacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pbuId != null ? pbuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Analise)) {
            return false;
        }
        Analise other = (Analise) object;
        if ((this.pbuId == null && other.pbuId != null) || (this.pbuId != null && !this.pbuId.equals(other.pbuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Analise[ pbuId=" + pbuId + " ]";
    }

    public short getAnaAtivo() {
        return anaAtivo;
    }

    public void setAnaAtivo(short anaAtivo) {
        this.anaAtivo = anaAtivo;
    }

    @XmlTransient
    public List<Ckpleitura> getCkpleituraList() {
        return ckpleituraList;
    }

    public void setCkpleituraList(List<Ckpleitura> ckpleituraList) {
        this.ckpleituraList = ckpleituraList;
    }

    @XmlTransient
    public List<Checkpoint> getCheckpointList() {
        return checkpointList;
    }

    public void setCheckpointList(List<Checkpoint> checkpointList) {
        this.checkpointList = checkpointList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    public Date getAnaDtReceb() {
        return anaDtReceb;
    }

    public void setAnaDtReceb(Date anaDtReceb) {
        this.anaDtReceb = anaDtReceb;
    }

    public Equipe getEuiId() {
        return euiId;
    }

    public void setEuiId(Equipe euiId) {
        this.euiId = euiId;
    }

    public Dadosimport getDrtId() {
        return drtId;
    }

    public void setDrtId(Dadosimport drtId) {
        this.drtId = drtId;
    }

    public Veiculo getAnaVeiculoPlaca() {
        return anaVeiculoPlaca;
    }

    public void setAnaVeiculoPlaca(Veiculo anaVeiculoPlaca) {
        this.anaVeiculoPlaca = anaVeiculoPlaca;
    }
    
}
