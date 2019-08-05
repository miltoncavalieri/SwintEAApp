package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Locrecup;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LocrecupFacade extends AbstractFacade <Locrecup> {
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
    
    
    public LocrecupFacade() {
        super(Locrecup.class);
    }
    
    
    public Locrecup findLrpId (Integer lrpId) {
        Query query = getEm().createNamedQuery("Locrecup.findLrpId");
        query.setParameter("lrpId", lrpId);
        return (Locrecup) query.getSingleResult();
    }
    
}
