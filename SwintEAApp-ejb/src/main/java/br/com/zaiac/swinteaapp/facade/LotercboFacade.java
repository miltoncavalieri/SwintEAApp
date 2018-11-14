package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Lotercbo;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LotercboFacade  extends AbstractFacade<Lotercbo> {
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public LotercboFacade() {
        super(Lotercbo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Lotercbo findByLcbId(Long lcbId) {
        Query query = getEm().createNamedQuery("Lotercbo.findByLcbId");
        query.setParameter("lcbId", lcbId);
        return (Lotercbo) query.getSingleResult();
    }
    
    public int deleteByLcbId(Long lcbId) {
        Query query = getEm().createNamedQuery("Lotercbo.deleteByLcbId");
        query.setParameter("lcbId", lcbId);
        return query.executeUpdate();
    }
    
}
