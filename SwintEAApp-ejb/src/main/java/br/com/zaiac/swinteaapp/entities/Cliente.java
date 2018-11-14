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

@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCliId", query = "SELECT c FROM Cliente c WHERE c.cliId = :cliId")
})
public class Cliente implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Clientecobranca> clientecobrancaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliId")
    private List<Lotercbo> lotercboList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cli_id")
    private Long cliId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cli_nome")
    private String cliNome;
    @Size(max = 60)
    @Column(name = "cli_email")
    private String cliEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cli_dt_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cliDtCadastro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cli_sigla")
    private String cliSigla;
    @Size(max = 40)
    @Column(name = "cli_nome_fantasia")
    private String cliNomeFantasia;
    @Size(max = 15)
    @Column(name = "cli_cpf_cnpj")
    private String cliCpfCnpj;
    @JoinColumn(name = "usu_id_coordenador", referencedColumnName = "usu_id")
    @ManyToOne
    private Usuario usuIdCoordenador;
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id")
    @ManyToOne
    private Tipopes pesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliId")
    private List<Clientefilial> clientefilialList;
    @OneToMany(mappedBy = "cliId")
    private List<Analise> analiseList;
    @OneToMany(mappedBy = "cliId")
    private List<Usuario> usuarioList;

    public Cliente() {
    }

    public Cliente(Long cliId) {
        this.cliId = cliId;
    }

    public Cliente(Long cliId, String cliNome, Date cliDtCadastro, String cliSigla) {
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliDtCadastro = cliDtCadastro;
        this.cliSigla = cliSigla;
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public Date getCliDtCadastro() {
        return cliDtCadastro;
    }

    public void setCliDtCadastro(Date cliDtCadastro) {
        this.cliDtCadastro = cliDtCadastro;
    }

    public String getCliSigla() {
        return cliSigla;
    }

    public void setCliSigla(String cliSigla) {
        this.cliSigla = cliSigla;
    }

    public String getCliNomeFantasia() {
        return cliNomeFantasia;
    }

    public void setCliNomeFantasia(String cliNomeFantasia) {
        this.cliNomeFantasia = cliNomeFantasia;
    }

    public String getCliCpfCnpj() {
        return cliCpfCnpj;
    }

    public void setCliCpfCnpj(String cliCpfCnpj) {
        this.cliCpfCnpj = cliCpfCnpj;
    }

    public Usuario getUsuIdCoordenador() {
        return usuIdCoordenador;
    }

    public void setUsuIdCoordenador(Usuario usuIdCoordenador) {
        this.usuIdCoordenador = usuIdCoordenador;
    }

    public Tipopes getPesId() {
        return pesId;
    }

    public void setPesId(Tipopes pesId) {
        this.pesId = pesId;
    }

    @XmlTransient
    public List<Clientefilial> getClientefilialList() {
        return clientefilialList;
    }

    public void setClientefilialList(List<Clientefilial> clientefilialList) {
        this.clientefilialList = clientefilialList;
    }

    @XmlTransient
    public List<Analise> getAnaliseList() {
        return analiseList;
    }

    public void setAnaliseList(List<Analise> analiseList) {
        this.analiseList = analiseList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliId != null ? cliId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cliId == null && other.cliId != null) || (this.cliId != null && !this.cliId.equals(other.cliId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Cliente[ cliId=" + cliId + " ]";
    }

    @XmlTransient
    public List<Lotercbo> getLotercboList() {
        return lotercboList;
    }

    public void setLotercboList(List<Lotercbo> lotercboList) {
        this.lotercboList = lotercboList;
    }

    @XmlTransient
    public List<Clientecobranca> getClientecobrancaList() {
        return clientecobrancaList;
    }

    public void setClientecobrancaList(List<Clientecobranca> clientecobrancaList) {
        this.clientecobrancaList = clientecobrancaList;
    }
    
}
