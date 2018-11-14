package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Agentesit;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AgentesitFacade extends AbstractFacade<Agentesit>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    public AgentesitFacade() {
        super(Agentesit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Agentesit findByAgsId (Short agsId) {
        Query query = getEm().createNamedQuery("Agentesit.findByAgsId");
        query.setParameter("agsId", agsId);
        return (Agentesit) query.getSingleResult();
    }
    
}
