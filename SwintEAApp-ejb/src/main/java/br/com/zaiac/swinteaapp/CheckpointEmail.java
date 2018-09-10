package br.com.zaiac.swinteaapp;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Pedbus;
import br.com.zaiac.swinteaapp.entities.Analisedoc;
import br.com.zaiac.swinteaapp.entities.Dbparam;
import br.com.zaiac.swinteaapp.entities.Pedbusit;
import br.com.zaiac.swinteaapp.facade.AnaliseFacade;

import br.com.zaiac.swinteaapp.facade.PedbusFacade;
import br.com.zaiac.swinteaapp.facade.AnalisedocFacade;
import br.com.zaiac.swinteaapp.facade.DbparamFacade;
import br.com.zaiac.swinteaapp.facade.PedbusitFacade;

import br.com.zaiac.swinteaapp.facade.VwPbemailtoccFacade;
import br.com.zaiac.swinteaapp.facade.VwPedbusEnvioFacade;
import br.com.zaiac.swinteaapp.views.VwPbemailtocc;
import br.com.zaiac.swinteaapp.views.VwPedbusEnvio;
import javax.ejb.LocalBean;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;


@Stateless
@LocalBean

@Transactional
public class CheckpointEmail {
    private static final Logger logger = Logger.getLogger(CheckpointEmail.class.getName());
    @PersistenceContext(unitName = "SwintService", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Resource(name = "java:jboss/mail/Swint")
    private Session session;
//    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/10", second = "0",year="*", persistent = false)    
    
    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/5", year="*", persistent = false)
//    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/2", year="*", persistent = false)    
    public void enviaEmailAgente() throws NamingException, NoClassDefFoundError {
        logger.log(Level.INFO, "Checking for Send-Email to Agente Versão 1.7");
        
        PedbusFacade pedbusJpa = new PedbusFacade();
        VwPedbusEnvioFacade vwPedbusEnvioJpa = new VwPedbusEnvioFacade();
        VwPbemailtoccFacade vwPbemailtoccJpa = new VwPbemailtoccFacade();
        AnalisedocFacade analisedocJpa = new AnalisedocFacade();
        AnaliseFacade analiseJpa = new AnaliseFacade();
        PedbusitFacade pedbusitJpa = new PedbusitFacade();
        DbparamFacade dbparamJpa = new DbparamFacade();
        
        pedbusJpa.setEm(em);
        vwPedbusEnvioJpa.setEm(em);
        vwPbemailtoccJpa.setEm(em);
        analisedocJpa.setEm(em);
        analiseJpa.setEm(em);
        pedbusitJpa.setEm(em);
        dbparamJpa.setEm(em);
        
        List<VwPedbusEnvio> vwPedbusEnvios;
        Pedbus pedbus;
        Analise analise;
        List<Dbparam> dbparams;
        Dbparam dbparam;
        
        vwPedbusEnvios = vwPedbusEnvioJpa.findAll();
        dbparams = dbparamJpa.findAll();
        dbparam = dbparams.get(0);
        HashMap mapa = new HashMap();
        
        
        String caminhoOrigem = "/webserver/reports/jasper/";
        String caminhoRelatorio = "/webserver/reports/pdf/";
        String caminhoAnaliseDoc = "/webserver/images/analisedoc/";
        String arquivoJasper = "pb.jasper";
        File arquivoGerado = null;
        String linhaHTML;
        Boolean enviado = false;
        int seqdoc = 0;
        
        Pedbusit pedbusit = pedbusitJpa.findByPbsId(Short.parseShort("1"));
        
        try {
            MimeMessage msg = new MimeMessage(session);
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart;
            
            msg.setFrom(dbparam.getParEmailOrigem());
            
//            Analise analise = null;
            for (VwPedbusEnvio pb : vwPedbusEnvios) {
                analise = analiseJpa.findByPbuId(pb.getPbuId());
                pedbus = pedbusJpa.findByPbuId(pb.getPbuId());
                enviado = Boolean.TRUE;
                
                VwPbemailtocc vwPbemailtocc = (VwPbemailtocc) vwPbemailtoccJpa.findByPbuId(pb.getPbuId());
                
                if (vwPbemailtocc.getTo() == null) {
                    continue;
                }
                
//PB_NUMERO_LOCADORA_PLACA_CIDADE_UF                
                
                msg.setFrom("gerencia@zaiac.com.br");
//                String subject = "PB_" + pb.getPbuId() + "_" + pb.getCliNome() + "_" + pb.getAnaVeiculoPlaca() + "_" + pb.getModNome() + "_" + pb.getPbuInvestCidade() + "_" + pb.getEstInvestUf();
                String subject = pb.getReferencia();
                System.out.println(subject);
                
                msg.setSubject(subject);
                msg.setRecipients(Message.RecipientType.TO, vwPbemailtocc.getTo());
                
                if (vwPbemailtocc.getCc() != null) {
                    msg.setRecipients(Message.RecipientType.CC, vwPbemailtocc.getCc());
                } else {
                    msg.setRecipients(Message.RecipientType.CC, "");
                }
                messageBodyPart = new MimeBodyPart();  
                linhaHTML = "<body>"
                          + "<p>Prezado agente <b>" + vwPbemailtocc.getUsuNome() + "</b></p>"
                          + "<p>Estamos enviando o PB <b>" + pb.getPbuId() + "</b> juntamente com a(s) documentacao(oes) necessaria(s)"
                          + " para sua providencia.</p>";
                if (vwPbemailtocc.getUsuCoordenador() != null) {
                    linhaHTML = linhaHTML                          
                              + "<p>O Coodenador <b> " + vwPbemailtocc.getUsuCoordenador() + "</b> Foi copiado neste e-mail."
                              + "</body>";
                } else {
                    linhaHTML = linhaHTML                          
                              + "</body>";
                }
                messageBodyPart.setContent(linhaHTML, "text/html");
                multipart.addBodyPart(messageBodyPart);

                mapa.clear();
//                mapa.put("pbuId", Integer.parseUnsignedInt(pb.getPbuId().toString()));
                mapa.put("pbuId", Integer.parseUnsignedInt(String.valueOf(pb.getPbuId())));
                try {
                    Context initContext = new InitialContext();
                    Context envContext  = (Context)initContext.lookup("java:jboss");
                    DataSource ds = (DataSource)envContext.lookup("datasources/SwintDS");
                    Connection conn = ds.getConnection();
                    
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(caminhoOrigem + arquivoJasper);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapa, conn);
                    JRPdfExporter jrPdfExporter = new JRPdfExporter();
                    arquivoGerado = new java.io.File(caminhoRelatorio + pb.getPbuId() + ".pdf");
                    jrPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));
                    jrPdfExporter.exportReport();
                    
                    conn.close();
                    
                    messageBodyPart = new MimeBodyPart();                    
                    javax.activation.DataSource source = new FileDataSource(caminhoRelatorio + pb.getPbuId() + ".pdf"); //É o nome do arquivo
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName("PB_" + pb.getPbuId() + "_" + pb.getCliNome() + "_" + pb.getAnaVeiculoPlaca() + "_"  + pb.getModNome() + "_" + pb.getPbuInvestCidade() + "_" + pb.getEstInvestUf() + ".pdf"); // É o nome que vai aparecer no Outlook
                    multipart.addBodyPart(messageBodyPart);
                    
                    pedbus.setPbsId(pedbusit);
                    pedbusJpa.edit(pedbus);

                } catch (JRException e) {
                    System.out.println(e.toString());
                } catch (SQLException e) {
                    System.out.println(e.toString());
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                
                List<Analisedoc> analisedocs = analisedocJpa.findByPbuId(analise);
                seqdoc = 1;
                
                for (Analisedoc analisedoc : analisedocs) {
                    if (analisedoc.getAndDocumento() != null) {
                        messageBodyPart = new MimeBodyPart();
                        javax.activation.DataSource source = new FileDataSource(caminhoAnaliseDoc + analisedoc.getAndDocumento());
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(analisedoc.getDocId().getDocNome() + "_" + seqdoc + "." + fileNameExtension(analisedoc.getAndDocumento()));
                        multipart.addBodyPart(messageBodyPart);
                        seqdoc += 1;
                    }

                }

                msg.setContent(multipart);
                Transport.send(msg);
                multipart = null;
                System.gc();
                multipart = new MimeMultipart();
                System.out.println("Sending e-mail to Pedbus ID: " + pb.getPbuId() + " EmailTO: " + vwPbemailtocc.getTo() + " EmailCc: " + vwPbemailtocc.getCc());                
                                
          }
        } catch (MessagingException e) {
//            System.out.println("Sending e-mail to Pedbus ID: " + pb.getPbuId() + " EmailTO: " + vwPbemailtocc.getTo() + " EmailCc: " + vwPbemailtocc.getCc());                
            System.out.println("Failed to send e-mail" + e.toString());
        }
        if (!enviado) {
           System.out.println("There are not e-mail to be send"); 
        }
        vwPedbusEnvios.clear();
        dbparams.clear();
        
        vwPedbusEnvios = null;
        dbparams = null;
        System.gc();
    }
    

    public void businessMethod() {
    }
    
    
    public String fileNameExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }
}




