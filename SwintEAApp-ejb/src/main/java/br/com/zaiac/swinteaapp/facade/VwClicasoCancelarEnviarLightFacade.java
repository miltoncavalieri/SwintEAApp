package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwClicasoCancelarEnviarLight;
import java.util.List;
import javax.persistence.Query;

public class VwClicasoCancelarEnviarLightFacade extends AbstractFacade<VwClicasoCancelarEnviarLight>{
    public VwClicasoCancelarEnviarLightFacade() {
        super(VwClicasoCancelarEnviarLight.class);
    }
    
    
    @Override
    public List<VwClicasoCancelarEnviarLight> findAll() {
        Query query = getEm().createNamedQuery("VwClicasoCancelarEnviarLight.findAll");
        return query.getResultList();
    }
    
    
    
//VwClicasoCancelarEnviarLight.findAll    
    
}
