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
public class LocrecuplocPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "lrp_id")
    private int lrpId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lrl_id")
    private int lrlId;

    public LocrecuplocPK() {
    }

    public LocrecuplocPK(int lrpId, int lrlId) {
        this.lrpId = lrpId;
        this.lrlId = lrlId;
    }

    public int getLrpId() {
        return lrpId;
    }

    public void setLrpId(int lrpId) {
        this.lrpId = lrpId;
    }

    public int getLrlId() {
        return lrlId;
    }

    public void setLrlId(int lrlId) {
        this.lrlId = lrlId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) lrpId;
        hash += (int) lrlId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocrecuplocPK)) {
            return false;
        }
        LocrecuplocPK other = (LocrecuplocPK) object;
        if (this.lrpId != other.lrpId) {
            return false;
        }
        if (this.lrlId != other.lrlId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.LocrecuplocPK[ lrpId=" + lrpId + ", lrlId=" + lrlId + " ]";
    }
    
}
