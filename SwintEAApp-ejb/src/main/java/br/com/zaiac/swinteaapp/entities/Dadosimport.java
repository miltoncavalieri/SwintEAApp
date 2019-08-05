/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author milton_cavalieri
 */
@Entity
@Table(name = "dadosimport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dadosimport.findAll", query = "SELECT d FROM Dadosimport d")})
public class Dadosimport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "drt_id")
    private Long drtId;
    @Size(max = 50)
    @Column(name = "drt_agencia")
    private String drtAgencia;
    @Size(max = 60)
    @Column(name = "drt_agencia_nome")
    private String drtAgenciaNome;
    @Size(max = 2)
    @Column(name = "drt_uf_agencia")
    private String drtUfAgencia;
    @Size(max = 20)
    @Column(name = "drt_contrato")
    private String drtContrato;
    @Size(max = 3)
    @Column(name = "drt_primloc")
    private String drtPrimloc;
    @Column(name = "drt_dt_abertura")
    @Temporal(TemporalType.DATE)
    private Date drtDtAbertura;
    @Column(name = "drt_dt_previsao_retorno")
    @Temporal(TemporalType.DATE)
    private Date drtDtPrevisaoRetorno;
    @Size(max = 3)
    @Column(name = "drt_tarifa_uber")
    private String drtTarifaUber;
    @Size(max = 15)
    @Column(name = "drt_codigo_regional")
    private String drtCodigoRegional;
    @Column(name = "drt_codigo_cliente")
    private BigInteger drtCodigoCliente;
    @Size(max = 60)
    @Column(name = "drt_nome_cliente")
    private String drtNomeCliente;
    @Size(max = 15)
    @Column(name = "drt_documento_cliente")
    private String drtDocumentoCliente;
    @Size(max = 20)
    @Column(name = "drt_telefone")
    private String drtTelefone;
    @Size(max = 20)
    @Column(name = "drt_celular")
    private String drtCelular;
    @Size(max = 10)
    @Column(name = "drt_tipoender")
    private String drtTipoender;
    @Size(max = 75)
    @Column(name = "drt_endereco")
    private String drtEndereco;
    @Size(max = 10)
    @Column(name = "drt_numero")
    private String drtNumero;
    @Size(max = 30)
    @Column(name = "drt_complemento")
    private String drtComplemento;
    @Size(max = 45)
    @Column(name = "drt_bairro")
    private String drtBairro;
    @Size(max = 8)
    @Column(name = "drt_cep")
    private String drtCep;
    @Size(max = 45)
    @Column(name = "drt_cidade")
    private String drtCidade;
    @Size(max = 2)
    @Column(name = "drt_uf")
    private String drtUf;
    @Size(max = 100)
    @Column(name = "drt_email")
    private String drtEmail;
    @Size(max = 7)
    @Column(name = "drt_placa")
    private String drtPlaca;
    @Size(max = 17)
    @Column(name = "drt_chassis")
    private String drtChassis;
    @Size(max = 45)
    @Column(name = "drt_modelo")
    private String drtModelo;
    @Size(max = 50)
    @Column(name = "drt_cor")
    private String drtCor;
    @Size(max = 100)
    @Column(name = "drt_observacao")
    private String drtObservacao;
    @Size(max = 1)
    @Column(name = "drt_ascli")
    private String drtAscli;
    @Column(name = "drt_dt_envio")
    @Temporal(TemporalType.DATE)
    private Date drtDtEnvio;
    @Column(name = "drt_erro")
    private Short drtErro;
    @JoinColumn(name = "lrt_id", referencedColumnName = "lrt_id")
    @ManyToOne(optional = false)
    private Loteimport lrtId;
    @OneToMany(mappedBy = "drtId")
    private List<Analise> analiseList;

    public Dadosimport() {
    }

    public Dadosimport(Long drtId) {
        this.drtId = drtId;
    }

    public Long getDrtId() {
        return drtId;
    }

    public void setDrtId(Long drtId) {
        this.drtId = drtId;
    }

    public String getDrtAgencia() {
        return drtAgencia;
    }

    public void setDrtAgencia(String drtAgencia) {
        this.drtAgencia = drtAgencia;
    }

    public String getDrtAgenciaNome() {
        return drtAgenciaNome;
    }

    public void setDrtAgenciaNome(String drtAgenciaNome) {
        this.drtAgenciaNome = drtAgenciaNome;
    }

    public String getDrtUfAgencia() {
        return drtUfAgencia;
    }

    public void setDrtUfAgencia(String drtUfAgencia) {
        this.drtUfAgencia = drtUfAgencia;
    }

    public String getDrtContrato() {
        return drtContrato;
    }

    public void setDrtContrato(String drtContrato) {
        this.drtContrato = drtContrato;
    }

    public String getDrtPrimloc() {
        return drtPrimloc;
    }

    public void setDrtPrimloc(String drtPrimloc) {
        this.drtPrimloc = drtPrimloc;
    }

    public Date getDrtDtAbertura() {
        return drtDtAbertura;
    }

    public void setDrtDtAbertura(Date drtDtAbertura) {
        this.drtDtAbertura = drtDtAbertura;
    }

    public Date getDrtDtPrevisaoRetorno() {
        return drtDtPrevisaoRetorno;
    }

    public void setDrtDtPrevisaoRetorno(Date drtDtPrevisaoRetorno) {
        this.drtDtPrevisaoRetorno = drtDtPrevisaoRetorno;
    }

    public String getDrtTarifaUber() {
        return drtTarifaUber;
    }

    public void setDrtTarifaUber(String drtTarifaUber) {
        this.drtTarifaUber = drtTarifaUber;
    }

    public String getDrtCodigoRegional() {
        return drtCodigoRegional;
    }

    public void setDrtCodigoRegional(String drtCodigoRegional) {
        this.drtCodigoRegional = drtCodigoRegional;
    }

    public BigInteger getDrtCodigoCliente() {
        return drtCodigoCliente;
    }

    public void setDrtCodigoCliente(BigInteger drtCodigoCliente) {
        this.drtCodigoCliente = drtCodigoCliente;
    }

    public String getDrtNomeCliente() {
        return drtNomeCliente;
    }

    public void setDrtNomeCliente(String drtNomeCliente) {
        this.drtNomeCliente = drtNomeCliente;
    }

    public String getDrtDocumentoCliente() {
        return drtDocumentoCliente;
    }

    public void setDrtDocumentoCliente(String drtDocumentoCliente) {
        this.drtDocumentoCliente = drtDocumentoCliente;
    }

    public String getDrtTelefone() {
        return drtTelefone;
    }

    public void setDrtTelefone(String drtTelefone) {
        this.drtTelefone = drtTelefone;
    }

    public String getDrtCelular() {
        return drtCelular;
    }

    public void setDrtCelular(String drtCelular) {
        this.drtCelular = drtCelular;
    }

    public String getDrtTipoender() {
        return drtTipoender;
    }

    public void setDrtTipoender(String drtTipoender) {
        this.drtTipoender = drtTipoender;
    }

    public String getDrtEndereco() {
        return drtEndereco;
    }

    public void setDrtEndereco(String drtEndereco) {
        this.drtEndereco = drtEndereco;
    }

    public String getDrtNumero() {
        return drtNumero;
    }

    public void setDrtNumero(String drtNumero) {
        this.drtNumero = drtNumero;
    }

    public String getDrtComplemento() {
        return drtComplemento;
    }

    public void setDrtComplemento(String drtComplemento) {
        this.drtComplemento = drtComplemento;
    }

    public String getDrtBairro() {
        return drtBairro;
    }

    public void setDrtBairro(String drtBairro) {
        this.drtBairro = drtBairro;
    }

    public String getDrtCep() {
        return drtCep;
    }

    public void setDrtCep(String drtCep) {
        this.drtCep = drtCep;
    }

    public String getDrtCidade() {
        return drtCidade;
    }

    public void setDrtCidade(String drtCidade) {
        this.drtCidade = drtCidade;
    }

    public String getDrtUf() {
        return drtUf;
    }

    public void setDrtUf(String drtUf) {
        this.drtUf = drtUf;
    }

    public String getDrtEmail() {
        return drtEmail;
    }

    public void setDrtEmail(String drtEmail) {
        this.drtEmail = drtEmail;
    }

    public String getDrtPlaca() {
        return drtPlaca;
    }

    public void setDrtPlaca(String drtPlaca) {
        this.drtPlaca = drtPlaca;
    }

    public String getDrtChassis() {
        return drtChassis;
    }

    public void setDrtChassis(String drtChassis) {
        this.drtChassis = drtChassis;
    }

    public String getDrtModelo() {
        return drtModelo;
    }

    public void setDrtModelo(String drtModelo) {
        this.drtModelo = drtModelo;
    }

    public String getDrtCor() {
        return drtCor;
    }

    public void setDrtCor(String drtCor) {
        this.drtCor = drtCor;
    }

    public String getDrtObservacao() {
        return drtObservacao;
    }

    public void setDrtObservacao(String drtObservacao) {
        this.drtObservacao = drtObservacao;
    }

    public String getDrtAscli() {
        return drtAscli;
    }

    public void setDrtAscli(String drtAscli) {
        this.drtAscli = drtAscli;
    }

    public Date getDrtDtEnvio() {
        return drtDtEnvio;
    }

    public void setDrtDtEnvio(Date drtDtEnvio) {
        this.drtDtEnvio = drtDtEnvio;
    }

    public Short getDrtErro() {
        return drtErro;
    }

    public void setDrtErro(Short drtErro) {
        this.drtErro = drtErro;
    }

    public Loteimport getLrtId() {
        return lrtId;
    }

    public void setLrtId(Loteimport lrtId) {
        this.lrtId = lrtId;
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
        hash += (drtId != null ? drtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dadosimport)) {
            return false;
        }
        Dadosimport other = (Dadosimport) object;
        if ((this.drtId == null && other.drtId != null) || (this.drtId != null && !this.drtId.equals(other.drtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Dadosimport[ drtId=" + drtId + " ]";
    }
    
}
