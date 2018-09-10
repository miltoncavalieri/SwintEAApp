package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Analisedoc;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//@Stateless

public class AnalisedocFacade extends AbstractFacade<Analisedoc> {
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
    
    public AnalisedocFacade() {
        super(Analisedoc.class);
    }
    
    public List<Analisedoc> findByPbuId(Analise pbuId) {
        Query query = em.createNamedQuery("Analisedoc.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return query.getResultList();
    }
}
