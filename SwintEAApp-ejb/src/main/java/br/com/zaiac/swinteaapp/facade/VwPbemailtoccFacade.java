package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwPbemailtocc;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//@Stateless

public class VwPbemailtoccFacade extends AbstractFacade<VwPbemailtocc>{
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

    public VwPbemailtoccFacade() {
        super(VwPbemailtocc.class);
    }    
    
    public VwPbemailtocc findByPbuId(Long pbuId) {
        Query query = em.createNamedQuery("VwPbemailtocc.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return (VwPbemailtocc) query.getSingleResult();
    }
    
}
