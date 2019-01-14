package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Operacao;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//@Stateless

public class AnaliseFacade extends AbstractFacade<Analise>{
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
    
    public AnaliseFacade() {
        super(Analise.class);
    }
    
    public Analise findByPbuId(Long pbuId) {
        Query query = getEm().createNamedQuery("Analise.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return (Analise) query.getSingleResult();
        
    }
    
    public List<Analise> findPbEmCampoPorOperacao (Operacao opeId) {
        Query query = getEm().createNamedQuery("Analise.findPbEmCampoPorOperacao");
        query.setParameter("opeId", opeId);
        return query.getResultList();
        
    }
    
}
