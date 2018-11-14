package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Ckpleitura;
import br.com.zaiac.swinteaapp.entities.Usuario;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CkpleituraFacade extends AbstractFacade<Ckpleitura>{

    public CkpleituraFacade() {
        super(Ckpleitura.class);
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
    
    
    public int updateUltimaLeitura(Analise pbuId, Usuario usuId) {
        Query query = getEm().createNamedQuery("Ckpleitura.updateUltimaLeitura");
        query.setParameter("pbuId", pbuId);
        query.setParameter("usuId", usuId);
        return query.executeUpdate();        
    }
    
    
}
