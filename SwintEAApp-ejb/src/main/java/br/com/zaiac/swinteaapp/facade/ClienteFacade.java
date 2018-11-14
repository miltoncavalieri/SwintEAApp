package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Cliente;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClienteFacade extends AbstractFacade<Cliente>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Cliente findByCliId(Long cliId) {
        Query query = getEm().createNamedQuery("Cliente.findByCliId");
        query.setParameter("cliId", cliId);
        return (Cliente) query.getSingleResult();
    }
    
}
