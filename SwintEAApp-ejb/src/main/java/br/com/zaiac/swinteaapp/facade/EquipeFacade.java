package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Equipe;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EquipeFacade extends AbstractFacade<Equipe>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EquipeFacade() {
        super(Equipe.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Equipe findByEuiId(Integer euiId) {
        Query query = getEm().createNamedQuery("Equipe.findByEuiId");
        query.setParameter("euiId", euiId);
        return (Equipe) query.getSingleResult();
    }
    
}
