package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Cliente;
import br.com.zaiac.swinteaapp.entities.Clientecobranca;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClientecobrancaFacade extends AbstractFacade<Clientecobranca>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ClientecobrancaFacade() {
        super(Clientecobranca.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Clientecobranca findByCliIdClbDt(Cliente cliId) {
        Query query = getEm().createNamedQuery("Clientecobranca.findByCliIdClbDt");
        query.setParameter("cliId", cliId.getCliId());
        return (Clientecobranca) query.getSingleResult();
    }
    
    public Clientecobranca findVlrCobrancaCliente(Cliente cliId, Date rcbDt) {
        Query query = getEm().createNamedQuery("Clientecobranca.findVlrCobrancaCliente");
        query.setParameter("cliId", cliId.getCliId());
        query.setParameter("rcbDt", rcbDt);
        return (Clientecobranca) query.getSingleResult();
    }
    
}
