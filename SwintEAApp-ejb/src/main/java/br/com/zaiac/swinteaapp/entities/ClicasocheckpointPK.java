/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author milton_cavalieri
 */
@Embeddable
public class ClicasocheckpointPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "a04_id_caso")
    private long a04IdCaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a05_seq")
    private int a05Seq;

    public ClicasocheckpointPK() {
    }

    public ClicasocheckpointPK(long a04IdCaso, int a05Seq) {
        this.a04IdCaso = a04IdCaso;
        this.a05Seq = a05Seq;
    }

    public long getA04IdCaso() {
        return a04IdCaso;
    }

    public void setA04IdCaso(long a04IdCaso) {
        this.a04IdCaso = a04IdCaso;
    }

    public int getA05Seq() {
        return a05Seq;
    }

    public void setA05Seq(int a05Seq) {
        this.a05Seq = a05Seq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) a04IdCaso;
        hash += (int) a05Seq;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClicasocheckpointPK)) {
            return false;
        }
        ClicasocheckpointPK other = (ClicasocheckpointPK) object;
        if (this.a04IdCaso != other.a04IdCaso) {
            return false;
        }
        if (this.a05Seq != other.a05Seq) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.ClicasocheckpointPK[ a04IdCaso=" + a04IdCaso + ", a05Seq=" + a05Seq + " ]";
    }
    
}
