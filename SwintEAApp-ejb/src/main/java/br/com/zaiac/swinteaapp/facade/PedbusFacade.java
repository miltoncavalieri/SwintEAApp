package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//@Stateless

public class PedbusFacade extends AbstractFacade<Pedbus>{
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
    
    
    public PedbusFacade() {
        super(Pedbus.class);
    }
    
    public List<Pedbus> findAllPbsId() {
        Query query = em.createNamedQuery("Pedbus.findAllPbsId");
        return query.getResultList();
    }
    
    public Pedbus findByPbuId(Long pbuId) {
        Query query = em.createNamedQuery("Pedbus.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return (Pedbus) query.getSingleResult();
    }
    
}
