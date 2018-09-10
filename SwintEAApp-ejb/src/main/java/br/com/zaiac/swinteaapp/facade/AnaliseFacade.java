package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//@Stateless

public class AnaliseFacade extends AbstractFacade<Analise>{
//    @PersistenceContext(name="SwintService", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AnaliseFacade() {
        super(Analise.class);
    }
    
    public Analise findByPbuId(Long pbuId) {
        Query query = em.createNamedQuery("Analise.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return (Analise) query.getSingleResult();
        
    }
    
}
