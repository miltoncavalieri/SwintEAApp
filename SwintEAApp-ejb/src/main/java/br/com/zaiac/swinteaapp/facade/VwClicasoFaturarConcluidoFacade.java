package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class VwClicasoFaturarConcluidoFacade extends AbstractFacade<VwClicasoFaturarConcluido>{
    public VwClicasoFaturarConcluidoFacade() {
        super(VwClicasoFaturarConcluido.class);
    }
    
    
    public VwClicasoFaturarConcluido findByA04IdCasoCliId(Long a04IdCaso, Long cliId) {
        Query query = getEm().createNamedQuery("VwClicasoFaturarConcluido.findByA04IdCasoCliId");
        query.setParameter("a04IdCaso", a04IdCaso);
        query.setParameter("cliId", cliId);
        return (VwClicasoFaturarConcluido) query.getSingleResult();
        
        
    }
    
}
