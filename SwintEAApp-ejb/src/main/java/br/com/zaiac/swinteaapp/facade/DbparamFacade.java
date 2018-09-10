package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Dbparam;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//@Stateless

public class DbparamFacade extends AbstractFacade<Dbparam> {
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
    
    public DbparamFacade() {
        super(Dbparam.class);
    }
    
    public List<Dbparam> findAll() {
        Query query = em.createNamedQuery("Dbparam.findAll");
        return query.getResultList();
    }
    
    
}
