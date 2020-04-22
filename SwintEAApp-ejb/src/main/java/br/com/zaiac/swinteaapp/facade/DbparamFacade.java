package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Dbparam;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.Query;

public class DbparamFacade extends AbstractFacade<Dbparam> {
//    @PersistenceContext(name="SwintService", type=PersistenceContextType.TRANSACTION)
//    private EntityManager em;
//    public EntityManager getEm() {
//        return em;
//    }

//    public void setEm(EntityManager em) {
//        this.em = em;
//    }

//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
    
    public DbparamFacade() {
        super(Dbparam.class);
    }
    
    @Override
    public List<Dbparam> findAll() {
        Query query = getEm().createNamedQuery("Dbparam.findAll");
        return query.getResultList();
    }
    
    
    public Dbparam findByParId() {
        Query query = getEm().createNamedQuery("Dbparam.findByParId");
        return (Dbparam) query.getSingleResult();
        
    }
    
    
}
