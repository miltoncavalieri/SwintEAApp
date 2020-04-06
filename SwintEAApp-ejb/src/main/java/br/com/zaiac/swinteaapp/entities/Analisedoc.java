package br.com.zaiac.swinteaapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "analisedoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analisedoc.findAll", query = "SELECT a FROM Analisedoc a"),
    @NamedQuery(name = "Analisedoc.findByPbuId", query = "SELECT a FROM Analisedoc a WHERE a.pbuId = :pbuId")})
public class Analisedoc implements Serializable {

    @Size(max = 255)
    @Column(name = "and_diretorio")
    private String andDiretorio;
    @Column(name = "and_face_disponivel")
    private Short andFaceDisponivel;
    @Size(max = 25)
    @Column(name = "and_face_probabilidade")
    private String andFaceProbabilidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "and_face_similaridade")
    private Double andFaceSimilaridade;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "and_id")
    private Long andId;
    @Size(max = 30)
    @Column(name = "and_numero")
    private String andNumero;
    @Size(max = 20)
    @Column(name = "and_documento")
    private String andDocumento;
    @Size(max = 255)
    @Column(name = "and_conteudo")
    private String andConteudo;
    @Size(max = 255)
    @Column(name = "and_orignomearq")
    private String andOrignomearq;
    @JoinColumn(name = "doc_id", referencedColumnName = "doc_id")
    @ManyToOne(optional = false)
    private Tipodoc docId;
    @JoinColumn(name = "pbu_id", referencedColumnName = "pbu_id")
    @ManyToOne(optional = false)
    private Analise pbuId;

    public Analisedoc() {
    }

    public Analisedoc(Long andId) {
        this.andId = andId;
    }

    public Long getAndId() {
        return andId;
    }

    public void setAndId(Long andId) {
        this.andId = andId;
    }

    public String getAndNumero() {
        return andNumero;
    }

    public void setAndNumero(String andNumero) {
        this.andNumero = andNumero;
    }

    public String getAndDocumento() {
        return andDocumento;
    }

    public void setAndDocumento(String andDocumento) {
        this.andDocumento = andDocumento;
    }

    public String getAndConteudo() {
        return andConteudo;
    }

    public void setAndConteudo(String andConteudo) {
        this.andConteudo = andConteudo;
    }

    public String getAndOrignomearq() {
        return andOrignomearq;
    }

    public void setAndOrignomearq(String andOrignomearq) {
        this.andOrignomearq = andOrignomearq;
    }

    public Tipodoc getDocId() {
        return docId;
    }

    public void setDocId(Tipodoc docId) {
        this.docId = docId;
    }

    public Analise getPbuId() {
        return pbuId;
    }

    public void setPbuId(Analise pbuId) {
        this.pbuId = pbuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (andId != null ? andId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Analisedoc)) {
            return false;
        }
        Analisedoc other = (Analisedoc) object;
        if ((this.andId == null && other.andId != null) || (this.andId != null && !this.andId.equals(other.andId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.zaiac.swinteaapp.entities.Analisedoc[ andId=" + andId + " ]";
    }

    public String getAndDiretorio() {
        return andDiretorio;
    }

    public void setAndDiretorio(String andDiretorio) {
        this.andDiretorio = andDiretorio;
    }

    public Short getAndFaceDisponivel() {
        return andFaceDisponivel;
    }

    public void setAndFaceDisponivel(Short andFaceDisponivel) {
        this.andFaceDisponivel = andFaceDisponivel;
    }

    public String getAndFaceProbabilidade() {
        return andFaceProbabilidade;
    }

    public void setAndFaceProbabilidade(String andFaceProbabilidade) {
        this.andFaceProbabilidade = andFaceProbabilidade;
    }

    public Double getAndFaceSimilaridade() {
        return andFaceSimilaridade;
    }

    public void setAndFaceSimilaridade(Double andFaceSimilaridade) {
        this.andFaceSimilaridade = andFaceSimilaridade;
    }
    
}
