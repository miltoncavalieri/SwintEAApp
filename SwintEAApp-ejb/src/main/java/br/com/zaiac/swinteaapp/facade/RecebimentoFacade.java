package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Checkpoint;
import br.com.zaiac.swinteaapp.entities.Lotercbo;
import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.entities.Recebimento;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RecebimentoFacade extends AbstractFacade<Recebimento>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public RecebimentoFacade() {
        super(Recebimento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Recebimento findByRcbId(Long rcbId) {
        Query query = getEm().createNamedQuery("Recebimento.findByRcbId");
        query.setParameter("rcbId", rcbId);
        return (Recebimento) query.getSingleResult();
    }
    
    public Integer deleteByPckId(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Recebimento.deleteByPckId");
        query.setParameter("pckId", pckId);
        return query.executeUpdate();
    }
    
    public int updateRcbRelatorioByPbuId(Pedbus pbuId) {
        Query query = getEm().createNamedQuery("Recebimento.updateRcbRelatorioByPbuId");
        query.setParameter("pbuId", pbuId);
        return query.executeUpdate();
    }
    
    public int updateRcbRelatorioByPckId(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Recebimento.updateRcbRelatorioByPckId");
        query.setParameter("pckId", pckId);
        return query.executeUpdate();
    }
    
    public List<Recebimento> findByLcbId(Lotercbo lcbId) {
        Query query = getEm().createNamedQuery("Recebimento.findByLcbId");
        query.setParameter("lcbId", lcbId);
        return query.getResultList();
    }
    
    
    public int deleteRecEventual(Lotercbo lcbId) {
        Query query = getEm().createNamedQuery("Recebimento.deleteRecEventual");
        query.setParameter("lcbId", lcbId);
        return query.executeUpdate();
    }
    
    public int updateLcbIdToNull(Lotercbo lcbId) {
        Query query = getEm().createNamedQuery("Recebimento.updateLcbIdToNull");
        query.setParameter("lcbId", lcbId);
        return query.executeUpdate();
    }
    
}
