package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Checkpoint;
import br.com.zaiac.swinteaapp.entities.Pedbusrel;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PedbusrelFacade extends AbstractFacade<Pedbusrel>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public PedbusrelFacade() {
        super(Pedbusrel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Integer deleteByPckId(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Pedbusrel.deleteByPckId");
        query.setParameter("pckId", pckId.getPckId());
        return query.executeUpdate();
    }
    
}
