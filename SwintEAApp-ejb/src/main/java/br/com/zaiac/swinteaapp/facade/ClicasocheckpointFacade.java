package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicasocheckpoint;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class ClicasocheckpointFacade extends AbstractFacade<Clicasocheckpoint>{
    public ClicasocheckpointFacade() {
        super(Clicasocheckpoint.class);
    }
    
    public int findMaxA05Seq(Long a04IdCaso) {
        Query query = getEm().createNamedQuery("Clicasocheckpoint.findMaxA05Seq");
        query.setParameter("a04IdCaso", a04IdCaso);
        Integer i = (Integer) query.getSingleResult();
        if (i == null) {
            i = (int) 0;
        }
        i += 1;
        return i;
    }
    
    public Clicasocheckpoint findA04IdCasoA05Seq (Long a04IdCaso, Integer a05Seq) {
        Query query = getEm().createNamedQuery("Clicasocheckpoint.findA04IdCasoA05Seq");
        query.setParameter("a04IdCaso", a04IdCaso);
        query.setParameter("a05Seq", a05Seq);
        return (Clicasocheckpoint) query.getSingleResult();
    }
    
    public int deleteByA04IdCaso(Long a04IdCaso) {
        Query query = getEm().createNamedQuery("Clicasocheckpoint.deleteByA04IdCaso");
        query.setParameter("a04IdCaso", a04IdCaso);
        return query.executeUpdate();
    }
}
