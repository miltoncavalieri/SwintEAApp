package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwPedbusPagoRecebido;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class VwPedbusPagoRecebidoFacade extends AbstractFacade<VwPedbusPagoRecebido> {
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public VwPedbusPagoRecebidoFacade() {
        super(VwPedbusPagoRecebido.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public VwPedbusPagoRecebido findByPbuId (Long pbuId) {
        Query query = getEm().createNamedQuery("VwPedbusPagoRecebido.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return (VwPedbusPagoRecebido) query.getSingleResult();
    }
    
    public VwPedbusPagoRecebido findByPckId (Long pckId) {
        Query query = getEm().createNamedQuery("VwPedbusPagoRecebido.findByPckId");
        query.setParameter("pckId", pckId);
        return (VwPedbusPagoRecebido) query.getSingleResult();
    }
    
    
}
