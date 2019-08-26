package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Sysparam;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class SysparamFacade  extends AbstractFacade<Sysparam>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public SysparamFacade() {
        super(Sysparam.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public String findByPrmNome (String prmNome) {
        Query query = getEm().createNamedQuery("Sysparam.findByPrmNome");
        query.setParameter("prmNome", prmNome);
        
        Sysparam sysparam;
        try {
            sysparam = (Sysparam) query.getSingleResult();
            return sysparam.getPrmValue();
        } catch (NoResultException e) {
            return "";
        }
    }
    
    
}
