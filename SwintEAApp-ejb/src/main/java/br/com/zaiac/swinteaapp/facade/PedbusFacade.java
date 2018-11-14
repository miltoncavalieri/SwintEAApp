package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PedbusFacade extends AbstractFacade<Pedbus>{
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
        Query query = getEm().createNamedQuery("Pedbus.findAllPbsId");
        return query.getResultList();
    }
    
    public Pedbus findByPbuId(Long pbuId) {
        Query query = getEm().createNamedQuery("Pedbus.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return (Pedbus) query.getSingleResult();
    }
    
    public Integer updatePbParaCampo(Long pbuId) {
        Query query = getEm().createNamedQuery("Pedbus.updatePbParaCampo");
        query.setParameter("pbuId", pbuId);
        return query.executeUpdate();
    }
    
    
    
}
