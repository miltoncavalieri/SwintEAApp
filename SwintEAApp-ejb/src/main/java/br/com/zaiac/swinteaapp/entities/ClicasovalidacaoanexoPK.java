package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ClicasovalidacaoanexoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "a04_id_caso")
    private long a04IdCaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a08_seq")
    private int a08Seq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a10_seq")
    private short a10Seq;

    public ClicasovalidacaoanexoPK() {
    }

    public ClicasovalidacaoanexoPK(long a04IdCaso, int a08Seq, short a10Seq) {
        this.a04IdCaso = a04IdCaso;
        this.a08Seq = a08Seq;
        this.a10Seq = a10Seq;
    }

    public long getA04IdCaso() {
        return a04IdCaso;
    }

    public void setA04IdCaso(long a04IdCaso) {
        this.a04IdCaso = a04IdCaso;
    }

    public int getA08Seq() {
        return a08Seq;
    }

    public void setA08Seq(int a08Seq) {
        this.a08Seq = a08Seq;
    }

    public short getA10Seq() {
        return a10Seq;
    }

    public void setA10Seq(short a10Seq) {
        this.a10Seq = a10Seq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) a04IdCaso;
        hash += (int) a08Seq;
        hash += (int) a10Seq;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClicasovalidacaoanexoPK)) {
            return false;
        }
        ClicasovalidacaoanexoPK other = (ClicasovalidacaoanexoPK) object;
        if (this.a04IdCaso != other.a04IdCaso) {
            return false;
        }
        if (this.a08Seq != other.a08Seq) {
            return false;
        }
        if (this.a10Seq != other.a10Seq) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.ClicasovalidacaoanexoPK[ a04IdCaso=" + a04IdCaso + ", a08Seq=" + a08Seq + ", a10Seq=" + a10Seq + " ]";
    }
    
}
