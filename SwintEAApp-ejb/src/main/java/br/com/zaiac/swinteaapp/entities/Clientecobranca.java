package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "clientecobranca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientecobranca.findAll", query = "SELECT c FROM Clientecobranca c"),
    @NamedQuery(name = "Clientecobranca.findByCliId", query = "SELECT c FROM Clientecobranca c WHERE c.clientecobrancaPK.cliId = :cliId"),
    @NamedQuery(name = "Clientecobranca.deleteByCliIdClbDt", query = "DELETE FROM Clientecobranca c WHERE c.clientecobrancaPK = :clientecobrancaPK"),
    @NamedQuery(name = "Clientecobranca.findByCliIdClbDt", 
            query = "SELECT c FROM Clientecobranca c WHERE c.clientecobrancaPK.cliId = :cliId AND c.clientecobrancaPK.clbDt < :clbDt ORDER BY c.clientecobrancaPK.clbDt DESC)"),
    @NamedQuery(name = "Clientecobranca.findVlrCobrancaCliente", 
            query = "SELECT c FROM Clientecobranca c WHERE c.clientecobrancaPK.cliId = :cliId AND c.clientecobrancaPK.clbDt = (SELECT MAX(c1.clientecobrancaPK.clbDt) FROM Clientecobranca c1 WHERE (c1.clientecobrancaPK.cliId = :cliId) AND (c1.clientecobrancaPK.clbDt <= :rcbDt))"),
})
public class Clientecobranca implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_patio")
    private BigDecimal clbVlrPatio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_delegacia")
    private BigDecimal clbVlrDelegacia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_patio_rastreado")
    private BigDecimal clbVlrPatioRastreado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_delegacia_rastreado")
    private BigDecimal clbVlrDelegaciaRastreado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_furtoroubo")
    private BigDecimal clbVlrFurtoroubo;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClientecobrancaPK clientecobrancaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_investigacao")
    private BigDecimal clbVlrInvestigacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_recuperacao")
    private BigDecimal clbVlrRecuperacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_vlr_rastreado")
    private BigDecimal clbVlrRastreado;
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Clientecobranca() {
    }

    public Clientecobranca(ClientecobrancaPK clientecobrancaPK) {
        this.clientecobrancaPK = clientecobrancaPK;
    }

    public Clientecobranca(ClientecobrancaPK clientecobrancaPK, BigDecimal clbVlrInvestigacao, BigDecimal clbVlrRecuperacao, BigDecimal clbVlrRastreado) {
        this.clientecobrancaPK = clientecobrancaPK;
        this.clbVlrInvestigacao = clbVlrInvestigacao;
        this.clbVlrRecuperacao = clbVlrRecuperacao;
        this.clbVlrRastreado = clbVlrRastreado;
    }

    public Clientecobranca(long cliId, Date clbDt) {
        this.clientecobrancaPK = new ClientecobrancaPK(cliId, clbDt);
    }

    public ClientecobrancaPK getClientecobrancaPK() {
        return clientecobrancaPK;
    }

    public void setClientecobrancaPK(ClientecobrancaPK clientecobrancaPK) {
        this.clientecobrancaPK = clientecobrancaPK;
    }

    public BigDecimal getClbVlrInvestigacao() {
        return clbVlrInvestigacao;
    }

    public void setClbVlrInvestigacao(BigDecimal clbVlrInvestigacao) {
        this.clbVlrInvestigacao = clbVlrInvestigacao;
    }

    public BigDecimal getClbVlrRecuperacao() {
        return clbVlrRecuperacao;
    }

    public void setClbVlrRecuperacao(BigDecimal clbVlrRecuperacao) {
        this.clbVlrRecuperacao = clbVlrRecuperacao;
    }

    public BigDecimal getClbVlrRastreado() {
        return clbVlrRastreado;
    }

    public void setClbVlrRastreado(BigDecimal clbVlrRastreado) {
        this.clbVlrRastreado = clbVlrRastreado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientecobrancaPK != null ? clientecobrancaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Clientecobranca)) {
            return false;
        }
        Clientecobranca other = (Clientecobranca) object;
        if ((this.clientecobrancaPK == null && other.clientecobrancaPK != null) || (this.clientecobrancaPK != null && !this.clientecobrancaPK.equals(other.clientecobrancaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clientecobranca[ clientecobrancaPK=" + clientecobrancaPK + " ]";
    }

    public BigDecimal getClbVlrFurtoroubo() {
        return clbVlrFurtoroubo;
    }

    public void setClbVlrFurtoroubo(BigDecimal clbVlrFurtoroubo) {
        this.clbVlrFurtoroubo = clbVlrFurtoroubo;
    }

    public BigDecimal getClbVlrPatio() {
        return clbVlrPatio;
    }

    public void setClbVlrPatio(BigDecimal clbVlrPatio) {
        this.clbVlrPatio = clbVlrPatio;
    }

    public BigDecimal getClbVlrDelegacia() {
        return clbVlrDelegacia;
    }

    public void setClbVlrDelegacia(BigDecimal clbVlrDelegacia) {
        this.clbVlrDelegacia = clbVlrDelegacia;
    }

    public BigDecimal getClbVlrPatioRastreado() {
        return clbVlrPatioRastreado;
    }

    public void setClbVlrPatioRastreado(BigDecimal clbVlrPatioRastreado) {
        this.clbVlrPatioRastreado = clbVlrPatioRastreado;
    }

    public BigDecimal getClbVlrDelegaciaRastreado() {
        return clbVlrDelegaciaRastreado;
    }

    public void setClbVlrDelegaciaRastreado(BigDecimal clbVlrDelegaciaRastreado) {
        this.clbVlrDelegaciaRastreado = clbVlrDelegaciaRastreado;
    }
    
}
