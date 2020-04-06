package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicasovalidacao;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class ClicasovalidacaoFacade extends AbstractFacade<Clicasovalidacao>{
    public ClicasovalidacaoFacade() {
        super(Clicasovalidacao.class);
    }
    
    
    public int deleteByA04IdCaso(Long a04IdCaso) {
        Query query = getEm().createNamedQuery("Clicasovalidacao.deleteByA04IdCaso");
        query.setParameter("a04IdCaso", a04IdCaso);
        return query.executeUpdate();
    }
    
}
