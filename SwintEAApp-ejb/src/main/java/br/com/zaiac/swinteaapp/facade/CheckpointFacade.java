package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Checkpoint;
import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.entities.Usuario;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import br.com.zaiac.swinteaapp.views.VwCheckpointStatusAprovado;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class CheckpointFacade extends AbstractFacade<Checkpoint>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public CheckpointFacade() {
        super(Checkpoint.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Checkpoint findByPckId(Long pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByPckId");
        query.setParameter("pckId", pckId);
        return (Checkpoint) query.getSingleResult();
    }
    
    
    public List<Checkpoint> findByPbuId(Pedbus pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByPbuId");
        query.setParameter("pbuId", pbuId);
        return query.getResultList();
    }
    
    public List<Checkpoint> findByPbuId(Analise pbuId, Usuario usuId) {
        String sql;
        String sqlSelect = "SELECT c FROM Checkpoint c WHERE c.pbuId = :pbuId ";
        String sqlWhere = "";
        String sqlOrderBy = "ORDER BY c.pckDt";
        if ((usuId.getUsuAgente() == 1) || ((!Objects.isNull(usuId.getCliId())))) {
            sqlWhere = " AND c.cktId = 2 ";
        }
        sql = sqlSelect + sqlWhere + sqlOrderBy;       

        Query query = getEm().createQuery(sql);
        query.setParameter("pbuId", pbuId);
        return query.getResultList();
    }
    
    
    
    public List<Checkpoint> findByPbuIdMobile(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByPbuIdMobile");
        query.setParameter("pbuId", pbuId);
        return query.getResultList();
    }
    
    public void deleteByPckId (Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.deleteByPckId");
        query.setParameter("pckId", pckId.getPckId());
        query.executeUpdate();
    }
    
    
    public Boolean findExistsInvestigadoRecuperado(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByInvestigadoRecuperado");
        query.setParameter("pbuId", pbuId);
        List<Checkpoint> ckps = query.getResultList();
        if (ckps.isEmpty()) {
            return false;
        } else {
            return true;
        }
        
    }
    
    public int reverteEncerrado(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.reverteEncerrado");
        query.setParameter("pbuId", pbuId);
        return query.executeUpdate();
        
    }
    
    
    public Checkpoint findByInvestigadoRecuperado(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByInvestigadoRecuperado");
        query.setParameter("pbuId", pbuId);
        return (Checkpoint) query.getSingleResult();
    }
    
    public Checkpoint findCheckpointParaVoltar(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findCheckpointParaVoltar");
        query.setParameter("pbuId", pbuId);
        return (Checkpoint) query.getSingleResult();
    }
    
    
    public Checkpoint findByPckIdStatusAprovado(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByPckIdStatusAprovado");
        query.setParameter("pckId", pckId);
        return (Checkpoint) query.getSingleResult();
    }
    
    public Checkpoint findByPckIdStatusReprovado(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByPckIdStatusReprovado");
        query.setParameter("pckId", pckId);
        return (Checkpoint) query.getSingleResult();
    }
    
    
    
    
    public Checkpoint findByAprovacaoInvestigadoRecuperado(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findByPckId");
        Query queryByPckIdAprovado = getEm().createNamedQuery("VwCheckpointStatusAprovado.findByPbuId");
        
        queryByPckIdAprovado.setParameter("pbuId", pbuId);
        VwCheckpointStatusAprovado vwCheckpointStatusAprovado = (VwCheckpointStatusAprovado) queryByPckIdAprovado.getSingleResult();
        
        
        query.setParameter("pckId", vwCheckpointStatusAprovado.getPckId());     
        return (Checkpoint) query.getSingleResult();
    }
    
    public Integer updatePckAtivo(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.updatePckAtivo");
        query.setParameter("pckIdAprovado", pckId);
        query.setParameter("pckId", pckId.getPckId());
        return query.executeUpdate();
    }
    
    public Integer updatePckAtivoRelatorioAprovado(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.updatePckAtivoRelatorioAprovado");
        query.setParameter("pckId", pckId);
        return query.executeUpdate();
    }
    
    public Integer updatePckAtivoRelatorioReprovado(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.updatePckAtivoRelatorioReprovado");
        query.setParameter("pckId", pckId);
        return query.executeUpdate();
    }
    
    public Integer updatePckAtivoRelatorio(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Checkpoint.updatePckAtivoRelatorio");
        query.setParameter("pckId", pckId);
        return query.executeUpdate();
    }
    
    public Integer updateClearPromdev(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.updateClearPromdev");
        query.setParameter("pbuId", pbuId);
        return query.executeUpdate();
    }
    
    public Boolean checkPromessaDevolucaoAtiva(Analise pbuId) {
        Query queryPv;
        Query queryDv;
        
        Checkpoint ckpPv;
        Checkpoint ckpDv;
        try {
            queryPv = getEm().createNamedQuery("Checkpoint.findPromessaDevolucaoAtiva");
            queryPv.setParameter("pbuId", pbuId);
            ckpPv = (Checkpoint) queryPv.getSingleResult();
            
            try {
                queryDv = getEm().createNamedQuery("Checkpoint.findVeiculoDevolvidoAtiva");
                queryDv.setParameter("pbuId", pbuId);
                ckpDv = (Checkpoint) queryDv.getSingleResult();
                return false;
            } catch (NoResultException e) {
                return true;
            }
        } catch (NoResultException e) {
            return false;    
        }
    }
    
    
    public Checkpoint findPromessaDevolucaoAtiva(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findPromessaDevolucaoAtiva");
        query.setParameter("pbuId", pbuId);
        return (Checkpoint) query.getSingleResult();
    }
    
    public Checkpoint findVeiculoDevolvidoAtiva(Analise pbuId) {
        Query query = getEm().createNamedQuery("Checkpoint.findVeiculoDevolvidoAtiva");
        query.setParameter("pbuId", pbuId);
        return (Checkpoint) query.getSingleResult();
    }
    
    public Integer updateImagemCheckpoint(Long pckId, String pckImagem) {
        Query queryUpdate = getEm().createNamedQuery("Checkpoint.updateImagemCheckpoint");
        queryUpdate.setParameter("pckId", pckId);
        queryUpdate.setParameter("pckImagem", pckImagem);
        return queryUpdate.executeUpdate();
    }
    
    
    public Integer updateBatch(Long pckId, String pckDescricao, String pckLegendaFoto, String pckImagem, Short pckRelatorio) {
        Query queryUpdate = getEm().createNamedQuery("Checkpoint.updateBatch");
        queryUpdate.setParameter("pckId", pckId);
        queryUpdate.setParameter("pckDescricao", pckDescricao);
        queryUpdate.setParameter("pckLegendaFoto", pckLegendaFoto);
        queryUpdate.setParameter("pckImagem", pckImagem);
        queryUpdate.setParameter("pckRelatorio", pckRelatorio);        
        return queryUpdate.executeUpdate();
    }
    
    public Integer updateBatchDeleteFoto(Long pckId) {
        Query queryUpdate = getEm().createNamedQuery("Checkpoint.updateBatchDeleteFoto");
        queryUpdate.setParameter("pckId", pckId);
        return queryUpdate.executeUpdate();
    }
    
    public void deleteBatch(Long pckId) {
        try {
            Query query = getEm().createNamedQuery("Checkpoint.findByPckId");
            query.setParameter("pckId", pckId);
            
            Checkpoint checkpoint = (Checkpoint) query.getSingleResult();
            
            if (!(checkpoint.getPckIdAprovado() == null)) {
                pckId = checkpoint.getPckIdAprovado().getPckId();
            }
            Query queryUpdateSlave = getEm().createNamedQuery("Checkpoint.deleteBatchSlave");
            queryUpdateSlave.setParameter("pckId", pckId);
            queryUpdateSlave.executeUpdate();
        
            Query queryUpdateMaster = getEm().createNamedQuery("Checkpoint.deleteBatchMaster");
            queryUpdateMaster.setParameter("pckId", pckId);        
            queryUpdateMaster.executeUpdate();
            
        } catch (NoResultException e) {
            
        }
        
    }
    
}
