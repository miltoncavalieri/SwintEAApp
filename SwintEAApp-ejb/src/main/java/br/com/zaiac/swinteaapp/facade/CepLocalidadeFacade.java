package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.CepLocalidade;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CepLocalidadeFacade extends AbstractFacade<CepLocalidade>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public CepLocalidadeFacade() {
        super(CepLocalidade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CepLocalidade findByLocNu (Long locNu) {
        Query query = getEm().createNamedQuery("CepLocalidade.findByLocNu");
        query.setParameter("locNu", locNu);
        return (CepLocalidade) query.getSingleResult();
    }
    
}
