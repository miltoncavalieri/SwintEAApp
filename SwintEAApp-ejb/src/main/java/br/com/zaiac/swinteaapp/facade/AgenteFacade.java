package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Agente;
import br.com.zaiac.swinteaapp.entities.Checkpoint;
import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.entities.Usuario;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AgenteFacade extends AbstractFacade<Agente>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public AgenteFacade() {
        super(Agente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Integer updateByAgeIdSetPckIdNull(Long ageId) {
        Query query = getEm().createNamedQuery("Agente.updateByAgeIdSetPckIdNull");
        query.setParameter("ageId", ageId);
        return query.executeUpdate();
    }
    
    public Agente findByPbuIdAgsIdAtivo (Pedbus pbuId) {
        Query query = getEm().createNamedQuery("Agente.findByPbuIdAgsIdAtivo");
        query.setParameter("pbuId", pbuId);
        return (Agente) query.getSingleResult();
    }
    
    public Agente findByPckIdAgsIdCheckpoint (Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Agente.findByPckIdAgsIdCheckpoint");
        query.setParameter("pckId", pckId);
        return (Agente) query.getSingleResult();
    }
    
    public Integer deleteByAgeId(Long ageId) {
        Query query = getEm().createNamedQuery("Agente.deleteByAgeId");
        query.setParameter("ageId", ageId);
        return query.executeUpdate();
    }
    
    public Boolean findAgenteAtivo (Pedbus pbuId, Usuario usuId) {
        Query query = getEm().createNamedQuery("Agente.findAgenteAtivo");
        query.setParameter("pbuId", pbuId);
        query.setParameter("usuId", usuId);
        Long i = (Long) query.getSingleResult();
        if ( i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
