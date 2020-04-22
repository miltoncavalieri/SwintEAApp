package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Ambiente;
import br.com.zaiac.swinteaapp.entities.Websrvfornec;
import br.com.zaiac.swinteaapp.entities.Websrvjson;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class WebsrvjsonFacade extends AbstractFacade<Websrvjson>{
    public WebsrvjsonFacade() {
        super(Websrvjson.class);
    }


    public Websrvjson findService(Ambiente ambId, Websrvfornec wbfId, String jsoOp) {
        Query query = getEm().createNamedQuery("Websrvjson.findService");
        query.setParameter("ambId", ambId);
        query.setParameter("wbfId", wbfId);
        query.setParameter("jsoOp", jsoOp);
        return (Websrvjson) query.getSingleResult();
    }
    
    public Websrvjson findServiceProducao(Websrvfornec wbfId, String jsoOp) {
        Query query = getEm().createNamedQuery("Websrvjson.findServiceProducao");
        query.setParameter("wbfId", wbfId);
        query.setParameter("jsoOp", jsoOp);
        return (Websrvjson) query.getSingleResult();
    }
    
    public Websrvjson findServiceProducaoDatavalid(String jsoOp) {
        Query query = getEm().createNamedQuery("Websrvjson.findServiceProducaoDatavalid");
        query.setParameter("jsoOp", jsoOp);
        return (Websrvjson) query.getSingleResult();
    }
    
    
}
