package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwClicasoAnteriorFaturar;
import javax.persistence.Query;

public class VwClicasoAnteriorFaturarFacade extends AbstractFacade<VwClicasoAnteriorFaturar>{
    public VwClicasoAnteriorFaturarFacade() {
        super(VwClicasoAnteriorFaturar.class);
    }
    
    public VwClicasoAnteriorFaturar findByA04IdCasoCliId(Long a04IdCaso, Long cliId) {
        Query query = getEm().createNamedQuery("VwClicasoAnteriorFaturar.findByA04IdCasoCliId");
        query.setParameter("a04IdCaso", a04IdCaso);
        query.setParameter("cliId", cliId);
        return (VwClicasoAnteriorFaturar) query.getSingleResult();
    }
    
}
