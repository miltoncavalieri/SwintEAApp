package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwPedbusEnvio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//@Stateless


public class VwPedbusEnvioFacade extends AbstractFacade<VwPedbusEnvio>{
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
    
    public VwPedbusEnvioFacade() {
        super(VwPedbusEnvio.class);
    }
    
    
    public List<VwPedbusEnvio> findAll() {
        Query query = em.createNamedQuery("VwPedbusEnvio.findAll");
        return query.getResultList();
    }
    
}
