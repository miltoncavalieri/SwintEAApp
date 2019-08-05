package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Locrecuploc;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LocrecuplocFacade  extends AbstractFacade <Locrecuploc>{
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
    
    
    public LocrecuplocFacade() {
        super(Locrecuploc.class);
    }
    
    public Locrecuploc findByLrpIdLrlId (Integer lrpId, Integer lrlId) {
        Query query = getEm().createNamedQuery("Locrecuploc.findByLrpIdLrlId");
        query.setParameter("lrpId", lrpId);
        query.setParameter("lrlId", lrlId);
        return (Locrecuploc) query.getSingleResult();
    }
    
    
}
