package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicasolote;
import br.com.zaiac.swinteaapp.entities.Clicasotipolote;
import br.com.zaiac.swinteaapp.entities.Cliente;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.Query;

public class ClicasoloteFacade extends AbstractFacade<Clicasolote>{
    public ClicasoloteFacade() {
        super(Clicasolote.class);
    }
    
    public Clicasolote findLoteAberto(Cliente cliId, Clicasotipolote a01Id) {
        Query query = getEm().createNamedQuery("Clicasolote.findLoteAberto");
        query.setParameter("cliId", cliId);
        query.setParameter("a01Id", a01Id);
        return (Clicasolote) query.getSingleResult();
        
        
    }
    
}
