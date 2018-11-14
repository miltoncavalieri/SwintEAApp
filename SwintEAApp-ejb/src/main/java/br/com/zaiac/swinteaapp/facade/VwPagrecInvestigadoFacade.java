package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwPagrecInvestigado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class VwPagrecInvestigadoFacade extends AbstractFacade<VwPagrecInvestigado>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public VwPagrecInvestigadoFacade() {
        super(VwPagrecInvestigado.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<VwPagrecInvestigado> findByPbuId (Long pbuId) {
        Query query = getEm().createNamedQuery("VwPagrecInvestigado.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return query.getResultList();
    }
}
