package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Pedbusit;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//@Stateless

public class PedbusitFacade extends AbstractFacade<Pedbusit> {
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
    
    public PedbusitFacade() {
        super(Pedbusit.class);
    }
    
    public Pedbusit findByPbsId(Short pbsId) {
        Query query = em.createNamedQuery("Pedbusit.findByPbsId");
        query.setParameter("pbsId", pbsId);
        return (Pedbusit) query.getSingleResult();
    }
    
    
}
