package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Submotivopadrao;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class SubmotivopadraoFacade extends AbstractFacade<Submotivopadrao>{
    
    public SubmotivopadraoFacade() {
        super(Submotivopadrao.class);
    }
    
    public Submotivopadrao findBySmpId (Integer smpId) {
        Query query = getEm().createNamedQuery("Submotivopadrao.findBySmpId");
        query.setParameter("smpId", smpId);
        return (Submotivopadrao) query.getSingleResult();
    }
    
    
    
    
    
    
}
