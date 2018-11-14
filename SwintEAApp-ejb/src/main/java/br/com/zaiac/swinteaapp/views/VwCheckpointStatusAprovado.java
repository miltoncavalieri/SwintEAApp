package br.com.zaiac.swinteaapp.views;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "vw_checkpoint_status_aprovado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwCheckpointStatusAprovado.findAll", query = "SELECT v FROM VwCheckpointStatusAprovado v")})
public class VwCheckpointStatusAprovado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Id
    @Column(name = "pck_id")
    private long pckId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbu_id")
    private long pbuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pck_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pckDt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ckt_id")
    private short cktId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_id_ckp")
    private int usuIdCkp;
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
    @Column(name = "usu_id_origem")
    private Integer usuIdOrigem;
    @Column(name = "usu_id_destino")
    private Integer usuIdDestino;
    @Column(name = "pck_id_aprovado")
    private BigInteger pckIdAprovado;

    public VwCheckpointStatusAprovado() {
    }

    public long getPckId() {
        return pckId;
    }

    public void setPckId(long pckId) {
        this.pckId = pckId;
    }

    public long getPbuId() {
        return pbuId;
    }

    public void setPbuId(long pbuId) {
        this.pbuId = pbuId;
    }

    public Date getPckDt() {
        return pckDt;
    }

    public void setPckDt(Date pckDt) {
        this.pckDt = pckDt;
    }

    public short getCktId() {
        return cktId;
    }

    public void setCktId(short cktId) {
        this.cktId = cktId;
    }

    public int getUsuIdCkp() {
        return usuIdCkp;
    }

    public void setUsuIdCkp(int usuIdCkp) {
        this.usuIdCkp = usuIdCkp;
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

    public Integer getUsuIdOrigem() {
        return usuIdOrigem;
    }

    public void setUsuIdOrigem(Integer usuIdOrigem) {
        this.usuIdOrigem = usuIdOrigem;
    }

    public Integer getUsuIdDestino() {
        return usuIdDestino;
    }

    public void setUsuIdDestino(Integer usuIdDestino) {
        this.usuIdDestino = usuIdDestino;
    }

    public BigInteger getPckIdAprovado() {
        return pckIdAprovado;
    }

    public void setPckIdAprovado(BigInteger pckIdAprovado) {
        this.pckIdAprovado = pckIdAprovado;
    }
    
}
