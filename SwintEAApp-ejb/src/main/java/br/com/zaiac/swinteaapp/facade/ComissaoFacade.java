package br.com.zaiac.swinteaapp.facade;


import br.com.zaiac.swinteaapp.entities.Comissao;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ComissaoFacade extends AbstractFacade<Comissao> {
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ComissaoFacade() {
        super(Comissao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Comissao findByComId(Integer comId) {
        Query query = getEm().createNamedQuery("Comissao.findByComId");
        query.setParameter("comId", comId);
        return (Comissao) query.getSingleResult();
    }

    @Override
    public List<Comissao> findAll() {
        Query query = getEm().createNamedQuery("Comissao.findAll");
        return query.getResultList();
        
    }
    
    
}
