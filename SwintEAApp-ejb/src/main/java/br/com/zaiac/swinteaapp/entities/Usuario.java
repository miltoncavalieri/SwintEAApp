/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_id")
    private Integer usuId;
    @Size(max = 15)
    @Column(name = "usu_login")
    private String usuLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "usu_nome")
    private String usuNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "usu_email")
    private String usuEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_aprovador")
    private short usuAprovador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_agente")
    private short usuAgente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_ativo")
    private short usuAtivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "usu_senha")
    private String usuSenha;
    @Column(name = "usu_dt_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuDtCadastro;
    @Column(name = "usu_dt_desativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuDtDesativacao;
    @Size(max = 15)
    @Column(name = "usu_cpf_cnpj")
    private String usuCpfCnpj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_coordenador")
    private short usuCoordenador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_aprovador_financeiro")
    private short usuAprovadorFinanceiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usu_apelido")
    private String usuApelido;
    @OneToMany(mappedBy = "usuId")
    private List<Pedbus> pedbusList;
    @OneToMany(mappedBy = "usuIdCoordenador")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId")
    private List<Analise> analiseList;
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
    @ManyToOne
    private Cliente cliId;
    @JoinColumn(name = "reg_id", referencedColumnName = "reg_id")
    @ManyToOne
    private Regiao regId;
    @JoinColumn(name = "sla_id", referencedColumnName = "sla_id")
    @ManyToOne
    private Sla slaId;
    @OneToMany(mappedBy = "usuIdCoordenador")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "usu_id_coordenador", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario usuIdCoordenador;
    @JoinColumn(name = "com_id", referencedColumnName = "com_id")
    @ManyToOne
    private Comissao comId;
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id")
    @ManyToOne(optional = false)
    private Tipopes pesId;

    public Usuario() {
    }

    public Usuario(Integer usuId) {
        this.usuId = usuId;
    }

    public Usuario(Integer usuId, String usuNome, String usuEmail, short usuAprovador, short usuAgente, short usuAtivo, String usuSenha, short usuCoordenador, short usuAprovadorFinanceiro, String usuApelido) {
        this.usuId = usuId;
        this.usuNome = usuNome;
        this.usuEmail = usuEmail;
        this.usuAprovador = usuAprovador;
        this.usuAgente = usuAgente;
        this.usuAtivo = usuAtivo;
        this.usuSenha = usuSenha;
        this.usuCoordenador = usuCoordenador;
        this.usuAprovadorFinanceiro = usuAprovadorFinanceiro;
        this.usuApelido = usuApelido;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public short getUsuAprovador() {
        return usuAprovador;
    }

    public void setUsuAprovador(short usuAprovador) {
        this.usuAprovador = usuAprovador;
    }

    public short getUsuAgente() {
        return usuAgente;
    }

    public void setUsuAgente(short usuAgente) {
        this.usuAgente = usuAgente;
    }

    public short getUsuAtivo() {
        return usuAtivo;
    }

    public void setUsuAtivo(short usuAtivo) {
        this.usuAtivo = usuAtivo;
    }

    public String getUsuSenha() {
        return usuSenha;
    }

    public void setUsuSenha(String usuSenha) {
        this.usuSenha = usuSenha;
    }

    public Date getUsuDtCadastro() {
        return usuDtCadastro;
    }

    public void setUsuDtCadastro(Date usuDtCadastro) {
        this.usuDtCadastro = usuDtCadastro;
    }

    public Date getUsuDtDesativacao() {
        return usuDtDesativacao;
    }

    public void setUsuDtDesativacao(Date usuDtDesativacao) {
        this.usuDtDesativacao = usuDtDesativacao;
    }

    public String getUsuCpfCnpj() {
        return usuCpfCnpj;
    }

    public void setUsuCpfCnpj(String usuCpfCnpj) {
        this.usuCpfCnpj = usuCpfCnpj;
    }

    public short getUsuCoordenador() {
        return usuCoordenador;
    }

    public void setUsuCoordenador(short usuCoordenador) {
        this.usuCoordenador = usuCoordenador;
    }

    public short getUsuAprovadorFinanceiro() {
        return usuAprovadorFinanceiro;
    }

    public void setUsuAprovadorFinanceiro(short usuAprovadorFinanceiro) {
        this.usuAprovadorFinanceiro = usuAprovadorFinanceiro;
    }

    public String getUsuApelido() {
        return usuApelido;
    }

    public void setUsuApelido(String usuApelido) {
        this.usuApelido = usuApelido;
    }

    @XmlTransient
    public List<Pedbus> getPedbusList() {
        return pedbusList;
    }

    public void setPedbusList(List<Pedbus> pedbusList) {
        this.pedbusList = pedbusList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    public Cliente getCliId() {
        return cliId;
    }

    public void setCliId(Cliente cliId) {
        this.cliId = cliId;
    }

    public Regiao getRegId() {
        return regId;
    }

    public void setRegId(Regiao regId) {
        this.regId = regId;
    }

    public Sla getSlaId() {
        return slaId;
    }

    public void setSlaId(Sla slaId) {
        this.slaId = slaId;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getUsuIdCoordenador() {
        return usuIdCoordenador;
    }

    public void setUsuIdCoordenador(Usuario usuIdCoordenador) {
        this.usuIdCoordenador = usuIdCoordenador;
    }

    public Comissao getComId() {
        return comId;
    }

    public void setComId(Comissao comId) {
        this.comId = comId;
    }

    public Tipopes getPesId() {
        return pesId;
    }

    public void setPesId(Tipopes pesId) {
        this.pesId = pesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Usuario[ usuId=" + usuId + " ]";
    }
    
}
