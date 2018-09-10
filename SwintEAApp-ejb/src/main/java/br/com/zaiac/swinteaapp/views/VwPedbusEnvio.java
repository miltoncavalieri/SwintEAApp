package br.com.zaiac.swinteaapp.views;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.Normalizer;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "vw_pedbus_envio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwPedbusEnvio.findAll", query = "SELECT v FROM VwPedbusEnvio v")})
public class VwPedbusEnvio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_id")
    private long pbuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pbuDt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ana_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anaDt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "ana_veiculo_placa")
    private String anaVeiculoPlaca;
    @Size(max = 45)
    @Column(name = "mod_nome")
    private String modNome;
    @Size(max = 50)
    @Column(name = "cor_nome")
    private String corNome;
    @Column(name = "cli_id")
    private BigInteger cliId;
    @Size(max = 50)
    @Column(name = "cli_nome")
    private String cliNome;
    @Size(max = 10)
    @Column(name = "cli_sigla")
    private String cliSigla;
    @Size(max = 50)
    @Column(name = "pbu_invest_cidade")
    private String pbuInvestCidade;
    @Size(max = 2)
    @Column(name = "est_invest_uf")
    private String estInvestUf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbs_id")
    private short pbsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pbs_nome")
    private String pbsNome;

    public VwPedbusEnvio() {
    }

    public long getPbuId() {
        return pbuId;
    }

    public void setPbuId(long pbuId) {
        this.pbuId = pbuId;
    }

    public Date getPbuDt() {
        return pbuDt;
    }

    public void setPbuDt(Date pbuDt) {
        this.pbuDt = pbuDt;
    }

    public Date getAnaDt() {
        return anaDt;
    }

    public void setAnaDt(Date anaDt) {
        this.anaDt = anaDt;
    }

    public String getAnaVeiculoPlaca() {
        return anaVeiculoPlaca;
    }

    public void setAnaVeiculoPlaca(String anaVeiculoPlaca) {
        this.anaVeiculoPlaca = anaVeiculoPlaca;
    }

    public String getModNome() {
        return modNome;
    }

    public void setModNome(String modNome) {
        this.modNome = modNome;
    }

    public String getCorNome() {
        return corNome;
    }

    public void setCorNome(String corNome) {
        this.corNome = corNome;
    }

    public BigInteger getCliId() {
        return cliId;
    }

    public void setCliId(BigInteger cliId) {
        this.cliId = cliId;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliSigla() {
        return cliSigla;
    }

    public void setCliSigla(String cliSigla) {
        this.cliSigla = cliSigla;
    }

    public String getPbuInvestCidade() {
        return pbuInvestCidade;
    }

    public void setPbuInvestCidade(String pbuInvestCidade) {
        this.pbuInvestCidade = pbuInvestCidade;
    }

    public String getEstInvestUf() {
        return estInvestUf;
    }

    public void setEstInvestUf(String estInvestUf) {
        this.estInvestUf = estInvestUf;
    }

    public short getPbsId() {
        return pbsId;
    }

    public void setPbsId(short pbsId) {
        this.pbsId = pbsId;
    }

    public String getPbsNome() {
        return pbsNome;
    }

    public void setPbsNome(String pbsNome) {
        this.pbsNome = pbsNome;
    }
    public String getReferencia() {
        String subject = "PB_" + getPbuId();
        if (!Objects.isNull(getCliNome())) {
            if (getCliSigla().length() >5) {
                subject += "_" + getCliSigla().substring(0, 5).trim();
            } else {
                subject += "_" + getCliNome().substring(0, getCliNome().length()).trim();
            }
        }
        subject += "_" + getAnaVeiculoPlaca();
        
        if (!Objects.isNull(getModNome())) {
            if(getModNome().length() > 1) {
                subject += "_" + getModNome();
            }
        }
        
        if (!Objects.isNull(getPbuInvestCidade())) {
            if(getPbuInvestCidade().length() > 1) {
                subject += "_" + getPbuInvestCidade();
            }
        }
                    
        if (!Objects.isNull(getEstInvestUf())) {
            if(getEstInvestUf().length() > 1) {
                subject += "_" + getEstInvestUf();
            }
        }
        subject = subject.replaceAll(" ", "");
        subject = Normalizer.normalize(subject, Normalizer.Form.NFD);
        subject = subject.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");                
        return subject;
    }
    
    
}
