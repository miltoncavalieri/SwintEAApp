package br.com.zaiac.swinteaapp.views;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "vw_pbemailtocc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwPbemailtocc.findAll", query = "SELECT v FROM VwPbemailtocc v"),
    @NamedQuery(name = "VwPbemailtocc.findByPbuId", query = "SELECT v FROM VwPbemailtocc v WHERE v.pbuId = :pbuId")})
public class VwPbemailtocc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Id
    @Column(name = "pbu_id")
    private long pbuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "usu_nome")
    private String usuNome;
    @Size(max = 100)
    @Column(name = "to")
    private String to;
    @Size(max = 60)
    @Column(name = "usu_coordenador")
    private String usuCoordenador;
    @Size(max = 100)
    @Column(name = "cc")
    private String cc;

    public VwPbemailtocc() {
    }

    public long getPbuId() {
        return pbuId;
    }

    public void setPbuId(long pbuId) {
        this.pbuId = pbuId;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUsuCoordenador() {
        return usuCoordenador;
    }

    public void setUsuCoordenador(String usuCoordenador) {
        this.usuCoordenador = usuCoordenador;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
    
}
