package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "clicasovalidacaoanexo")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "Clicasovalidacaoanexo.findAll", query = "SELECT c FROM Clicasovalidacaoanexo c"),
    @NamedQuery(name = "Clicasovalidacaoanexo.deleteByA04IdCaso", query = "DELETE FROM Clicasovalidacaoanexo c WHERE c.clicasovalidacaoanexoPK.a04IdCaso = :a04IdCaso")
})
public class Clicasovalidacaoanexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClicasovalidacaoanexoPK clicasovalidacaoanexoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "a10_arq_nome")
    private String a10ArqNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "a10_arq_ext")
    private String a10ArqExt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "a10_arq_dir")
    private String a10ArqDir;
    @JoinColumns({
        @JoinColumn(name = "a04_id_caso", referencedColumnName = "a04_id_caso", insertable = false, updatable = false),
        @JoinColumn(name = "a08_seq", referencedColumnName = "a08_seq", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Clicasovalidacao clicasovalidacao;

    public Clicasovalidacaoanexo() {
    }

    public Clicasovalidacaoanexo(ClicasovalidacaoanexoPK clicasovalidacaoanexoPK) {
        this.clicasovalidacaoanexoPK = clicasovalidacaoanexoPK;
    }

    public Clicasovalidacaoanexo(ClicasovalidacaoanexoPK clicasovalidacaoanexoPK, String a10ArqNome, String a10ArqExt, String a10ArqDir) {
        this.clicasovalidacaoanexoPK = clicasovalidacaoanexoPK;
        this.a10ArqNome = a10ArqNome;
        this.a10ArqExt = a10ArqExt;
        this.a10ArqDir = a10ArqDir;
    }

    public Clicasovalidacaoanexo(long a04IdCaso, int a08Seq, short a10Seq) {
        this.clicasovalidacaoanexoPK = new ClicasovalidacaoanexoPK(a04IdCaso, a08Seq, a10Seq);
    }

    public ClicasovalidacaoanexoPK getClicasovalidacaoanexoPK() {
        return clicasovalidacaoanexoPK;
    }

    public void setClicasovalidacaoanexoPK(ClicasovalidacaoanexoPK clicasovalidacaoanexoPK) {
        this.clicasovalidacaoanexoPK = clicasovalidacaoanexoPK;
    }

    public String getA10ArqNome() {
        return a10ArqNome;
    }

    public void setA10ArqNome(String a10ArqNome) {
        this.a10ArqNome = a10ArqNome;
    }

    public String getA10ArqExt() {
        return a10ArqExt;
    }

    public void setA10ArqExt(String a10ArqExt) {
        this.a10ArqExt = a10ArqExt;
    }

    public String getA10ArqDir() {
        return a10ArqDir;
    }

    public void setA10ArqDir(String a10ArqDir) {
        this.a10ArqDir = a10ArqDir;
    }

    public Clicasovalidacao getClicasovalidacao() {
        return clicasovalidacao;
    }

    public void setClicasovalidacao(Clicasovalidacao clicasovalidacao) {
        this.clicasovalidacao = clicasovalidacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clicasovalidacaoanexoPK != null ? clicasovalidacaoanexoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clicasovalidacaoanexo)) {
            return false;
        }
        Clicasovalidacaoanexo other = (Clicasovalidacaoanexo) object;
        if ((this.clicasovalidacaoanexoPK == null && other.clicasovalidacaoanexoPK != null) || (this.clicasovalidacaoanexoPK != null && !this.clicasovalidacaoanexoPK.equals(other.clicasovalidacaoanexoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Clicasovalidacaoanexo[ clicasovalidacaoanexoPK=" + clicasovalidacaoanexoPK + " ]";
    }
    
}
