package br.com.zaiac.swinteaapp;

import br.com.zaiac.swinteaapp.entities.Clientecobranca;
import br.com.zaiac.swinteaapp.entities.Comissao;
import br.com.zaiac.swinteaapp.entities.Lotepgto;
import br.com.zaiac.swinteaapp.entities.Lotercbo;
import br.com.zaiac.swinteaapp.entities.Pagamento;
import br.com.zaiac.swinteaapp.entities.Recebimento;
import br.com.zaiac.swinteaapp.entities.Usuario;
import br.com.zaiac.swinteaapp.facade.AgenteFacade;
import br.com.zaiac.swinteaapp.facade.ClientecobrancaFacade;
import br.com.zaiac.swinteaapp.facade.ComissaoFacade;
import br.com.zaiac.swinteaapp.facade.LotepgtoFacade;
import br.com.zaiac.swinteaapp.facade.LotercboFacade;
import br.com.zaiac.swinteaapp.facade.PagamentoFacade;
import br.com.zaiac.swinteaapp.facade.RecebimentoFacade;
import br.com.zaiac.swinteaapp.facade.ReciboFacade;
import br.com.zaiac.swinteaapp.facade.UsuarioFacade;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.MANDATORY;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateless
@TransactionManagement(CONTAINER)
public class EJBFaturamento implements EJBFaturamentoRemote {
    private static final Logger logger = Logger.getLogger(EJBFaturamento.class.getName());
    
    @PersistenceContext(unitName = "SwintService", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    
/*
    
    
+------------------------------------------------------------------------------+
!                      Recalcular comissão de Agentes                          !  
+------------------------------------------------------------------------------+
    
    
*/    
    @Override
    @TransactionAttribute(MANDATORY)
    public void recalculaComissaoAgente (Long pLopId) throws Exception {
        LotepgtoFacade lotepgtoJpa = new LotepgtoFacade();
        ComissaoFacade comissaoJpa = new ComissaoFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        PagamentoFacade pagamentoJpa = new PagamentoFacade();
        
        lotepgtoJpa.setEm(em);
        comissaoJpa.setEm(em);
        usuarioJpa.setEm(em);
        pagamentoJpa.setEm(em);
        
        Lotepgto lotepgto;
        Usuario usuario;
        Comissao comissao;
        
        try {
            lotepgto = lotepgtoJpa.findByLopId(pLopId);
            try {
                usuario = usuarioJpa.findByUsuId(lotepgto.getUsuIdAgente().getUsuId());
                if (Objects.isNull(usuario.getComId())) {
                    comissao = comissaoJpa.findByComId(1);
                } else {
                    comissao = comissaoJpa.findByComId(usuario.getComId().getComId());                    
                }
                pagamentoJpa.updatePagValorLopId(lotepgto, comissao);
            } catch (NoResultException e) {
                e.printStackTrace();
                throw new Exception("Usuario do Lote de pagamento (" + pLopId + ") não encontrado. (" + lotepgto.getUsuIdAgente().getUsuId() + ")");
            }
            
        } catch (NoResultException e) {
            throw new Exception("Lote de pagamento (" + pLopId + ") não encontrado.");
        }
    }

/*
    
    
+------------------------------------------------------------------------------+
!                      Recalcular valor pago pelo cliente                      !  
+------------------------------------------------------------------------------+
    
    
*/    
    
    @Override
    @TransactionAttribute(MANDATORY)
    public void recalculaRecebimentoCliente (Long pLcbId)  throws Exception {
        
        LotercboFacade lotercboJpa = new LotercboFacade();
        RecebimentoFacade recebimentoJpa = new RecebimentoFacade();
        ClientecobrancaFacade clientecobrancaJpa = new ClientecobrancaFacade();
        
        lotercboJpa.setEm(em);
        recebimentoJpa.setEm(em);
        clientecobrancaJpa.setEm(em);
        
        Lotercbo lotercbo;
        List<Recebimento> recebimentos;
        try {
            lotercbo = lotercboJpa.findByLcbId(pLcbId);
        } catch (NoResultException e) {
            throw new Exception ("Lote de recebimento (" + pLcbId + ") nao encontrado");
        }
        
        Clientecobranca clientecobranca;
        
        recebimentos = recebimentoJpa.findByLcbId(lotercbo);
        
        for (Recebimento recebimento : recebimentos) {
            try {
                clientecobranca = clientecobrancaJpa.findVlrCobrancaCliente(lotercbo.getCliId(), recebimento.getRcbDt());
                switch (recebimento.getTppId().getTppId()) {
                    case 1:
                        recebimento.setRcbValor(clientecobranca.getClbVlrRecuperacao());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrRecuperacao());
                        recebimentoJpa.edit(recebimento);
                        break;
                    case 2:
                        recebimento.setRcbValor(clientecobranca.getClbVlrInvestigacao());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrInvestigacao());
                        recebimentoJpa.edit(recebimento);                        
                        break;
                    case 3:
                        recebimento.setRcbValor(clientecobranca.getClbVlrRastreado());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrRastreado());
                        recebimentoJpa.edit(recebimento);                        
                        break;
                    case 4:
                        recebimento.setRcbValor(clientecobranca.getClbVlrFurtoroubo());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrFurtoroubo());
                        recebimentoJpa.edit(recebimento);                        
                        break;
                    case 5:
                        recebimento.setRcbValor(clientecobranca.getClbVlrPatio());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrPatio());
                        recebimentoJpa.edit(recebimento);                        
                        break;
                    case 6:
                        recebimento.setRcbValor(clientecobranca.getClbVlrDelegacia());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrDelegacia());
                        recebimentoJpa.edit(recebimento);                        
                        break;
                    case 7:
                        recebimento.setRcbValor(clientecobranca.getClbVlrPatioRastreado());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrPatioRastreado());
                        recebimentoJpa.edit(recebimento);                        
                        break;
                    case 8:
                        recebimento.setRcbValor(clientecobranca.getClbVlrDelegaciaRastreado());
                        recebimento.setRcbValorApurado(clientecobranca.getClbVlrDelegaciaRastreado());
                        recebimentoJpa.edit(recebimento);                        
                        break;
                    default:
                        break;
                }
            } catch (NoResultException e) {
                logger.log(Level.SEVERE, "Lote: {1}. Cadastro de valores para cobranca nao foi localizado", new Object[]{pLcbId.toString()});
            }
        }
    }









/*
    
    
+------------------------------------------------------------------------------+
!                      Deletar o lote de pagamento                             !  
+------------------------------------------------------------------------------+
    
    
*/    
    @Override
    @TransactionAttribute(MANDATORY)
    public void deleteLotePagamento (Long pLopId) throws Exception {
        PagamentoFacade pagamentoJpa = new PagamentoFacade();
        LotepgtoFacade lotepgtoJpa = new LotepgtoFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        ReciboFacade reciboJpa = new ReciboFacade();
        pagamentoJpa.setEm(em);
        lotepgtoJpa.setEm(em);
        agenteJpa.setEm(em);
        reciboJpa.setEm(em);
        
        Lotepgto lotepgto;
        
        lotepgto = lotepgtoJpa.findByLopId(pLopId);        
        List<Pagamento> pagamentos = pagamentoJpa.findPagEventual(lotepgto);
        int ret;
        
//        System.out.println("Chegou aqui 1");
        for (Pagamento pagamento : pagamentos) {
            ret = pagamentoJpa.deleteByPagId(pagamento.getPagId());            
            ret = agenteJpa.deleteByAgeId(pagamento.getAgeId().getAgeId());
        }
//        System.out.println("Chegou aqui 2");
        pagamentoJpa.updateLopIdToNull(lotepgto);
        reciboJpa.updateLopIdToNull(lotepgto);
        lotepgtoJpa.deleteByLopId(pLopId);
//        System.out.println("Chegou aqui 3");
    }

/*
    
    
+------------------------------------------------------------------------------+
!                      Deletar lote de recebimento                             !  
+------------------------------------------------------------------------------+
    
    
*/    
    
    @Override
    @TransactionAttribute(MANDATORY)
    public void deleteLoteRecebimento (Long pLcbId)  throws Exception {
        RecebimentoFacade recebimentoJpa = new RecebimentoFacade();
        ReciboFacade reciboJpa = new ReciboFacade();
        LotercboFacade lotercboJpa = new LotercboFacade();
        
        recebimentoJpa.setEm(em);
        reciboJpa.setEm(em);
        lotercboJpa.setEm(em);
        
        Lotercbo lotercbo;
        lotercbo = lotercboJpa.findByLcbId(pLcbId);
        
        recebimentoJpa.deleteRecEventual(lotercbo);
        recebimentoJpa.updateLcbIdToNull(lotercbo);
        reciboJpa.updateLcbIdToNull(lotercbo);
        lotercboJpa.deleteByLcbId(pLcbId);
    }
    
    
}
