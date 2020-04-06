package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Agente;
import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Checkpoint;
import br.com.zaiac.swinteaapp.entities.Comissao;
import br.com.zaiac.swinteaapp.entities.Lotepgto;
import br.com.zaiac.swinteaapp.entities.Pagamento;
import br.com.zaiac.swinteaapp.entities.Usuario;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PagamentoFacade extends AbstractFacade<Pagamento>{
    private EntityManager em;
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public PagamentoFacade() {
        super(Pagamento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Pagamento findByPagId(Long pagId) {
        Query query = getEm().createNamedQuery("Pagamento.findByPagId");
        query.setParameter("pagId", pagId);
        return (Pagamento) query.getSingleResult();
    }

    public Integer deleteByPckId(Checkpoint pckId) {
        Query query = getEm().createNamedQuery("Pagamento.deleteByPckId");
        query.setParameter("pckId", pckId);
        return query.executeUpdate();
    }

    public Integer deleteByPagId(Long pagId) {
        Query query = getEm().createNamedQuery("Pagamento.deleteByPagId");
        query.setParameter("pagId", pagId);
        return query.executeUpdate();
    }
    
    public Pagamento findPagamentoByAgeId (Agente ageId) {
        Query query = getEm().createNamedQuery("Pagamento.findByAgeId");
        query.setParameter("ageId", ageId.getAgeId());
        return (Pagamento) query.getSingleResult();
    }
    
    
    
    public Integer updatePagValorLopId (Lotepgto lopId, Comissao comissao) {
        Query query = getEm().createNamedQuery("Pagamento.findByLopId");
        query.setParameter("lopId", lopId);
        
        Query queryUpdatePagValorValorApuradoByPagId = getEm().createNamedQuery("Pagamento.updatePagValorValorApuradoByPagId");        

        List<Pagamento> pagamentos = query.getResultList();
        
        int i = 0;
        
        for (Pagamento pag : pagamentos) {
            i += 1;
            switch (pag.getTppId().getTppId()) {
                case 1:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComRecuperacao());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
                case 2:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComInvestigacao());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
                case 3:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComRastreado());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
                case 4:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComRoubofurto());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
                case 5:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComPatio());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
                case 6:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComDelegacia());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
                case 7:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComPatioRastreado());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
                case 8:
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagId", pag.getPagId());
                    queryUpdatePagValorValorApuradoByPagId.setParameter("pagValor", comissao.getComDelegaciaRastreado());
                    queryUpdatePagValorValorApuradoByPagId.executeUpdate();
                    break;
            }
        }
        return i;
    }
    
    
    public Integer updatePagValorLopIdRecuperacao (Lotepgto lopId, BigDecimal pagValor) {
        Query query = getEm().createNamedQuery("Pagamento.updatePagValorLopIdRecuperacao");
        query.setParameter("lopId", lopId);
        query.setParameter("pagValor", pagValor);
        return query.executeUpdate();
    }
    
    public Integer updatePagValorLopIdInvestigacao (Lotepgto lopId, BigDecimal pagValor) {
        Query query = getEm().createNamedQuery("Pagamento.updatePagValorLopIdInvestigacao");
        query.setParameter("lopId", lopId);
        query.setParameter("pagValor", pagValor);
        return query.executeUpdate();
    }


    public List<Pagamento> findPagEventual (Lotepgto lopId) {
        Query query = getEm().createNamedQuery("Pagamento.findPagEventual");
        query.setParameter("lopId", lopId);
        return query.getResultList();
    }
    
    
    public Integer updateLopIdToNull(Lotepgto lopId) {
        Query query = getEm().createNamedQuery("Pagamento.updateLopIdToNull");
        query.setParameter("lopId", lopId);
        return query.executeUpdate();
    }
    
    
}
