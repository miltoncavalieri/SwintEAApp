package br.com.zaiac.swinteaapp;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Clicaso;
import br.com.zaiac.swinteaapp.entities.Clicasocheckpoint;
import br.com.zaiac.swinteaapp.entities.ClicasocheckpointPK;
import br.com.zaiac.swinteaapp.entities.Clicasofase;
import br.com.zaiac.swinteaapp.entities.Clicasolote;
import br.com.zaiac.swinteaapp.facade.AnaliseFacade;
//import br.com.zaiac.swinteaapp.facade.AnalisedocFacade;
import br.com.zaiac.swinteaapp.facade.ClicasoFacade;
import br.com.zaiac.swinteaapp.facade.ClicasocheckpointFacade;
import br.com.zaiac.swinteaapp.facade.ClicasofaseFacade;
import br.com.zaiac.swinteaapp.facade.ClicasoloteFacade;
import br.com.zaiac.swinteaapp.facade.ClicasotipoloteFacade;
import br.com.zaiac.swinteaapp.facade.ClienteFacade;
import br.com.zaiac.swinteaapp.facade.VwClicasoCancelarEnviarLightFacade;
//import br.com.zaiac.swinteaapp.facade.DbparamFacade;
//import br.com.zaiac.swinteaapp.facade.PedbusFacade;
//import br.com.zaiac.swinteaapp.facade.PedbusitFacade;
//import br.com.zaiac.swinteaapp.facade.SysparamFacade;
import br.com.zaiac.swinteaapp.facade.VwClicasoPendenvioFacade;
import br.com.zaiac.swinteaapp.views.VwClicasoCancelarEnviarLight;
//import br.com.zaiac.swinteaapp.facade.VwPbemailtoccFacade;
//import br.com.zaiac.swinteaapp.facade.VwPedbusEnvioFacade;
import br.com.zaiac.swinteaapp.views.VwClicasoPendenvio;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.BEAN;
import javax.inject.Inject;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateless
@LocalBean

@TransactionManagement(BEAN)
public class EJBEnvioIntegracaoPendente {
    
    private static final Logger logger = Logger.getLogger(EJBEnvioIntegracaoPendente.class.getName());
    private static boolean isRunningEnvioPendente = false;

    @PersistenceContext(unitName = "SwintService", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Inject UserTransaction tx;
    
    @Resource(name = "java:jboss/mail/Swint")
    private Session session;
    private final AnaliseFacade analiseJpa = new AnaliseFacade();
    private final VwClicasoPendenvioFacade vwClicasoPendenvioJpa = new VwClicasoPendenvioFacade();
    private final ClicasoFacade clicasoJpa = new ClicasoFacade();
    private final ClicasocheckpointFacade clicasocheckpointJpa = new ClicasocheckpointFacade();
    private final ClicasoloteFacade clicasoloteJpa = new ClicasoloteFacade();
    private final ClicasofaseFacade clicasofaseJpa = new ClicasofaseFacade();
    private final ClicasotipoloteFacade clicasotipoloteJpa = new ClicasotipoloteFacade();
    private final ClienteFacade clienteJpa = new ClienteFacade();
    
//    private final ClicasoloteFacade clicasoloteJpa = new ClicasoloteFacade();
    private final VwClicasoCancelarEnviarLightFacade vwClicasoCancelarEnviarLightJpa = new VwClicasoCancelarEnviarLightFacade();
    

//    @Schedule(dayOfWeek = "*", hour = "22", minute = "00", year="*", persistent = false)
    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/30", year="*", persistent = false)        
//    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/5", year="*", persistent = false)        
    public void envioIntegracaoPendente()    {
        List<VwClicasoPendenvio> vwClicasoPendenvios;
        
        logger.log(Level.INFO, "Checking for Send PB Integration Between SWINT/Customer Version 8.2.0");
        
        if (isRunningEnvioPendente) {
            logger.log(Level.INFO, "-----> Already running");
        }
        
        isRunningEnvioPendente = true;
        
        
        analiseJpa.setEm(em);
        clicasoJpa.setEm(em);
        clicasocheckpointJpa.setEm(em);
        clicasoloteJpa.setEm(em);
        clicasofaseJpa.setEm(em);
        vwClicasoPendenvioJpa.setEm(em);
        vwClicasoCancelarEnviarLightJpa.setEm(em);
        clienteJpa.setEm(em);
        clicasotipoloteJpa.setEm(em);

        vwClicasoPendenvios = vwClicasoPendenvioJpa.findAll();
        Analise analise;

        Clicaso clicaso;
        Clicasolote clicasolote;
        Clicasocheckpoint clicasocheckpoint;
        Clicasofase clicasofase;
        ClicasocheckpointPK clicasocheckpointPK;
        

/* 
   +----------------------------------------------------------------------------------------------+
   |                                                                                              |
   |                                                                                              | 
   |                                                                                              |
   |                   Casos de Integracao de Recuperacoes e Investigacoes                        |
   |                                                                                              | 
   |                                                                                              |                 
   |                                                                                              |
   +----------------------------------------------------------------------------------------------+             
*/                


        
        

        for (VwClicasoPendenvio vw :  vwClicasoPendenvios) {
            try {
                analise = analiseJpa.findByPbuId(vw.getPbuId());
                
                clicaso = clicasoJpa.findByA04IdCaso((Long)vw.getA04IdCaso());
                clicasocheckpoint = clicasocheckpointJpa.findA04IdCasoA05Seq(clicaso.getA04IdCaso(), clicaso.getA05SeqUltimoStatus());
                if (clicasocheckpoint.getClicasofase().getClicasofasePK().getA02Id() != 3) {
                    continue;
                }


/* +----------------------------------------------------------------------------------------------+
   |                                                                                              |
   |                                                                                              | 
   |                                                                                              |
   |                   Colocar aqui o codigo de integracao com o Localiza                         |
   |                                                                                              | 
   |                                                                                              |                 
   |                                                                                              |
   +----------------------------------------------------------------------------------------------+             
*/                
                try {
                    tx.begin();
                    clicasofase = clicasofaseJpa.findByA01IdA02Id((short)4, (short)4);
                    clicasocheckpoint = new Clicasocheckpoint();
                    clicasocheckpointPK = new ClicasocheckpointPK();
                    clicasocheckpoint.setClicaso(clicaso);
                    clicasocheckpointPK.setA04IdCaso(clicaso.getA04IdCaso());
                    clicasocheckpointPK.setA05Seq(clicasocheckpointJpa.findMaxA05Seq(clicaso.getA04IdCaso()));
                    clicasocheckpoint.setClicasocheckpointPK(clicasocheckpointPK);
                    clicasocheckpoint.setClicasofase(clicasofase);
                    clicasocheckpoint.setA05Dt(new Date(System.currentTimeMillis()));
                    clicasocheckpointJpa.create(clicasocheckpoint);
                    
                    
                    clicaso.setA05SeqUltimoStatus(clicasocheckpoint.getClicasocheckpointPK().getA05Seq());
                    clicasoJpa.edit(clicaso);
                    tx.commit();
                } catch (NoResultException e) {
                    logger.log(Level.WARNING, "Problema com o Analise/Caso {1}/{2}", new Object[]{analise.getPbuId().toString(),clicaso.getA04IdCaso().toString()});
                } catch (NotSupportedException | SystemException e) {
                    if (
                        (e instanceof NotSupportedException) ||
                        (e instanceof SystemException) 
                        ) {  
                        logger.log(Level.WARNING, "Problema com o Analise/Caso {1}/{2}", new Object[]{analise.getPbuId().toString(),clicaso.getA04IdCaso().toString()});
                    } else {
                        try {
                            tx.rollback();
                            logger.log(Level.WARNING, "Problema com o Analise/Caso {1}/{2}", new Object[]{analise.getPbuId().toString(),clicaso.getA04IdCaso().toString()});
                        } catch (IllegalStateException | SecurityException | SystemException e1) {
                            logger.log(Level.WARNING, "Problema com o Analise/Caso {1}/{2}", new Object[]{analise.getPbuId().toString(),clicaso.getA04IdCaso().toString()});
                        }
                    }            
                    
                    
                } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException e)  {
                    if (
                        (e instanceof RollbackException) ||     
                        (e instanceof HeuristicMixedException) ||     
                        (e instanceof HeuristicRollbackException)
                        ) {  
                        logger.log(Level.WARNING, "Problema com o Analise/Caso {1}/{2}", new Object[]{analise.getPbuId().toString(),clicaso.getA04IdCaso().toString()});
                    } else {
                        try {
                            tx.rollback();
                            logger.log(Level.WARNING, "Problema com o Analise/Caso {1}/{2}", new Object[]{analise.getPbuId().toString(),clicaso.getA04IdCaso().toString()});
                        } catch (IllegalStateException | SecurityException | SystemException e1) {
                            logger.log(Level.WARNING, "Problema com o Analise/Caso {1}/{2}", new Object[]{analise.getPbuId().toString(),clicaso.getA04IdCaso().toString()});
                        }
                    }            
                }
            } catch (NoResultException e) {
                logger.log(Level.SEVERE, "Problema com o Analise/Caso {1}/{2}", new Object[]{String.valueOf(vw.getPbuId()), String.valueOf(vw.getA04IdCaso())});
            }
        }
        
        
/* 
   +----------------------------------------------------------------------------------------------+
   |                                                                                              |
   |                                                                                              | 
   |                                                                                              |
   |                   Casos de Integracao de Recuperacoes e Investigacoes                        |
   |                                                                                              | 
   |                                                                                              |                 
   |                                                                                              |
   +----------------------------------------------------------------------------------------------+             
*/                
        List<VwClicasoCancelarEnviarLight> vwClicasoCancelarEnviarLights;
        vwClicasoCancelarEnviarLights = vwClicasoCancelarEnviarLightJpa.findAll();
        
        for (VwClicasoCancelarEnviarLight casos : vwClicasoCancelarEnviarLights) {

/* +----------------------------------------------------------------------------------------------+
   |                                                                                              |
   |                                                                                              | 
   |                                                                                              |
   |                   Colocar aqui o codigo de integracao com o Localiza                         |
   |                                                                                              | 
   |                                                                                              |                 
   |                                                                                              |
   +----------------------------------------------------------------------------------------------+             
*/                
            try {
            
                tx.begin();
                clicasolote = clicasoloteJpa.findLoteAberto(clienteJpa.findByCliId(casos.getCliId()), clicasotipoloteJpa.findByA01Id(casos.getA01Id()));
                clicaso = clicasoJpa.findByA04IdCaso(casos.getA04IdCasoCancelar().longValue());

                clicasocheckpoint = new Clicasocheckpoint();
                clicasocheckpointPK = new ClicasocheckpointPK();

                clicasocheckpointPK.setA04IdCaso(clicaso.getA04IdCaso());
                clicasocheckpointPK.setA05Seq(clicasocheckpointJpa.findMaxA05Seq(clicaso.getA04IdCaso()));
                clicasocheckpoint.setClicasocheckpointPK(clicasocheckpointPK);
                clicasocheckpoint.setA05Dt(new Date(System.currentTimeMillis()));                

                clicasocheckpoint.setClicasofase(clicasofaseJpa.findByA01IdA02Id(clicasolote.getA01Id().getA01Id(), (short)(4)));                

                clicasocheckpoint.setClicaso(clicaso);
                clicasocheckpointJpa.create(clicasocheckpoint);
                
                clicaso.setA05SeqUltimoStatus(clicasocheckpoint.getClicasocheckpointPK().getA05Seq());
                clicasoJpa.edit(clicaso);
                
                
                
                tx.commit();
            } catch (Exception e) {
                if (
                    (e instanceof RollbackException) ||     
                    (e instanceof HeuristicMixedException) ||     
                    (e instanceof HeuristicRollbackException)
                    ) {  
                    logger.log(Level.WARNING, "Problema com o Caso/Caso Cliente {1}/{2}", new Object[]{casos.getA04IdCasoCancelar().toString(),casos.getA04IdCasoClienteCancelar().toString()});
                } else {
                    try {
                        tx.rollback();
                        logger.log(Level.WARNING, "Problema com o Caso/Caso Cliente {1}/{2}", new Object[]{casos.getA04IdCasoCancelar().toString(),casos.getA04IdCasoClienteCancelar().toString()});
                    } catch (IllegalStateException | SecurityException | SystemException e1) {
                        logger.log(Level.WARNING, "Problema com o Caso/Caso Cliente {1}/{2}", new Object[]{casos.getA04IdCasoCancelar().toString(),casos.getA04IdCasoClienteCancelar().toString()});
                    }
                }            
            }
            
        }





        isRunningEnvioPendente = false;        
    }
    
}
