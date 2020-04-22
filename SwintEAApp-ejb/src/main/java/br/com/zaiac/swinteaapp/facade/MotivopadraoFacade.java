package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Motivopadrao;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.Query;

public class MotivopadraoFacade extends AbstractFacade<Motivopadrao>{
    
    public MotivopadraoFacade() {
        super(Motivopadrao.class);
    }
    
    
    public Motivopadrao findByMopId(Integer mopId) {
        Query query = getEm().createNamedQuery("Motivopadrao.findByMopId");
        query.setParameter("mopId", mopId);
        return (Motivopadrao) query.getSingleResult();
    }
    
}
