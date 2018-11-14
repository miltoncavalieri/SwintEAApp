package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Pedbusfase;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PedbusfaseFacade extends AbstractFacade<Pedbusfase>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public PedbusfaseFacade() {
        super(Pedbusfase.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Pedbusfase findByFasId (short fasId) {
        Query query = getEm().createNamedQuery("Pedbusfase.findByFasId");
        query.setParameter("fasId", fasId);
        return  (Pedbusfase) query.getSingleResult();
        
        
    }
    
}
