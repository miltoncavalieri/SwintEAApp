package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicasovalidacaoanexo;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class ClicasovalidacaoanexoFacade extends AbstractFacade<Clicasovalidacaoanexo>{
    public ClicasovalidacaoanexoFacade() {
        super(Clicasovalidacaoanexo.class);
    }
    
    
    public int deleteByA04IdCaso(Long a04IdCaso) {
        Query query = getEm().createNamedQuery("Clicasovalidacaoanexo.deleteByA04IdCaso");
        query.setParameter("a04IdCaso", a04IdCaso);
        return query.executeUpdate();
    }
    
    
}
