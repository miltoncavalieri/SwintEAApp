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
import br.com.zaiac.swinteaapp.entities.Pedbusrel;
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
import br.com.zaiac.swinteaapp.facade.LocrecupFacade;
import br.com.zaiac.swinteaapp.facade.LocrecuplocFacade;
import br.com.zaiac.swinteaapp.facade.OperacaoFacade;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.MANDATORY;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

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
        
//
//        Se for Recuperado nao permitir voltar para campo
//
        
        if (vwPedbusPagoRecebido.getPbsId() == 3) {
            if (vwPedbusPagoRecebido.getLcbId() != null) {
                logger.log(Level.SEVERE, "Operacao nao permitida para PB ja recebido/recuperado: {0} / {1}", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
                throw new Exception("Operacao nao permitida para PB ja recebido: "+ vwPedbusPagoRecebido.getPbuId() + "/" + checkpointParaVoltar.getPckId());
            }
            
            if (vwPedbusPagoRecebido.getLopId()!= null) {
                logger.log(Level.SEVERE, "Operacao nao permitida para PB ja pago: {0} / {1}", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
                throw new Exception("Operacao nao permitida para PB ja pago: "+ vwPedbusPagoRecebido.getPbuId() + "/" + checkpointParaVoltar.getPckId());
            }
        }
        
//
//        Nao voltar para campo se ele ja estiver em campo
//
        

        if (vwPedbusPagoRecebido.getPbsId() == 1) {
            logger.log(Level.SEVERE, "Operacao nao permitida para PB em campo: {0} / {1}", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
            throw new Exception("Operacao nao permitida para PB em campo: "+ vwPedbusPagoRecebido.getPbuId()+ "/" + checkpointParaVoltar.getPckId());
        }

        Agente agente = agenteJpa.findByPbuIdAgsIdAtivo(checkpointParaVoltar.getAgeId().getPbuId());
        Agente agenteNew = null;
        Integer ret;

        if ((vwPedbusPagoRecebido.getPbsId() == 2)) {
//            if ((vwPedbusPagoRecebido.getLcbId() != null) && (vwPedbusPagoRecebido.getLopId() != null)) {        
//                logger.log(Level.SEVERE, "Operacao nao permitida para PB Pago/Recedigo: {0} / {1}", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
//                throw new Exception("Operacao nao permitida para PB ja pago e recebido: "+ vwPedbusPagoRecebido.getPbuId() + "/" + checkpointParaVoltar.getPckId());
//            }
            if ((vwPedbusPagoRecebido.getLcbId() != null) && (vwPedbusPagoRecebido.getLopId() == null)) { 
                logger.log(Level.SEVERE, "Operacao nao permitida para PB em andamento para recebimento do cliente: {0} / {1}. Remova este recebimento para voltar para campo", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
                throw new Exception("Operacao nao permitida para PB em andamento para recebimento do cliente: "+ vwPedbusPagoRecebido.getPbuId() + "/" + checkpointParaVoltar.getPckId() + ". Remova este recebimento para voltar para campo");
            }

            if ((vwPedbusPagoRecebido.getLopId() != null) && (vwPedbusPagoRecebido.getLcbId() == null)) {        
                logger.log(Level.SEVERE, "Operacao nao permitida para PB em andamento para pagamento para o agente: {0} / {1}. Remova este pagamento para voltar para campo", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
                throw new Exception("Operacao nao permitida para PB em andamento para pagamento para o agente: "+ vwPedbusPagoRecebido.getPbuId() + "/" + checkpointParaVoltar.getPckId() + ". Remova este pagamento para voltar para campo");
            }
            if ((vwPedbusPagoRecebido.getLopId() != null) && (vwPedbusPagoRecebido.getLcbId() != null)) {        
                if ((vwPedbusPagoRecebido.getLcbDtPagto() == null) || (vwPedbusPagoRecebido.getLopDtPagto() == null)) {
                    logger.log(Level.SEVERE, "Operacao nao permitida para PB ja alocado para pagamento e recebimento: {0} / {1}. Remova o pagamento e recebimento para voltar para campo", new Object[]{checkpointParaVoltar.getPbuId().getPbuId(), checkpointParaVoltar.getPckId()});
                    throw new Exception("Operacao nao permitida para PB ja alocado para pagamento e recebimento: "+ vwPedbusPagoRecebido.getPbuId() + "/" + checkpointParaVoltar.getPckId() + ". Remova o pagamento e recebimento para voltar para campo ou de baixa no pagamento/recebimento.");
                }
            }
        }
        
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
        } else {
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
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);        
        calendar.set(Calendar.SECOND, 0);        
        calendar.set(Calendar.MINUTE, 0);        
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        
//        Calendar cPpckDtPromdev = Calendar.getInstance();
//        cPpckDtPromdev.setTime(pPpckDtPromdev);
        
        Date dtsys = calendar.getTime();
        
//        System.out.println("dtsys " + dtsys);
//        System.out.println("pPpckDtPromdev " + pPpckDtPromdev);
        
        if (!((pPpckDtPromdev.after(dtsys)) || pPpckDtPromdev.compareTo(dtsys) == 0)) {
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
        
        try {
            Checkpoint checkpointPromDev = checkpointJpa.findPromessaDevolucaoAtiva(analise);
            try {
                Checkpoint checkpointVeicDevAtivo = checkpointJpa.findVeiculoDevolvidoAtiva(analise);
            } catch (NoResultException e) {
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
            
        } catch (NoResultException e) {
            
        }

    }
    
/*
    
    
+------------------------------------------------------------------------------+
!                      Trocar Agente                            !
+------------------------------------------------------------------------------+
    
    
    
*/    
    
    @Override
    @TransactionAttribute(MANDATORY)
    public void trocarAgente(Long pPbuId, Integer pUsuIdLogin, Short pAgsId, Integer pUsuIdDestino, Short pEstIdInvest, Long pLocNuInvest, Integer pOpeId) throws Exception {
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
        OperacaoFacade operacaoJpa = new OperacaoFacade();
        
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
        operacaoJpa.setEm(em);
//        equipeJpa.setEm(em);

        
        Analise analise = analiseJpa.findByPbuId(pPbuId);
        Pedbus pedbus = pedbusJpa.findByPbuId(pPbuId);

        Date dtsys = new Date(System.currentTimeMillis());
        
        Agente agenteAtivo = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);

        if (agenteJpa.findAgenteAtivo(pedbusJpa.findByPbuId(pPbuId), usuarioJpa.findByUsuId(pUsuIdDestino))) {
            if (pAgsId == 3) {
                throw new Exception("Para este PB o agente já recebeu Investigacao");
            }
        }
        
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
        pedbus.setOpeId(operacaoJpa.findByOpeId(pOpeId));
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
                                            Short pFasId,
                                            Long pPckId, 
                                            String pPckDescricao,
                                            String pPckLegendaFoto,
                                            Short pCkpId, 
                                            Short pPusId,
                                            Short pEstIdRecup,
                                            Long pLocNuRecup,
                                            Integer pUsuIdLogin,
                                            Long pPbuId,
                                            String pBatchOperacao,
                                            String pExtFilename,
                                            String pPckImagem,
                                            Integer pLprId,
                                            Integer pLrlId,
                                            Boolean pGerarRelatorio) throws Exception {
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
        LocrecupFacade locrecupJpa = new LocrecupFacade();
        LocrecuplocFacade locrecuplocJpa = new LocrecuplocFacade();
        PedbusrelFacade pedbusrelJpa = new PedbusrelFacade();
        
        
        Pedbus pedbus = null;
        Analise analise = null;
        Agente agenteAtivo = null;
        
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
        locrecupJpa.setEm(em);
        locrecuplocJpa.setEm(em);
        pedbusrelJpa.setEm(em);
        
        
        analise = analiseJpa.findByPbuId(pPbuId);
        if (pFasId == 2) {
            pedbus = pedbusJpa.findByPbuId(pPbuId);
            agenteAtivo = agenteJpa.findByPbuIdAgsIdAtivo(pedbus);
        }
        try {

        } catch (NoResultException e) {
            
        }
        
        Checkpoint checkpoint = null;
        Date dtsys = new Date(System.currentTimeMillis());
        
        try {
            pBatchOperacao = pBatchOperacao.toUpperCase();
            if (!("SN".contains(pBatchOperacao))) {
                throw new Exception("Insercao em Batch para Operacao deverá ser S/N");
            }
            if (pFasId == 1) {
                pBatchOperacao = "N";
            }
        } catch (NullPointerException e) {
            pBatchOperacao = "N";
        }
        if (!(pedbus ==null)) {
            if (pedbus.getOpeId() == null) {
                throw new Exception("Insercao em Batch para Operacao a Operação deverá ser definida no Pedido de Busca");
            }
        }
        
        if (Operacao.equals("UPDATE")) {
            checkpoint = checkpointJpa.findByPckId(pPckId);             
            String pckImagem = checkpoint.getPckImagem();
            Short pckRelatorio = checkpoint.getPckRelatorio();
            
            if (!(pPckImagem == null)) {
                if (pPckImagem.length() > 0) {
                    String diretorio = "/webserver/images/mobile/";
                    NumberFormat format = new DecimalFormat("0000000000"); 
                    pExtFilename = pExtFilename.toLowerCase();
                
                    InputStream is = ImageMgmt.encodeBase64ToImage(pPckImagem);
                    BufferedImage im = ImageIO.read(is);
                    
                    if (checkpoint.getPckImagem() == null) {
                        if (checkpoint.getPckIdAprovado() == null) {
                            ImageIO.write(im, pExtFilename, new File(diretorio,  format.format(checkpoint.getPckId()) + "." + pExtFilename));
                            pckImagem = format.format(checkpoint.getPckId()) + "." + pExtFilename;
                        } else {
                            ImageIO.write(im, pExtFilename, new File(diretorio,  format.format(checkpoint.getPckIdAprovado().getPckId()) + "." + pExtFilename));
                            pckImagem = format.format(checkpoint.getPckIdAprovado().getPckId()) + "." + pExtFilename;
                        }
                    } else {
                        File fil = new File(diretorio, checkpoint.getPckImagem());
                        if (fil.exists()) {
                            fil.delete();
                        }
                        ImageIO.write(im, pExtFilename, new File(diretorio,  checkpoint.getPckImagem()));
                        pckImagem = checkpoint.getPckImagem();
                    }             
                    pckRelatorio = ((short) 1);
                }
            } 
            if (checkpoint.getPckIdAprovado() == null) {
                checkpointJpa.updateBatch(pPckId, pPckDescricao, pPckLegendaFoto, pckImagem, pckRelatorio);
            } else {
                checkpointJpa.updateBatch(checkpoint.getPckIdAprovado().getPckId(), pPckDescricao, pPckLegendaFoto, pckImagem, pckRelatorio);
            }
            
        } else if (Operacao.equals("INSERT")) {
            checkpoint = new Checkpoint();
            Usuario usuarioLogin = usuarioJpa.findByUsuId(pUsuIdLogin);
            checkpoint.setPckDt(dtsys);
            checkpoint.setEuiId((pedbus == null) ? null : pedbus.getEuiId());
            checkpoint.setUsuIdCkp(usuarioLogin);
            checkpoint.setPckRelatorio((short)0);
            checkpoint.setCktId(ckptipoJpa.findByCktId(pCkpId));
            checkpoint.setPbuId(analise);
            checkpoint.setPckAtivo((short) 1);
            checkpoint.setPckDescricao(pPckDescricao);
            if (usuarioLogin.getUsuCoordenador() == 1) {
                checkpoint.setPckDescrCoord(pPckDescricao);
            }
            checkpoint.setPckLegendaFoto(pPckLegendaFoto);
            checkpoint.setAgeId(agenteAtivo);
            checkpoint.setFasId(pedbusfaseJpa.findByFasId(pFasId));
            
            checkpointJpa.create(checkpoint);
            
            if (!(pPckImagem == null)) {
                if (pPckImagem.length() > 0) {
                    String diretorio = "/webserver/images/mobile/";
                    NumberFormat format = new DecimalFormat("0000000000"); 
                    pExtFilename = pExtFilename.toLowerCase();
                
                    InputStream is = ImageMgmt.encodeBase64ToImage(pPckImagem);
                    BufferedImage im = ImageIO.read(is);
                    checkpoint.setPckRelatorio((short) 1);
                    ImageIO.write(im, pExtFilename, new File(diretorio,  format.format(checkpoint.getPckId()) + "." + pExtFilename));
                    checkpoint.setPckImagem(format.format(checkpoint.getPckId()) + "." + pExtFilename);
                    checkpoint.setPckRelatorio((short) 1);
                }
            } else {
                checkpoint.setPckRelatorio((short) 0);
                if (usuarioLogin.getUsuCoordenador() == 1) {
                    checkpoint.setPckRelatorio((short)1);
                }
            }
            checkpointJpa.edit(checkpoint);
        }
        
        if (pCkpId == 2) {
            if (pFasId == 2) {
                pedbus.setPusId(pbsubstatusJpa.findPusId(pPusId));
                pedbusJpa.edit(pedbus);
            
                checkpoint.setOpeId(pedbus.getOpeId());
            }
            
            if (pBatchOperacao.equals("S")) {
                if (Operacao.equals("INSERT")) {
                    List<Analise> analises = analiseJpa.findPbEmCampoPorOperacao(pedbus.getOpeId());
                    for (Analise ana : analises) {
                        if (ana.getPbuId().compareTo(analise.getPbuId()) != 0) {
                            Checkpoint checkpointBatch = new Checkpoint();
                            checkpointBatch.setPckDt(dtsys);
                            checkpointBatch.setUsuIdCkp(usuarioJpa.findByUsuId(pUsuIdLogin));
                            checkpointBatch.setPckRelatorio(checkpoint.getPckRelatorio());
                            checkpointBatch.setCktId(ckptipoJpa.findByCktId(pCkpId));
                            checkpointBatch.setPbuId(ana);
                            checkpointBatch.setPckAtivo((short) 1);
                            checkpointBatch.setPckDescricao(pPckDescricao);
                            checkpointBatch.setPckLegendaFoto(pPckLegendaFoto);
                            checkpointBatch.setAgeId(agenteAtivo);
                            checkpointBatch.setFasId(pedbusfaseJpa.findByFasId(pFasId));
                            checkpointBatch.setOpeId(pedbus.getOpeId());
                            checkpointBatch.setPckIdAprovado(checkpoint);
                            checkpointBatch.setPckImagem(checkpoint.getPckImagem());
                            checkpointJpa.create(checkpointBatch);     
                        }
                    }
                }
            }
            return checkpoint.getPckId();
        }
        
        pbDevolucaoVeiculo(analise.getPbuId(), checkpoint.getPckDt(), "Recuperado/Investigado pelo Agente", pUsuIdLogin);

        CepLocalidade cepLocalidade = cepLocalidadeJpa.findByLocNu(pLocNuRecup);
        checkpoint.setLocNuRecup(cepLocalidade);
        checkpoint.setPckRecupCidade(cepLocalidade.getLocNo());
        checkpoint.setEstIdRecup(estadoJpa.findByEstId(pEstIdRecup));
        
        if (!(pLprId == null)) {            
            checkpoint.setLocrecuploc(locrecuplocJpa.findByLrpIdLrlId(pLprId, pLrlId));
        }
        checkpoint.setPckAprovado((short)0);
        checkpoint.setPckRecusado((short)0);
        checkpoint.setPckAprovadoms((short)0);
        checkpoint.setPckRelatoriofeito((short)0);
        
        if (pFasId == 2) {
            agenteAtivo.setPckId(checkpoint);
            agenteJpa.edit(agenteAtivo);
        }
        
               
        
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
                if (analise.getAnaRastreado() == 1) {
                    if (analise.getAnaRastreadorRemovido() == 0) {
                        tipopag = tipopagJpa.findByTppId(Short.parseShort("3"));
                        recebimento.setRcbValor(clientecobranca.getClbVlrRastreado());
                        recebimento.setTppId(tipopag);
                    }
                }
                if (analise.getSitId().getSitId() == 2) {
                    tipopag = tipopagJpa.findByTppId(Short.parseShort("4"));
                    recebimento.setRcbValor(clientecobranca.getClbVlrFurtoroubo());
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
            case 3: 
                pedbus.setPbsId(pedbusitJpa.findByPbsId(Short.parseShort("2")));
                pedbus.setPusId(pbsubstatusJpa.findPusId((short)5));
                break;
            case 4: 
                pedbus.setPbsId(pedbusitJpa.findByPbsId(Short.parseShort("3")));
                pedbus.setPusId(pbsubstatusJpa.findPusId((short)5));
                break;
        }
        
        Boolean isSaveMudancaStatus = false;
        
        if (!(pedbus.getPbsIdPre() == null)) {
            if (pedbus.getPbsId().getPbsId().equals(pedbus.getPbsIdPre().getPbsId())) {
                aprovarMudancaStatus(pedbus.getPbuId(), checkpoint.getPckId(), pUsuIdLogin , "AUTO");
                checkpoint.setPbsIdPre(pedbus.getPbsIdPre());
                checkpoint.setPckDtPre(pedbus.getPbuDtPre());                
                pedbus.setPbsIdPre(null);
                pedbus.setPbuDtPre(null);
                isSaveMudancaStatus = true;
            }
        }
        pedbusJpa.edit(pedbus);     
        
        
        System.out.println("Checkpoint????? : " + checkpoint.getPckId());
        
        
        
        if (pGerarRelatorio) {
            if (!isSaveMudancaStatus) {
                aprovarMudancaStatus(pedbus.getPbuId(), checkpoint.getPckId(), pUsuIdLogin , "AUTO");
            }
            
            Pedbusrel pedbusrel = new Pedbusrel();
            pedbusrel.setPckId(checkpoint.getPckId());
            pedbusrel.setCheckpoint(checkpoint);
            pedbusrelJpa.create(pedbusrel);            
            this.pbGerarRelatorio(checkpoint.getPckId(), pUsuIdLogin, pEstIdRecup, pLocNuRecup);            
            
            this.pbAprovarRelatorio(checkpoint.getPckId(), pUsuIdLogin);
            this.gerarRelatorioCliente(checkpoint.getPckId());
        }
        
        return checkpoint.getPckId();
    }    

    public String diretorioPb (Long pbuId, Date pbuDt, String subdir) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return "/webserver/pb/" + dateFormat.format(pbuDt) + "/" + pbuId + "/" + subdir + "/";
    }
    
    
    @Override
    @TransactionAttribute(MANDATORY)
    public void excluirFotoCheckpointMobile(Long pPckId) throws Exception {
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        checkpointJpa.setEm(em);
        Checkpoint checkpoint = checkpointJpa.findByPckId(pPckId);
        String diretorio = "/webserver/images/mobile/";
        File f = new File(diretorio, checkpoint.getPckImagem());
        if (f.exists()) {
            f.delete();
        }
        checkpointJpa.updateBatchDeleteFoto(pPckId);
    }    
    
    @Override
    @TransactionAttribute(MANDATORY)
    public void excluirCheckpointMobile(Long pPckId) throws Exception {
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        checkpointJpa.setEm(em);
        Checkpoint checkpoint = checkpointJpa.findByPckId(pPckId);
        String diretorio = "/webserver/images/mobile/";
        if (checkpoint.getPckImagem() != null) {
            File f = new File(diretorio, checkpoint.getPckImagem());

            if (f.exists()) {
                f.delete();
            }
        }
        checkpointJpa.deleteBatch(pPckId);
    }    
    
    
    
    @Override
    public void gerarRelatorioCliente (Long pPckId) throws Exception {
        CheckpointFacade checkpointJpa  = new CheckpointFacade();
        checkpointJpa.setEm(em);
        
        Checkpoint checkpoint;
        
        checkpoint = checkpointJpa.findByPckId(pPckId);
        String caminhoOrigem = "/webserver/reports/jasper/";
        
        String dir = FormatValues.diretorioPb(checkpoint.getPbuId().getPbuId(), checkpoint.getPbuId().getAnaDt(), "relatorio");
        FileMgmt.createDirectoryStructure(dir);
//        System.out.println (dir);
        
        String caminhoRelatorio = dir;
        String arquivoJasper = "relatorio.jasper";
        File arquivoGerado = null;
        HashMap mapa = new HashMap();
        
        mapa.clear();
        mapa.put("pbuId",Integer.parseUnsignedInt(checkpoint.getPbuId().getPbuId().toString()));
        mapa.put("pckId",Integer.parseUnsignedInt(checkpoint.getPckId().toString()));
                
        try {
            arquivoGerado = new java.io.File(caminhoRelatorio + checkpoint.getPckId() + ".pdf");
            if (arquivoGerado.exists()) {
                arquivoGerado.delete();
            }
            
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:jboss");
            DataSource ds = (DataSource)envContext.lookup("datasources/SwintDS");
            Connection conn = ds.getConnection();
                    
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(caminhoOrigem + arquivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapa, conn);
            JRPdfExporter jrPdfExporter = new JRPdfExporter();
            jrPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));
            jrPdfExporter.exportReport();
            conn.close();
        } catch (JRException e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    
}
