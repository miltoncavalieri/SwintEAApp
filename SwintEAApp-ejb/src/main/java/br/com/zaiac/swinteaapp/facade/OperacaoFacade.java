package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Operacao;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class OperacaoFacade extends AbstractFacade<Operacao> {
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public OperacaoFacade() {
        super(Operacao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Operacao findByOpeId(Integer opeId) {
        Query query = getEm().createNamedQuery("Operacao.findByOpeId");
        query.setParameter("opeId", opeId);
        return (Operacao) query.getSingleResult();
    }
}
