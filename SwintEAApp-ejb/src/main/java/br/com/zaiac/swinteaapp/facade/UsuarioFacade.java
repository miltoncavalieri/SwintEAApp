package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Usuario;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioFacade extends AbstractFacade<Usuario>{

    public UsuarioFacade() {
        super(Usuario.class);
    }
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Usuario findByUsuId (Integer usuId) {
        Query query = getEm().createNamedQuery("Usuario.findByUsuId");
        query.setParameter("usuId", usuId);
        return (Usuario) query.getSingleResult();
        
    }
    
}
