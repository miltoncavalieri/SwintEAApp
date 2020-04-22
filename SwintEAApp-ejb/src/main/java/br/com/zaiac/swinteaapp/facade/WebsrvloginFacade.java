package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Ambiente;
import br.com.zaiac.swinteaapp.entities.Websrvfornec;
import br.com.zaiac.swinteaapp.entities.Websrvlogin;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class WebsrvloginFacade extends AbstractFacade<Websrvlogin>{
    public WebsrvloginFacade() {
        super(Websrvlogin.class);
    }

    public Websrvlogin findLogin (Ambiente ambId, Websrvfornec wbfId) {
        Query query = getEm().createNamedQuery("Websrvlogin.findLogin");
        query.setParameter("ambId", ambId);
        query.setParameter("wbfId", wbfId);
        return (Websrvlogin) query.getSingleResult();
    }
    
    
}
