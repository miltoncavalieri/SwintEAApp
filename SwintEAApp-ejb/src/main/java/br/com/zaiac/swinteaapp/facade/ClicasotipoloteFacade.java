package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicasotipolote;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class ClicasotipoloteFacade extends AbstractFacade<Clicasotipolote>{
    public ClicasotipoloteFacade() {
        super(Clicasotipolote.class);
    }
    
    public Clicasotipolote findByA01Id(Short a01Id) {
        Query query = getEm().createNamedQuery("Clicasotipolote.findByA01Id");
        query.setParameter("a01Id", a01Id);
        return (Clicasotipolote) query.getSingleResult();
    }
    
}
