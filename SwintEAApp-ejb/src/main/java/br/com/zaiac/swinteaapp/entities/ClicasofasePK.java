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
public class ClicasofasePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "a01_id")
    private short a01Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a02_id")
    private short a02Id;

    public ClicasofasePK() {
    }

    public ClicasofasePK(short a01Id, short a02Id) {
        this.a01Id = a01Id;
        this.a02Id = a02Id;
    }

    public short getA01Id() {
        return a01Id;
    }

    public void setA01Id(short a01Id) {
        this.a01Id = a01Id;
    }

    public short getA02Id() {
        return a02Id;
    }

    public void setA02Id(short a02Id) {
        this.a02Id = a02Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) a01Id;
        hash += (int) a02Id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClicasofasePK)) {
            return false;
        }
        ClicasofasePK other = (ClicasofasePK) object;
        if (this.a01Id != other.a01Id) {
            return false;
        }
        if (this.a02Id != other.a02Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.ClicasofasePK[ a01Id=" + a01Id + ", a02Id=" + a02Id + " ]";
    }
    
}
