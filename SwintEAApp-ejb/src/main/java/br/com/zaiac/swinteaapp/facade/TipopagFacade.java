package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Tipopag;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TipopagFacade extends AbstractFacade<Tipopag>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public TipopagFacade() {
        super(Tipopag.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Tipopag findByTppId (short tppId) {
        Query query = getEm().createNamedQuery("Tipopag.findByTppId");
        query.setParameter("tppId", tppId);
        return (Tipopag) query.getSingleResult();
    }
    
    
}
