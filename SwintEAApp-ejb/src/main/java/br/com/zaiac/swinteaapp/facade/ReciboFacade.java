package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Lotepgto;
import br.com.zaiac.swinteaapp.entities.Lotercbo;
import br.com.zaiac.swinteaapp.entities.Recibo;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ReciboFacade extends AbstractFacade<Recibo>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ReciboFacade() {
        super(Recibo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Integer updateLopIdToNull(Lotepgto lopId) {
        Query query = getEm().createNamedQuery("Recibo.updateLopIdToNull");
        query.setParameter("lopId", lopId);
        return query.executeUpdate();
    }
    
    public Integer updateLcbIdToNull(Lotercbo lcbId) {
        Query query = getEm().createNamedQuery("Recibo.updateLcbIdToNull");
        query.setParameter("lcbId", lcbId);
        return query.executeUpdate();
    }
    
    
}
