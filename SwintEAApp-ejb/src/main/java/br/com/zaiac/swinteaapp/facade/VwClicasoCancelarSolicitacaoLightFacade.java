package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwClicasoCancelarSolicitacaoLight;
import javax.persistence.Query;

public class VwClicasoCancelarSolicitacaoLightFacade extends AbstractFacade<VwClicasoCancelarSolicitacaoLight>{
    public VwClicasoCancelarSolicitacaoLightFacade() {
        super(VwClicasoCancelarSolicitacaoLight.class);
    }
    
    
    public VwClicasoCancelarSolicitacaoLight findByPbuId (Long pbuId) {
        Query query = getEm().createNamedQuery("VwClicasoCancelarSolicitacaoLight.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return (VwClicasoCancelarSolicitacaoLight) query.getSingleResult();
    }
            
            
            
            
    
}
