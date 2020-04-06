package br.com.zaiac.swinteaapp;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.BEAN;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
//import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.UserTransaction;

@Stateless
@LocalBean
@TransactionManagement(BEAN)
public class EJBMessages implements Serializable{
    
    private static final Logger logger = Logger.getLogger(EJBScheduler.class.getName());
    private static boolean isRunningEnviaEmailAgente = false;

    @PersistenceContext(unitName = "SwintService", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Inject UserTransaction tx;
    
//    @Resource(name = "java:jboss/mail/Swint")
//    private Session session;
    
    @Resource(mappedName = "java:/ConnectionFactory")
    private QueueConnectionFactory connFactory;

    @Resource(mappedName = "java:/jms/queue/SwintQueue")
    private Queue queue;


//    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/10", second = "0",year="*", persistent = false)
//    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/5", year="*", persistent = false)
//    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/2", year="*", persistent = false)
    public void enviaMensagem() {
        try {
            System.out.println("Enviou a mensagem para o WEB");
            Connection connection = connFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("Hello World");
            textMessage.setStringProperty("ID", "1");
            messageProducer.send(textMessage);
            System.out.println("Enviou a mensagem para o WEB (Concluiu)");
            
            messageProducer.close();
            session.close();
            connection.close();
            
        } catch (JMSException ex) {
            Logger.getLogger(EJBMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
}
