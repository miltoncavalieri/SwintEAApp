package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ClicasovalidacaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "a04_id_caso")
    private long a04IdCaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a08_seq")
    private int a08Seq;

    public ClicasovalidacaoPK() {
    }

    public ClicasovalidacaoPK(long a04IdCaso, int a08Seq) {
        this.a04IdCaso = a04IdCaso;
        this.a08Seq = a08Seq;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) a04IdCaso;
        hash += (int) a08Seq;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClicasovalidacaoPK)) {
            return false;
        }
        ClicasovalidacaoPK other = (ClicasovalidacaoPK) object;
        if (this.a04IdCaso != other.a04IdCaso) {
            return false;
        }
        if (this.a08Seq != other.a08Seq) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.ClicasovalidacaoPK[ a04IdCaso=" + a04IdCaso + ", a08Seq=" + a08Seq + " ]";
    }
    
}
