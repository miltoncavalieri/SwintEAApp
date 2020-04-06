package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwClicasoAguardar;
import java.util.List;
import javax.persistence.Query;

public class VwClicasoAguardarFacade extends AbstractFacade<VwClicasoAguardar>{
    public VwClicasoAguardarFacade() {
        super(VwClicasoAguardar.class);
    }
    
    @Override
    public List<VwClicasoAguardar> findAll() {
        Query query = getEm().createNamedQuery("VwClicasoAguardar.findAll");
        return query.getResultList();
        
    }
    
}
