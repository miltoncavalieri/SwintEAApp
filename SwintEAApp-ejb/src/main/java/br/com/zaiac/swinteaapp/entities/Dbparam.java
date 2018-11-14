/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.zaiac.swinteaapp.entities;

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

/**
 *
 * @author root
 */
@Entity
@Table(name = "dbparam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dbparam.findAll", query = "SELECT d FROM Dbparam d")})
public class Dbparam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "par_id")
    private Short parId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "par_db_versao")
    private String parDbVersao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "par_ambiente")
    private String parAmbiente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "par_smtp")
    private String parSmtp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "par_porta")
    private int parPorta;
    @Size(max = 60)
    @Column(name = "par_smtp_usuario")
    private String parSmtpUsuario;
    @Size(max = 60)
    @Column(name = "par_smtp_senha")
    private String parSmtpSenha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "par_ssl")
    private short parSsl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "par_auth")
    private short parAuth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "par_email_origem")
    private String parEmailOrigem;
    @Column(name = "par_dm")
    private Short parDm;
    @Size(max = 30)
    @Column(name = "par_dm_usuario")
    private String parDmUsuario;
    @Size(max = 30)
    @Column(name = "par_dm_senha")
    private String parDmSenha;
    @Size(max = 3)
    @Column(name = "amb_id")
    private String ambId;

    public Dbparam() {
    }

    public Dbparam(Short parId) {
        this.parId = parId;
    }

    public Dbparam(Short parId, String parDbVersao, String parAmbiente, String parSmtp, int parPorta, short parSsl, short parAuth, String parEmailOrigem) {
        this.parId = parId;
        this.parDbVersao = parDbVersao;
        this.parAmbiente = parAmbiente;
        this.parSmtp = parSmtp;
        this.parPorta = parPorta;
        this.parSsl = parSsl;
        this.parAuth = parAuth;
        this.parEmailOrigem = parEmailOrigem;
    }

    public Short getParId() {
        return parId;
    }

    public void setParId(Short parId) {
        this.parId = parId;
    }

    public String getParDbVersao() {
        return parDbVersao;
    }

    public void setParDbVersao(String parDbVersao) {
        this.parDbVersao = parDbVersao;
    }

    public String getParAmbiente() {
        return parAmbiente;
    }

    public void setParAmbiente(String parAmbiente) {
        this.parAmbiente = parAmbiente;
    }

    public String getParSmtp() {
        return parSmtp;
    }

    public void setParSmtp(String parSmtp) {
        this.parSmtp = parSmtp;
    }

    public int getParPorta() {
        return parPorta;
    }

    public void setParPorta(int parPorta) {
        this.parPorta = parPorta;
    }

    public String getParSmtpUsuario() {
        return parSmtpUsuario;
    }

    public void setParSmtpUsuario(String parSmtpUsuario) {
        this.parSmtpUsuario = parSmtpUsuario;
    }

    public String getParSmtpSenha() {
        return parSmtpSenha;
    }

    public void setParSmtpSenha(String parSmtpSenha) {
        this.parSmtpSenha = parSmtpSenha;
    }

    public short getParSsl() {
        return parSsl;
    }

    public void setParSsl(short parSsl) {
        this.parSsl = parSsl;
    }

    public short getParAuth() {
        return parAuth;
    }

    public void setParAuth(short parAuth) {
        this.parAuth = parAuth;
    }

    public String getParEmailOrigem() {
        return parEmailOrigem;
    }

    public void setParEmailOrigem(String parEmailOrigem) {
        this.parEmailOrigem = parEmailOrigem;
    }

    public Short getParDm() {
        return parDm;
    }

    public void setParDm(Short parDm) {
        this.parDm = parDm;
    }

    public String getParDmUsuario() {
        return parDmUsuario;
    }

    public void setParDmUsuario(String parDmUsuario) {
        this.parDmUsuario = parDmUsuario;
    }

    public String getParDmSenha() {
        return parDmSenha;
    }

    public void setParDmSenha(String parDmSenha) {
        this.parDmSenha = parDmSenha;
    }

    public String getAmbId() {
        return ambId;
    }

    public void setAmbId(String ambId) {
        this.ambId = ambId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Dbparam)) {
            return false;
        }
        Dbparam other = (Dbparam) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Dbparam[ parId=" + parId + " ]";
    }
    
}
