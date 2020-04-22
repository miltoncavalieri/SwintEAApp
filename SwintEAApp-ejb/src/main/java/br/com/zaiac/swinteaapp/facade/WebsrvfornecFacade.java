package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Websrvfornec;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class WebsrvfornecFacade extends AbstractFacade<Websrvfornec>{
    public WebsrvfornecFacade() {
        super(Websrvfornec.class);
    }
    
    public Websrvfornec findByWbfId (String wbfId) {
        Query query = getEm().createNamedQuery("Websrvfornec.findByWbfId");
        query.setParameter("wbfId", wbfId);
        return (Websrvfornec) query.getSingleResult();
    }
    
    public Boolean activeService (String wbfId) {
        Query query = getEm().createNamedQuery("Websrvfornec.findByWbfId");
        query.setParameter("wbfId", wbfId);
        Websrvfornec websrvfornec = (Websrvfornec) query.getSingleResult();
        return websrvfornec.getWbfAtivo() == (short)1;
    }
    
    
}
