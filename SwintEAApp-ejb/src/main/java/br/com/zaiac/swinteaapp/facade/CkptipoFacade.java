package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Ckptipo;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CkptipoFacade extends AbstractFacade<Ckptipo>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    public CkptipoFacade() {
        super(Ckptipo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Ckptipo findByCktId(Short cktId) {
        Query query = getEm().createNamedQuery("Ckptipo.findByCktId");
        query.setParameter("cktId", cktId);
        return (Ckptipo) query.getSingleResult();
    }
    
    
}
