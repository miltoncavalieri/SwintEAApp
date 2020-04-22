package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Ambiente;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class AmbienteFacade extends AbstractFacade<Ambiente>{
    public AmbienteFacade() {
        super(Ambiente.class);
    }
    
    public Ambiente findByAmbId(String ambId) {
        Query query = getEm().createNamedQuery("Ambiente.findByAmbId");
        query.setParameter("ambId", ambId);
        return (Ambiente) query.getSingleResult();
    }
}
