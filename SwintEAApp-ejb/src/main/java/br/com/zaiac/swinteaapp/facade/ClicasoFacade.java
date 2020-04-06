package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicaso;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class ClicasoFacade extends AbstractFacade<Clicaso>{
    public ClicasoFacade() {
        super(Clicaso.class);
    }
    
    
    public Clicaso findByA04IdCaso (Long a04IdCaso) {
        Query query = getEm().createNamedQuery("Clicaso.findByA04IdCaso");
        query.setParameter("a04IdCaso", a04IdCaso);
        return (Clicaso) query.getSingleResult();
    }
    
    public int deleteByA04IdCaso(Long a04IdCaso) {
        Query query = getEm().createNamedQuery("Clicaso.deleteByA04IdCaso");
        query.setParameter("a04IdCaso", a04IdCaso);
        return query.executeUpdate();
    }
    


}
