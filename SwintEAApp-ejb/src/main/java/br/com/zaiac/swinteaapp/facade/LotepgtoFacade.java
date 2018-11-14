package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Lotepgto;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LotepgtoFacade extends AbstractFacade<Lotepgto> {
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public LotepgtoFacade() {
        super(Lotepgto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Lotepgto findByLopId(Long lopId) {
        Query query = getEm().createNamedQuery("Lotepgto.findByLopId");
        query.setParameter("lopId", lopId);
        return (Lotepgto) query.getSingleResult();
    }
    
    public int deleteByLopId(Long lopId) {
        Query query = getEm().createNamedQuery("Lotepgto.deleteByLopId");
        query.setParameter("lopId", lopId);
        return query.executeUpdate();
    }
    
}
