package br.com.zaiac.swinteaapp;

import br.com.zaiac.swinteaapp.entities.Agente;
import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.CepLocalidade;
import br.com.zaiac.swinteaapp.entities.Checkpoint;
import br.com.zaiac.swinteaapp.entities.Ckpleitura;
import br.com.zaiac.swinteaapp.entities.Cliente;
import br.com.zaiac.swinteaapp.entities.Clientecobranca;
import br.com.zaiac.swinteaapp.entities.Comissao;
import br.com.zaiac.swinteaapp.entities.Pagamento;
import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.entities.Recebimento;
import br.com.zaiac.swinteaapp.entities.Tipopag;
import br.com.zaiac.swinteaapp.entities.Usuario;
import br.com.zaiac.swinteaapp.facade.AgenteFacade;
import br.com.zaiac.swinteaapp.facade.AgentesitFacade;
import br.com.zaiac.swinteaapp.facade.AnaliseFacade;
import br.com.zaiac.swinteaapp.facade.CepLocalidadeFacade;
import br.com.zaiac.swinteaapp.facade.CheckpointFacade;
import br.com.zaiac.swinteaapp.facade.CkpleituraFacade;
import br.com.zaiac.swinteaapp.facade.CkptipoFacade;
import br.com.zaiac.swinteaapp.facade.ClienteFacade;
import br.com.zaiac.swinteaapp.facade.ClientecobrancaFacade;
import br.com.zaiac.swinteaapp.facade.ComissaoFacade;
import br.com.zaiac.swinteaapp.facade.EstadoFacade;
import br.com.zaiac.swinteaapp.facade.PagamentoFacade;
import br.com.zaiac.swinteaapp.facade.PbsubstatusFacade;
import br.com.zaiac.swinteaapp.facade.PedbusFacade;
import br.com.zaiac.swinteaapp.facade.PedbusfaseFacade;
import br.com.zaiac.swinteaapp.facade.PedbusitFacade;
import br.com.zaiac.swinteaapp.facade.PedbusrelFacade;
import br.com.zaiac.swinteaapp.facade.RecebimentoFacade;
import br.com.zaiac.swinteaapp.facade.TipopagFacade;
import br.com.zaiac.swinteaapp.facade.UsuarioFacade;
import br.com.zaiac.swinteaapp.facade.VwPagrecInvestigadoFacade;
import br.com.zaiac.swinteaapp.facade.VwPedbusPagoRecebidoFacade;
import br.com.zaiac.swinteaapp.views.VwPagrecInvestigado;
import br.com.zaiac.swinteaapp.views.VwPedbusPagoRecebido;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
//import static javax.transaction.Transactional.TxType.MANDATORY;
//import javax.transaction.Transactional;

@Stateless

@TransactionManagement(CONTAINER)

public class EJBOperacao implements EJBOperacaoRemote {
    private static final Logger logger = Logger.getLogger(EJBOperacao.class.getName());
    
    @PersistenceContext(unitName = "SwintService", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

/*
    
    
+------------------------------------------------------------------------------+
!        Avançar a data de Leitura de Checkpoint por Usuario / PB              !
+------------------------------------------------------------------------------+
    
    
*/    
    
    @Override
    @TransactionAttribute(MANDATORY)
    public Integer UpdateCheckpointLeitura(Long pPbuId, Integer pUsuId) throws Exception {
        CkpleituraFacade ckpleituraJpa = new CkpleituraFacade();
        AnaliseFacade analiseJpa = new AnaliseFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        PedbusFacade pedbusJpa = new PedbusFacade();
        
        ckpleituraJpa.setEm(em);
        analiseJpa.setEm(em);
        usuarioJpa.setEm(em);
        pedbusJpa.setEm(em);
        
        Analise pbuId = analiseJpa.findByPbuId(pPbuId);
        Usuario usuId = usuarioJpa.findByUsuId(pUsuId);
        
        Integer ret = ckpleituraJpa.updateUltimaLeitura(pbuId, usuId);
        
        Date dtsys = new Date(System.currentTimeMillis());
        
        if (ret == 0) {
            try {
                Ckpleitura chkleitura = new Ckpleitura();
                chkleitura.setPbuId(pbuId);
                chkleitura.setUsuId(usuId);
                chkleitura.setPclDtLida(dtsys);
                ckpleituraJpa.create(chkleitura);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
        return 0;

    }

/*
    
    
+------------------------------------------------------------------------------+
!                         Aprovar mudança de Status de PB                      !
+------------------------------------------------------------------------------+
    
    
    
*/    

    @Override
    public void aprovarMudancaStatus(Long pPbuId, Long pPckId, Integer userId, String tipoOperacao) {
        PedbusFacade pedbusJpa = new PedbusFacade();
        AnaliseFacade analiseJpa = new AnaliseFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        CheckpointFacade checkpointJpa = new CheckpointFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        
        
        pedbusJpa.setEm(em); 
        analiseJpa.setEm(em); 
        usuarioJpa.setEm(em); 
        ckptipoJpa.setEm(em); 
        checkpointJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        agenteJpa.setEm(em);
        
        Analise analise = analiseJpa.findByPbuId(pPbuId);
        Pedbus pedbus = pedbusJpa.findByPbuId(pPbuId);
        Usuario usuario = usuarioJpa.findByUsuId(userId);
        Agente agente = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);
        
        Checkpoint CheckpointAprovado = checkpointJpa.findByPckId(pPckId);        
        CheckpointAprovado.setPckAprovadoms((short) 1);
        
        Date dtsys = new Date(System.currentTimeMillis());

        Checkpoint checkpoint = new Checkpoint();
        
        checkpoint.setPbuId(analise);
        checkpoint.setPckDt(dtsys);
        checkpoint.setUsuIdCkp(usuario);
        if (tipoOperacao.equals("AUTO")) {
            checkpoint.setPckDescricao((CheckpointAprovado.getCktId().getCktId() == 3 ? "Aprovacao Investigado - Automatico" : "Aprovacao Recuperado - Automatico"));
        } else {
            checkpoint.setPckDescricao((CheckpointAprovado.getCktId().getCktId() == 3 ? "Aprovacao Investigado - Manual" : "Aprovacao Recuperado - Manual"));
        }
        checkpoint.setPckRelatorio((short)0);
        checkpoint.setCktId(ckptipoJpa.findByCktId((short) 9));
        checkpoint.setAgeId(agente);
        checkpoint.setPckIdAprovado(CheckpointAprovado);
        checkpoint.setPckAtivo((short)1);
//        checkpoint.setPckIdAprovado(CheckpointAprovado);
        checkpoint.setFasId(pedbusfaseJpa.findByFasId((short)2));
        checkpoint.setEuiId(pedbus.getEuiId());
        checkpointJpa.create(checkpoint);        
        checkpointJpa.edit(CheckpointAprovado);        
//        pedbus.setPbuAprovadoms((short) 1);
        pedbusJpa.edit(pedbus);
        logger.log(Level.WARNING, "Mudanca de status do PB: " + pPbuId + " concluida com sucesso");
    }
    

/*
    
    
+------------------------------------------------------------------------------+
!                        Cancelar mudança de Status de PB                      !
+------------------------------------------------------------------------------+
    
    
    
*/    
    
    
    
    @Override
    public void cancelarMudancaStatus(Long pPbuId, Long pPckId, Integer userId) {
        PedbusFacade pedbusJpa = new PedbusFacade();
        AnaliseFacade analiseJpa = new AnaliseFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        CheckpointFacade checkpointJpa = new CheckpointFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        
        pedbusJpa.setEm(em); 
        analiseJpa.setEm(em); 
        usuarioJpa.setEm(em); 
        ckptipoJpa.setEm(em); 
        checkpointJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        agenteJpa.setEm(em);
        
        Analise analise = analiseJpa.findByPbuId(pPbuId);
        Pedbus pedbus = pedbusJpa.findByPbuId(pPbuId);
        Usuario usuario = usuarioJpa.findByUsuId(userId);
        Agente agente = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);
        
        Date dtsys = new Date(System.currentTimeMillis());
        
        Checkpoint CheckpointAprovado = checkpointJpa.findByPckId(pPckId);   
        
        try {
            Checkpoint CheckpointStatusAprovado = checkpointJpa.findByPckIdStatusAprovado(CheckpointAprovado);
            CheckpointStatusAprovado.setPckAtivo((short)0);
            checkpointJpa.edit(CheckpointStatusAprovado);
        } catch (NoResultException e) {
            
        }
        
        try {
            Checkpoint CheckpointStatusReprovado = checkpointJpa.findByPckIdStatusReprovado(CheckpointAprovado);
            CheckpointStatusReprovado.setPckAtivo((short)0);
            checkpointJpa.edit(CheckpointStatusReprovado);
        } catch (NoResultException e) {
            
        }
        
        
        
        CheckpointAprovado.setPckAprovadoms((short) 0);
        checkpointJpa.edit(CheckpointAprovado);        

        Checkpoint checkpointNew = new Checkpoint();
        checkpointNew.setPbuId(analise);
        checkpointNew.setPckDt(dtsys);
        checkpointNew.setUsuIdCkp(usuario);
        checkpointNew.setPckDescricao((CheckpointAprovado.getCktId().getCktId() == 3 ? "Cancelamento Aprovacao Investigado" : "Cancelamento Aprovacao Recuperado"));
        checkpointNew.setPckRelatorio((short) 0);
        checkpointNew.setCktId(ckptipoJpa.findByCktId((short) 12));
        checkpointNew.setPckAtivo((short)1);        
        checkpointNew.setPbuId(analise);
        checkpointNew.setAgeId(agente);
        checkpointNew.setEuiId(pedbus.getEuiId());
        checkpointNew.setFasId(pedbusfaseJpa.findByFasId((short) 2));
        checkpointNew.setPckIdAprovado(CheckpointAprovado);
        checkpointJpa.create(checkpointNew);
    }    
    
    

/*
    
    
+------------------------------------------------------------------------------+
!                         Voltar o Checkpoint para Campo                       !
+------------------------------------------------------------------------------+
    
    
    
*/    
    @Override
    public void voltarPbParaCampo(Long pPckId, Integer pUsuId) throws Exception {
        VwPedbusPagoRecebidoFacade vwPedbusPagoRecebidoJpa = new VwPedbusPagoRecebidoFacade();
        PagamentoFacade pagamentoJpa = new PagamentoFacade();
        RecebimentoFacade recebimentoJpa = new RecebimentoFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        
        AnaliseFacade analiseJpa = new AnaliseFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        PedbusFacade pedbusJpa = new PedbusFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        PedbusrelFacade pedbusrelJpa = new PedbusrelFacade();
        
        vwPedbusPagoRecebidoJpa.setEm(em);
        pagamentoJpa.setEm(em);
        recebimentoJpa.setEm(em);        
        checkpointJpa.setEm(em);
        analiseJpa.setEm(em);
        usuarioJpa.setEm(em);
        ckptipoJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        pedbusJpa.setEm(em);
        agenteJpa.setEm(em);
        pedbusrelJpa.setEm(em);
        
        VwPedbusPagoRecebido vwPedbusPagoRecebido;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dir;

        
        Date dtsys = new Date(System.currentTimeMillis());
        
        Checkpoint checkpointAtual = checkpointJpa.findByPckId(pPckId); 

        Checkpoint checkpointParaVoltar = checkpointJpa.findCheckpointParaVoltar(checkpointAtual.getPbuId());
        Analise analise = analiseJpa.findByPbuId(checkpointParaVoltar.getPbuId().getPbuId());
        
        try {
            vwPedbusPagoRecebido = vwPedbusPagoRecebidoJpa.findByPckId(checkpointParaVoltar.getPckId());
        } catch (NoResultException e) {
            logger.log(Level.INFO, "Operacao nao encontrada PCK: {0}", checkpointParaVoltar.getPbuId().getPbuId());
            throw new Exception("Operacao nao encontrada PCK: " + checkpointParaVoltar.getPbuId().getPbuId());
        }
        
        if (vwPedbusPagoRecebido.getPbsId() == 3) {
            if (vwPedbusPagoRecebido.getLcbId() != null) {
                logger.log(Level.SEVERE, "Operacao nao permitida para PB ja recebido/recuperado: {0} / {1}", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
                throw new Exception("Operacao nao permitida para PB ja recebido: "+ vwPedbusPagoRecebido + "/" + checkpointParaVoltar.getPbuId().getPbuId());
            }
            
            if (vwPedbusPagoRecebido.getLopId()!= null) {
                logger.log(Level.SEVERE, "Operacao nao permitida para PB ja pago: {0} / {1}", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
                throw new Exception("Operacao nao permitida para PB ja pago: "+ vwPedbusPagoRecebido + "/" + checkpointParaVoltar.getPbuId().getPbuId());
            }
        }

        if (vwPedbusPagoRecebido.getPbsId() == 1) {
            logger.log(Level.SEVERE, "Operacao nao permitida para PB em campo: {0} / {1}", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
            throw new Exception("Operacao nao permitida para PB ja recebido: "+ vwPedbusPagoRecebido + "/" + checkpointParaVoltar.getPbuId().getPbuId());
        }

        Agente agente = agenteJpa.findByPbuIdAgsIdAtivo(checkpointParaVoltar.getAgeId().getPbuId());
        Agente agenteNew = null;
        Integer ret;
        
        if ((vwPedbusPagoRecebido.getPbsId() == 2) && ((vwPedbusPagoRecebido.getLcbId() != null) || (vwPedbusPagoRecebido.getLopId() != null))) {
            agenteNew = new Agente();
            agenteNew.setUsuId(agente.getUsuId());
            agenteNew.setAgeDtDesignacao(dtsys);
            agenteNew.setAgsId(agente.getAgsId());
            agenteNew.setPbuId(agente.getPbuId());            
            agenteJpa.create(agenteNew);
        } else {
            if (vwPedbusPagoRecebido.getPagId() != null) {
                pagamentoJpa.deleteByPckId(checkpointParaVoltar);
            }
        
            if (vwPedbusPagoRecebido.getRcbId() != null) {
                recebimentoJpa.deleteByPckId(checkpointParaVoltar);
            }
            ret = pedbusrelJpa.deleteByPckId(checkpointParaVoltar);
            if (ret > 0) {
                logger.log(Level.INFO, "Removida entrada Pedbusrel PB/CHECKPOINT {0}/{1}, foi removido", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(),checkpointParaVoltar.getPckId()}); 
                dir = diretorioPb(analise.getPbuId(), analise.getAnaDt(), "relatorio");
                File file = new File(dir, checkpointParaVoltar.getPckId() + ".pdf");
                if (file.exists()) {
                    logger.log(Level.INFO, "Arquivo Relatorio de cliente: {0}, foi removido", file.getAbsolutePath());
                    file.delete();
                } else {
                    logger.log(Level.INFO, "Arquivo Relatorio de cliente: {0}, ainda nao gerado", file.getAbsolutePath());
                }   
            }
            checkpointJpa.updatePckAtivo(checkpointParaVoltar);
        }
        Checkpoint checkpoint = new Checkpoint();
        
        checkpoint.setPbuId(analise);
        checkpoint.setPckDt(dtsys);
        checkpoint.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuId));
        checkpoint.setCktId(ckptipoJpa.findByCktId((short) 11));
        checkpoint.setPckAtivo((short)1);
        checkpoint.setFasId(pedbusfaseJpa.findByFasId((short)2));
        if ((vwPedbusPagoRecebido.getPbsId() == 2) && ((vwPedbusPagoRecebido.getLcbId() != null) || (vwPedbusPagoRecebido.getLopId() != null))) {
            checkpoint.setAgeId(agenteNew);
            System.out.println("Entrou 4: ");
        } else {
            System.out.println("Entrou 5: ");
            checkpoint.setAgeId(agente);
            agenteJpa.updateByAgeIdSetPckIdNull(vwPedbusPagoRecebido.getAgeId());  
        }
        checkpoint.setEuiId(checkpoint.getPbuId().getPedbus().getEuiId());        
        checkpointJpa.create(checkpoint);
        pedbusJpa.updatePbParaCampo(vwPedbusPagoRecebido.getPbuId());
        logger.log(Level.INFO, "Operacao permitida para PB: {0} / {1} - Volta para CAMPO", new Object[]{vwPedbusPagoRecebido.getPbuId(), pPckId});
    }
    


/*
    
    
+------------------------------------------------------------------------------+
!                        Aprovar Relatorio                                     !
+------------------------------------------------------------------------------+
    
    
    
*/    
    

    @Override
    public void pbAprovarRelatorio(Long pPckId, Integer pUsuId) {
        AnaliseFacade analiseJpa = new AnaliseFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        PagamentoFacade pagamentoJpa  = new PagamentoFacade();
        ComissaoFacade comissaoJpa  = new ComissaoFacade();
        
        checkpointJpa.setEm(em);
        ckptipoJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        usuarioJpa.setEm(em);
        pagamentoJpa.setEm(em);
        comissaoJpa.setEm(em);
        agenteJpa.setEm(em);


        Checkpoint checkpoint = checkpointJpa.findByPckId(pPckId);
        Analise analise = analiseJpa.findByPbuId(checkpoint.getPbuId().getPbuId());
        Agente agente = agenteJpa.findByPckIdAgsIdCheckpoint(checkpoint);
        
        Date dtsys = new Date(System.currentTimeMillis());        
        
        checkpoint.setPckAprovado((short)1);
        checkpoint.setPckRecusado((short)0);
        checkpointJpa.edit(checkpoint);
        checkpointJpa.updatePckAtivoRelatorioAprovado(checkpoint);
        
        
        
        Checkpoint checkpointNew = new Checkpoint();
        checkpointNew.setPbuId(analise);
        checkpointNew.setFasId(pedbusfaseJpa.findByFasId((short) 2));
        checkpointNew.setPckDt(dtsys);
        checkpointNew.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuId));
        checkpointNew.setCktId(ckptipoJpa.findByCktId(Short.parseShort("7")));
        checkpointNew.setAgeId(agente);
        checkpointNew.setPckAtivo((short)1);
        checkpointNew.setEuiId(checkpoint.getPbuId().getPedbus().getEuiId());
        checkpointNew.setFasId(pedbusfaseJpa.findByFasId((short)2));
        checkpointNew.setPckIdAprovado(checkpoint);
        checkpointNew.setPckDescricao("Relatorio Aprovado");
                
                
        Pagamento pagamento = pagamentoJpa.findPagamentoByAgeId(agente);
        pagamento.setPagRelatorio((short)1);
        Comissao comissao;
        if (agente.getUsuId().getComId() == null) {
            comissao = comissaoJpa.findByComId(1);
        } else {
            comissao = comissaoJpa.findByComId(agente.getUsuId().getComId().getComId());
        }
        pagamento.setPagDtPrevisao(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * comissao.getComPrazo())));
        checkpointJpa.create(checkpointNew);
        pagamentoJpa.edit(pagamento); 
        
    }
    
/*
    
    
+------------------------------------------------------------------------------+
!                                Reprovar relatorio                            !
+------------------------------------------------------------------------------+
    
    
    
*/    
    

    @Override
    public void pbReprovarRelatorio(Long pPckId, Integer pUsuId) {
        AnaliseFacade analiseJpa = new AnaliseFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        
        checkpointJpa.setEm(em);
        ckptipoJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        usuarioJpa.setEm(em);
        agenteJpa.setEm(em);
        
        Date dtsys = new Date(System.currentTimeMillis());        

        Checkpoint checkpoint = checkpointJpa.findByPckId(pPckId);
        Analise analise = analiseJpa.findByPbuId(checkpoint.getPbuId().getPbuId());
        Agente agente = agenteJpa.findByPckIdAgsIdCheckpoint(checkpoint);

        checkpoint.setPckAprovado((short)0);
        checkpoint.setPckRecusado((short)1);
        
        checkpointJpa.edit(checkpoint);        
        checkpointJpa.updatePckAtivoRelatorioReprovado(checkpoint);

        Checkpoint checkpointNew = new Checkpoint();
        
        checkpointNew.setPbuId(analise);
        checkpointNew.setPckDt(dtsys);
        checkpointNew.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuId));
        checkpointNew.setCktId(ckptipoJpa.findByCktId((short) 6));
        checkpointNew.setPckIdAprovado(checkpoint);
        checkpointNew.setPckDescricao("Relatorio Reprovado");
        checkpointNew.setPckAtivo((short)1);
        checkpointNew.setAgeId(agente);
        checkpointNew.setEuiId(checkpoint.getPbuId().getPedbus().getEuiId());
        checkpointNew.setFasId(pedbusfaseJpa.findByFasId((short)2));
        checkpointJpa.create(checkpointNew);
    }
    
    
/*
    
    
+------------------------------------------------------------------------------+
!                                Reprovar relatorio                            !
+------------------------------------------------------------------------------+
    
    
    
*/    
    
    @Override
    public void pbGerarRelatorio(Long pPckId, Integer pUsuId, Short pEstIdRecup, Long pLocNuRecup) {
        AnaliseFacade analiseJpa = new AnaliseFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        PedbusFacade pedbusJpa = new PedbusFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        RecebimentoFacade recebimentoJpa = new RecebimentoFacade();
        EstadoFacade estadoJpa = new EstadoFacade();
        CepLocalidadeFacade cepLocalidadeJpa = new CepLocalidadeFacade();
        
        
        checkpointJpa.setEm(em);
        ckptipoJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        usuarioJpa.setEm(em);
        recebimentoJpa.setEm(em);
        agenteJpa.setEm(em);
        estadoJpa.setEm(em);
        cepLocalidadeJpa.setEm(em);
        
        logger.log(Level.INFO, "pbGerarRelatorio: {0}", pPckId);
        
        Date dtsys = new Date(System.currentTimeMillis());        

        Checkpoint checkpoint = checkpointJpa.findByPckId(pPckId);
        checkpoint.setPckRelatoriofeito((short)1);
        checkpoint.setPckAprovado((short)0);
        checkpoint.setPckRecusado((short)0);
        checkpoint.setEstIdRecup(estadoJpa.findByEstId(pEstIdRecup));
        checkpoint.setLocNuRecup(cepLocalidadeJpa.findByLocNu(pLocNuRecup));        
        checkpointJpa.edit(checkpoint);
        checkpointJpa.updatePckAtivoRelatorio(checkpoint);
        
        
//        checkpointJpa.updatePckAtivoRelatorioAprovado(checkpoint);
//        checkpointJpa.updatePckAtivoRelatorioReprovado(checkpoint);
        
        Analise analise = analiseJpa.findByPbuId(checkpoint.getPbuId().getPbuId());
        Agente agente = agenteJpa.findByPckIdAgsIdCheckpoint(checkpoint);
        
        Checkpoint checkpointNew = new Checkpoint();

        checkpointNew.setPbuId(analise);
        checkpointNew.setPckDt(dtsys);
        checkpointNew.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuId));
        checkpointNew.setCktId(ckptipoJpa.findByCktId((short) 5));
        checkpointNew.setPckIdAprovado(checkpoint);
        checkpointJpa.edit(checkpoint);      
        checkpointNew.setPckRelatorio((short) 0);
        checkpointNew.setAgeId(agente);
        checkpointNew.setPckDescricao("Relatório Gerado");
        checkpointNew.setPckAtivo((short)1);
        checkpointNew.setFasId(pedbusfaseJpa.findByFasId((short)2));
        checkpointNew.setEuiId(checkpoint.getPbuId().getPedbus().getEuiId());
        checkpointJpa.create(checkpointNew);        
        recebimentoJpa.updateRcbRelatorioByPckId(checkpoint);
    }
    
/*
    
    
+------------------------------------------------------------------------------+
!                      Gravar Promessa de Devolução                            !
+------------------------------------------------------------------------------+
    
    
    
*/    
    @Override
    public void pbGerarPromessaDevolucao(Long pPbuId, Date pPpckDtPromdev, String pPpckDescricao, Integer pUsuId) throws Exception {
        AnaliseFacade analiseJpa = new AnaliseFacade();
        PedbusFacade pedbusJpa = new PedbusFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        RecebimentoFacade recebimentoJpa = new RecebimentoFacade();
        
        Date dtsys = new Date(System.currentTimeMillis());
        
        if (dtsys.after(pPpckDtPromdev)) {
           throw new Exception ("Data anterior a data de hoje");
        }
        
        checkpointJpa.setEm(em);
        ckptipoJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        usuarioJpa.setEm(em);
        recebimentoJpa.setEm(em);
        agenteJpa.setEm(em);
        pedbusJpa.setEm(em);
        
        logger.log(Level.INFO, "pbGerarPromessaDevolucao: {0}", pPbuId);
        
        Analise analise = analiseJpa.findByPbuId(pPbuId);
        Pedbus pedbus = pedbusJpa.findByPbuId(pPbuId);
        Agente agente = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);
        
        checkpointJpa.updateClearPromdev(analise);
        
        Checkpoint checkpointNew = new Checkpoint();
        checkpointNew.setPbuId(analise);
        checkpointNew.setPckDt(dtsys);
        checkpointNew.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuId));
        checkpointNew.setCktId(ckptipoJpa.findByCktId((short) 14));
        checkpointNew.setPckRelatorio((short) 0);
        checkpointNew.setAgeId(agente);
        checkpointNew.setPckDescricao(pPpckDescricao);
        checkpointNew.setPckDtPromdev(pPpckDtPromdev);
        checkpointNew.setPckAtivo((short)1);
        checkpointNew.setFasId(pedbusfaseJpa.findByFasId((short)2));
        checkpointNew.setEuiId(pedbus.getEuiId());
        checkpointJpa.create(checkpointNew);        
    }

/*
    
    
+------------------------------------------------------------------------------+
!                      Gravar Promessa de Devolução                            !
+------------------------------------------------------------------------------+
    
    
    
*/    
    @Override
    public void pbDevolucaoVeiculo(Long pPbuId, Date pPpckDtPromdev, String pPpckDescricao, Integer pUsuId) throws Exception {
        AnaliseFacade analiseJpa = new AnaliseFacade();
        PedbusFacade pedbusJpa = new PedbusFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        RecebimentoFacade recebimentoJpa = new RecebimentoFacade();
        
        checkpointJpa.setEm(em);
        ckptipoJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        usuarioJpa.setEm(em);
        recebimentoJpa.setEm(em);
        agenteJpa.setEm(em);
        pedbusJpa.setEm(em);
        
        logger.log(Level.INFO, "pbGerarPromessaDevolucao: {0}", pPbuId);
        
        Date dtsys = new Date(System.currentTimeMillis());        
        
        Analise analise = analiseJpa.findByPbuId(pPbuId);
        Pedbus pedbus = pedbusJpa.findByPbuId(pPbuId);
        Agente agente = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);
        
        Checkpoint checkpointPromDev = checkpointJpa.findPromessaDevolucaoAtiva(analise);

        try {
            Checkpoint checkpointVeicDevAtivo = checkpointJpa.findVeiculoDevolvidoAtiva(analise);
            checkpointVeicDevAtivo.setPckAtivo((short)0);
            checkpointJpa.edit(checkpointVeicDevAtivo);
        } catch (NoResultException e) {}
        
        Checkpoint checkpointNew = new Checkpoint();
        checkpointNew.setPbuId(analise);
        checkpointNew.setPckDt(dtsys);
        checkpointNew.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuId));
        checkpointNew.setCktId(ckptipoJpa.findByCktId((short) 15));
        checkpointNew.setPckRelatorio((short) 0);
        checkpointNew.setAgeId(agente);
        checkpointNew.setPckDescricao(pPpckDescricao);
        checkpointNew.setPckDtPromdev(pPpckDtPromdev);
        checkpointNew.setPckAtivo((short)1);
        checkpointNew.setFasId(pedbusfaseJpa.findByFasId((short)2));
        checkpointNew.setPckIdAprovado(checkpointPromDev);
        checkpointNew.setEuiId(pedbus.getEuiId());
        checkpointJpa.create(checkpointNew); 
        
        checkpointPromDev.setPckIdAprovado(checkpointNew);
        checkpointJpa.edit(checkpointPromDev);
    }
    
/*
    
    
+------------------------------------------------------------------------------+
!                      Trocar Agente                            !
+------------------------------------------------------------------------------+
    
    
    
*/    
    
    @Override
    @TransactionAttribute(MANDATORY)
    public void trocarAgente(Long pPbuId, Integer pUsuIdLogin, Short pAgsId, Integer pUsuIdDestino, Short pEstIdInvest, Long pLocNuInvest) throws Exception {
        AnaliseFacade analiseJpa = new AnaliseFacade();
        PedbusFacade pedbusJpa = new PedbusFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        AgentesitFacade agentesitJpa  = new AgentesitFacade();
        ComissaoFacade comissaoJpa = new ComissaoFacade();
        TipopagFacade tipopagJpa = new TipopagFacade();
        PagamentoFacade pagamentoJpa = new PagamentoFacade();
        PedbusitFacade pedbusitJpa = new PedbusitFacade();
        EstadoFacade estadoJpa = new EstadoFacade();
        CepLocalidadeFacade cepLocalidadeJpa = new CepLocalidadeFacade();
//        EquipeFacade equipeJpa = new EquipeFacade();
        
        checkpointJpa.setEm(em);
        ckptipoJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        usuarioJpa.setEm(em);
        agenteJpa.setEm(em);
        agentesitJpa.setEm(em);
        pedbusJpa.setEm(em);
        comissaoJpa.setEm(em);
        tipopagJpa.setEm(em);
        pagamentoJpa.setEm(em);
        pedbusitJpa.setEm(em);
        estadoJpa.setEm(em);
        cepLocalidadeJpa.setEm(em);
//        equipeJpa.setEm(em);
        
        Analise analise = analiseJpa.findByPbuId(pPbuId);
        Pedbus pedbus = pedbusJpa.findByPbuId(pPbuId);

        Date dtsys = new Date(System.currentTimeMillis());
        
        Agente agenteAtivo = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);
        
        
        Checkpoint checkpoint = new Checkpoint();        
        Usuario usuarioAtivo = usuarioJpa.findByUsuId(agenteAtivo.getUsuId().getUsuId());
        Usuario usuarioDestino = usuarioJpa.findByUsuId(pUsuIdDestino);
        Usuario usuarioLogin = usuarioJpa.findByUsuId(pUsuIdLogin);
            
        String descricao = "Nova designação de agente. Agente Antigo: " 
                            + usuarioAtivo.getUsuNome()
                            + " Novo agente: " 
                            + usuarioDestino.getUsuNome();
//        Date dataSys = new Date(System.currentTimeMillis());
        
        checkpoint.setUsuIdCkp(usuarioLogin);
        checkpoint.setPckDt(dtsys);
        checkpoint.setPbuId(analise);
        checkpoint.setCktId(ckptipoJpa.findByCktId(Short.parseShort("8")));
        checkpoint.setPckDescricao(descricao);
        checkpoint.setUsuIdOrigem(usuarioAtivo);
        checkpoint.setUsuIdDestino(usuarioDestino);
        checkpoint.setFasId(pedbusfaseJpa.findByFasId((short) 2));
        checkpoint.setPckAtivo((short) 1);
        checkpoint.setAgeId(agenteAtivo);
        checkpoint.setPckRelatorio((short) 0);
        checkpoint.setEuiId(pedbus.getEuiId());
        checkpointJpa.create(checkpoint);
        
        Agente agenteNew = new Agente();
        
        agenteNew.setPbuId(pedbus);
        agenteNew.setAgeDtDesignacao(dtsys);
        agenteNew.setUsuId(usuarioDestino);
        agenteNew.setPbuId(pedbus);
        agenteNew.setAgsId(agentesitJpa.findByAgsId(Short.parseShort("2")));        
        agenteJpa.create(agenteNew);
            
        if (pAgsId == 3) {
            Checkpoint checkpointInvestigado = new Checkpoint();
            checkpointInvestigado.setUsuIdCkp(usuarioLogin);
            checkpointInvestigado.setPckDt(dtsys);
            checkpointInvestigado.setPbuId(analise);
            checkpointInvestigado.setCktId(ckptipoJpa.findByCktId(Short.parseShort("3")));
            checkpointInvestigado.setPckDescricao(null);
            checkpointInvestigado.setUsuIdOrigem(usuarioAtivo);
            checkpointInvestigado.setUsuIdDestino(usuarioDestino);
            checkpointInvestigado.setFasId(pedbusfaseJpa.findByFasId((short) 2));            
            checkpointInvestigado.setAgeId(agenteAtivo);
            checkpointInvestigado.setPckAtivo((short) 1);
            checkpointInvestigado.setPckAprovado((short) 0);
            checkpointInvestigado.setPckRecusado((short) 0);
            checkpointInvestigado.setPckRelatorio((short) 0);
            checkpointInvestigado.setPckAprovadoms((short) 1);
            checkpointInvestigado.setPckRelatoriofeito((short) 0);            
            checkpointJpa.create(checkpointInvestigado);           

            Pagamento  pagamento = new Pagamento();
            pagamento.setAgeId(agenteAtivo);
            pagamento.setPagRelatorio((short) 0);
            pagamento.setPagDtCriacao(dtsys);
            pagamento.setPbuId(pedbus);
            pagamento.setPckId(checkpointInvestigado);
            Comissao comissao;
            if (agenteAtivo.getUsuId().getComId() == null) {
                comissao = comissaoJpa.findByComId(1);
            } else {
                comissao = comissaoJpa.findByComId(agenteAtivo.getUsuId().getComId().getComId());
            }
            pagamento.setPagDtPrevisao(null);
            pagamento.setPagValor(comissao.getComInvestigacao());
            pagamento.setTppId(tipopagJpa.findByTppId((short)2));
            pagamento.setPagAtivo((short) 1);
            pagamentoJpa.create(pagamento);
            agenteAtivo.setPckId(checkpointInvestigado);
            agenteAtivo.setAgsId(agentesitJpa.findByAgsId(pAgsId));
            agenteJpa.edit(agenteAtivo);
        } else {
            agenteAtivo.setAgsId(agentesitJpa.findByAgsId(pAgsId));
            agenteJpa.edit(agenteAtivo);
        }
        pedbus.setPbuDtJanela(dtsys);
        pedbus.setPbsId(pedbusitJpa.findByPbsId(Short.parseShort("4")));
        pedbus.setEstIdInvest(estadoJpa.findByEstId(pEstIdInvest));
        CepLocalidade cepLocalidade = cepLocalidadeJpa.findByLocNu(pLocNuInvest);
        pedbus.setLocNuInvest(cepLocalidade);
        pedbus.setPbuInvestCidade(cepLocalidade.getLocNo());        
        pedbusJpa.edit(pedbus);        
    }
    


/*
    
    
+------------------------------------------------------------------------------+
!                      Gravar o checkpoint do Mobile                           !
+------------------------------------------------------------------------------+
    
    
    
*/    
    @Override
    @TransactionAttribute(MANDATORY)
    public Long gravarCheckpointMobile(String Operacao, 
                                            Long pPckId, 
                                            String pPckDescricao,
                                            String pPckLegendaFoto,
                                            short pCkpId, 
                                            short pPusId,
                                            short pEstIdRecup,
                                            Long pLocNuRecup,
                                            Integer pUsuIdLogin,
                                            Long pPbuId) {
/*
Operação: INSERT
          UPDATE 
*/        
        
        AnaliseFacade analiseJpa = new AnaliseFacade();
        PedbusFacade pedbusJpa = new PedbusFacade();
        UsuarioFacade usuarioJpa = new UsuarioFacade();
        CkptipoFacade ckptipoJpa = new CkptipoFacade();
        PedbusfaseFacade pedbusfaseJpa = new PedbusfaseFacade();
        AgenteFacade agenteJpa = new AgenteFacade();
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        AgentesitFacade agentesitJpa  = new AgentesitFacade();
        ComissaoFacade comissaoJpa = new ComissaoFacade();
        TipopagFacade tipopagJpa = new TipopagFacade();
        PagamentoFacade pagamentoJpa = new PagamentoFacade();
        PedbusitFacade pedbusitJpa = new PedbusitFacade();
        ClienteFacade clienteJpa = new ClienteFacade();
        RecebimentoFacade recebimentoJpa = new RecebimentoFacade();
        ClientecobrancaFacade clientecobrancaJpa = new ClientecobrancaFacade();
        PbsubstatusFacade pbsubstatusJpa = new PbsubstatusFacade();
        CepLocalidadeFacade cepLocalidadeJpa = new CepLocalidadeFacade();
        EstadoFacade estadoJpa = new EstadoFacade();
        VwPagrecInvestigadoFacade vwPagrecInvestigadoJpa = new VwPagrecInvestigadoFacade();
        
        
        
        checkpointJpa.setEm(em);
        ckptipoJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusfaseJpa.setEm(em);
        usuarioJpa.setEm(em);
        agenteJpa.setEm(em);
        agentesitJpa.setEm(em);
        pedbusJpa.setEm(em);
        comissaoJpa.setEm(em);
        tipopagJpa.setEm(em);
        pagamentoJpa.setEm(em);
        pedbusitJpa.setEm(em);
        clienteJpa.setEm(em);
        recebimentoJpa.setEm(em);
        clientecobrancaJpa.setEm(em);
        pbsubstatusJpa.setEm(em);
        cepLocalidadeJpa.setEm(em);
        estadoJpa.setEm(em);
        vwPagrecInvestigadoJpa.setEm(em);
        
        Analise analise = analiseJpa.findByPbuId(pPbuId);
        Pedbus pedbus = pedbusJpa.findByPbuId(pPbuId);
        
        Checkpoint checkpoint = null;
        Agente agenteAtivo = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);
        Date dtsys = new Date(System.currentTimeMillis());
        
        if (Operacao.equals("UPDATE")) {
             checkpoint = checkpointJpa.findByPckId(pPckId);
             checkpoint.setPckDescricao(pPckDescricao);
             checkpoint.setPckLegendaFoto(pPckLegendaFoto);             
             checkpointJpa.edit(checkpoint);
        } else if (Operacao.equals("INSERT")) {
            checkpoint = new Checkpoint();
            checkpoint.setPckDt(dtsys);
            checkpoint.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuIdLogin));
            checkpoint.setPckRelatorio((short)0);
            checkpoint.setCktId(ckptipoJpa.findByCktId(pCkpId));
            checkpoint.setPbuId(analise);
            checkpoint.setPckAtivo((short) 1);
            checkpoint.setPckDescricao(pPckDescricao);
            checkpoint.setPckLegendaFoto(pPckLegendaFoto);
            checkpoint.setAgeId(agenteAtivo);
            checkpoint.setFasId(pedbusfaseJpa.findByFasId((short) 2));
            checkpointJpa.create(checkpoint);    
        }
        
        if (pCkpId == 2) {
            pedbus.setPusId(pbsubstatusJpa.findPusId(pPusId));
            pedbusJpa.edit(pedbus);
            return checkpoint.getPckId();
        }
        
        CepLocalidade cepLocalidade = cepLocalidadeJpa.findByLocNu(pLocNuRecup);
        checkpoint.setLocNuRecup(cepLocalidade);
        checkpoint.setPckRecupCidade(cepLocalidade.getLocNo());
        checkpoint.setEstIdRecup(estadoJpa.findByEstId(pEstIdRecup));
        checkpoint.setPckAprovado((short)0);
        checkpoint.setPckRecusado((short)0);
        checkpoint.setPckAprovadoms((short)0);
        checkpoint.setPckRelatoriofeito((short)0);
        
        agenteAtivo.setPckId(checkpoint);
        agenteJpa.edit(agenteAtivo);
        
        
        Tipopag tipopag = null;
        
        if (pCkpId == 3) {
            tipopag = tipopagJpa.findByTppId(Short.parseShort("2"));
        } else if (pCkpId == 4) {
            tipopag = tipopagJpa.findByTppId(Short.parseShort("1"));
        }
        
        List<VwPagrecInvestigado> vwPagrecInvestigado = vwPagrecInvestigadoJpa.findByPbuId(pPbuId);
        Long pagIdInvest = null;
        Long rcbIdInvest = null;
        if (!vwPagrecInvestigado.isEmpty()) {
            pagIdInvest = vwPagrecInvestigado.get(0).getPagId();
            rcbIdInvest = vwPagrecInvestigado.get(0).getRcbId();
        }
            
        Pagamento pagamento = new Pagamento();
        pagamento.setAgeId(agenteAtivo);
        pagamento.setPagRelatorio((short) 0);
        pagamento.setPagDtCriacao(dtsys);
        pagamento.setPbuId(pedbus);
        pagamento.setPckId(checkpoint);
        if (!(pagIdInvest == null)) {
            pagamento.setPagIdInvest(pagamentoJpa.findByPagId(pagIdInvest));
        }
        Comissao comissao;
        if (agenteAtivo.getUsuId().getComId() == null) {
            comissao = comissaoJpa.findByComId(1);
        } else {
            comissao = comissaoJpa.findByComId(agenteAtivo.getUsuId().getComId().getComId());
        }
        if (pCkpId == 3) {
            pagamento.setPagValor(comissao.getComInvestigacao());
        } else if (pCkpId == 4) {
            pagamento.setPagValor(comissao.getComRecuperacao());
        }
        pagamento.setTppId(tipopag);
        pagamento.setPagAtivo((short)1);
        pagamentoJpa.create(pagamento);
                
        Cliente cliente = clienteJpa.findByCliId(analise.getCliId().getCliId());
                
        Recebimento recebimento = new Recebimento();
        recebimento.setPbuId(pedbus);
        recebimento.setRcbDt(pagamento.getPagDtCriacao());
        recebimento.setRcbRelatorio((short) 0);
        recebimento.setPckId(checkpoint);           
        recebimento.setTppId(tipopag);
        if (!(rcbIdInvest == null)) {
            recebimento.setRcbIdInvest(recebimentoJpa.findByRcbId(rcbIdInvest));
        }
        try {
            Clientecobranca clientecobranca = clientecobrancaJpa.findByCliIdClbDt(cliente);                
            if (pCkpId == 3) {
                recebimento.setRcbValor(clientecobranca.getClbVlrInvestigacao());
            } else if (pCkpId == 4) {
                recebimento.setRcbValor(clientecobranca.getClbVlrRecuperacao());
            }
            if (analise.getAnaRastreado() == 1) {
                if (analise.getAnaRastreadorRemovido() == 0) {
                    tipopag = tipopagJpa.findByTppId(Short.parseShort("3"));
                    recebimento.setRcbValor(clientecobranca.getClbVlrRastreado());
                    recebimento.setTppId(tipopag);
                }
            }
        } catch (NoResultException e) {
            if (analise.getAnaRastreado() == 1) {
                if (analise.getAnaRastreadorRemovido() == 0) {
                    tipopag = tipopagJpa.findByTppId(Short.parseShort("3"));
                    recebimento.setTppId(tipopag);
                }
            }
            recebimento.setRcbValor(BigDecimal.valueOf(0));
        }
        recebimentoJpa.create(recebimento);
        
        switch (pCkpId) {
            case 2: 
                pedbusJpa.edit(pedbus);
                break;
            case 3: 
                pedbus.setPbsId(pedbusitJpa.findByPbsId(Short.parseShort("2")));
                pedbus.setPusId(pbsubstatusJpa.findPusId((short)5));
                pedbusJpa.edit(pedbus);
                break;
            case 4: 
                pedbus.setPbsId(pedbusitJpa.findByPbsId(Short.parseShort("3")));
                pedbus.setPusId(pbsubstatusJpa.findPusId((short)5));
                pedbusJpa.edit(pedbus);
                break;
        }
        
        
        if (!(pedbus.getPbsIdPre() == null)) {
            if (pedbus.getPbsId().getPbsId().equals(pedbus.getPbsIdPre().getPbsId())) {
                aprovarMudancaStatus(pedbus.getPbuId(), checkpoint.getPckId(), pUsuIdLogin , "AUTO");
            }
        }
        return checkpoint.getPckId();
    }    

    public String diretorioPb (Long pbuId, Date pbuDt, String subdir) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return "/webserver/pb/" + dateFormat.format(pbuDt) + "/" + pbuId + "/" + subdir + "/";
    }
    
    
    
    
}
