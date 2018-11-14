package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Pbsubstatus;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PbsubstatusFacade extends AbstractFacade<Pbsubstatus>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public PbsubstatusFacade() {
        super(Pbsubstatus.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Pbsubstatus findPusId(Short pusId) {
        Query query = getEm().createNamedQuery("Pbsubstatus.findPusId");
        query.setParameter("pusId", pusId);
        return (Pbsubstatus) query.getSingleResult();        
    }
    
}
