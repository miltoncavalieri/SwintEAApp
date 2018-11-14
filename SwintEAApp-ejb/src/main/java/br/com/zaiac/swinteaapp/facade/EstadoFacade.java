package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Estado;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EstadoFacade extends AbstractFacade<Estado>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Estado findByEstId (short estId) {
        Query query = getEm().createNamedQuery("Estado.findByEstId");
        query.setParameter("estId", estId);
        return (Estado) query.getSingleResult();
    }
    
}
