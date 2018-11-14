/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author root
 */
@Embeddable
public class ClientecobrancaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cli_id")
    private long cliId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clb_dt")
    @Temporal(TemporalType.DATE)
    private Date clbDt;

    public ClientecobrancaPK() {
    }

    public ClientecobrancaPK(long cliId, Date clbDt) {
        this.cliId = cliId;
        this.clbDt = clbDt;
    }

    public long getCliId() {
        return cliId;
    }

    public void setCliId(long cliId) {
        this.cliId = cliId;
    }

    public Date getClbDt() {
        return clbDt;
    }

    public void setClbDt(Date clbDt) {
        this.clbDt = clbDt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cliId;
        hash += (clbDt != null ? clbDt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClientecobrancaPK)) {
            return false;
        }
        ClientecobrancaPK other = (ClientecobrancaPK) object;
        if (this.cliId != other.cliId) {
            return false;
        }
        if ((this.clbDt == null && other.clbDt != null) || (this.clbDt != null && !this.clbDt.equals(other.clbDt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.ClientecobrancaPK[ cliId=" + cliId + ", clbDt=" + clbDt + " ]";
    }
    
}
