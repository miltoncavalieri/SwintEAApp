package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicasofase;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class ClicasofaseFacade extends AbstractFacade<Clicasofase>{
    public ClicasofaseFacade() {
        super(Clicasofase.class);
    }
    
    
    public Clicasofase findByA01IdA02Id(Short a01Id, Short a02Id) {
        Query query = getEm().createNamedQuery("Clicasofase.findByA01IdA02Id");
        query.setParameter("a01Id", a01Id);
        query.setParameter("a02Id", a02Id);
        return (Clicasofase) query.getSingleResult();
        
    }
    
}
