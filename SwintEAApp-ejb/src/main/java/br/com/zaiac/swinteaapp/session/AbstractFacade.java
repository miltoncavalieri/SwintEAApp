package br.com.zaiac.swinteaapp.session;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.util.List;
import javax.persistence.EntityManager;
import java.sql.SQLException;


public abstract class AbstractFacade<T> {
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) throws MySQLIntegrityConstraintViolationException, SQLException, Exception {
        try 
        {
            getEntityManager().persist(entity);
//            getEntityManager().flush();
        }
        catch (Exception e) 
        {
       	    Throwable th1 = e.getCause();
            Throwable th2 = e.getCause().getCause();
            System.out.println("EXCEPTION CLASS NAME E: " + e.getClass().getName());
	    System.out.println("THROWABLE CLASS NAME E: " + e.getCause().getClass().getName());

            System.out.println("THROWABLE INFO TH1: " + th1.toString());
            System.out.println("EXCEPTION CLASS NAME TH1: " + th1.getClass().getName());
            
            if (th2 == null) {
                switch (th1.getClass().getName()) {
                    case "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException":
                        throw new MySQLIntegrityConstraintViolationException(th1.toString());
                    case "java.sql.SQLException":
                        throw new java.sql.SQLException(th1.toString());
                    case "org.hibernate.id.IdentifierGenerationException":
                        throw new org.hibernate.id.IdentifierGenerationException(th1.toString());
                    default:
                        throw new Exception(th1.toString());
                }
            } else {
                System.out.println("THROWABLE INFO TH2: " + th2.toString());
                System.out.println("EXCEPTION CLASS NAME TH2: " + th2.getClass().getName());
                switch (th2.getClass().getName()) {
                    case "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException":
                        throw new MySQLIntegrityConstraintViolationException(th1.toString());
                    case "java.sql.SQLException":
                        throw new java.sql.SQLException(th1.toString());
                    case "org.hibernate.id.IdentifierGenerationException":
                        throw new org.hibernate.id.IdentifierGenerationException(th1.toString());
                    default:
                        throw new Exception(th2.toString());
                }
            }
        }
    }

    public void edit(T entity) throws MySQLIntegrityConstraintViolationException, SQLException, Exception {
        try
        {
            getEntityManager().merge(entity);
//            getEntityManager().flush();
        }
        catch (Exception e) 
        {
       	    Throwable th1 = e.getCause();
            Throwable th2 = e.getCause().getCause();
            System.out.println("EXCEPTION CLASS NAME E: " + e.getClass().getName());
	    System.out.println("THROWABLE CLASS NAME E: " + e.getCause().getClass().getName());

            System.out.println("THROWABLE INFO TH1: " + th1.toString());
            System.out.println("EXCEPTION CLASS NAME TH1: " + th1.getClass().getName());

            if (th2 == null) {
                switch (th1.getClass().getName()) {
                    case "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException":
                        throw new MySQLIntegrityConstraintViolationException(th1.toString());
                    case "java.sql.SQLException":
                        throw new java.sql.SQLException(th1.toString());
                    case "org.hibernate.id.IdentifierGenerationException":
                        throw new org.hibernate.id.IdentifierGenerationException(th1.toString());
                    default:
                        throw new Exception(th1.toString());
                }
            } else {
                System.out.println("THROWABLE INFO TH2: " + th2.toString());
                System.out.println("EXCEPTION CLASS NAME TH2: " + th2.getClass().getName());
                switch (th2.getClass().getName()) {
                    case "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException":
                        throw new MySQLIntegrityConstraintViolationException(th1.toString());
                    case "java.sql.SQLException":
                        throw new java.sql.SQLException(th1.toString());
                    case "org.hibernate.id.IdentifierGenerationException":
                        throw new org.hibernate.id.IdentifierGenerationException(th1.toString());
                    default:
                        throw new Exception(th2.toString());
                }
            }
        }
    }

public void remove(T entity) throws MySQLIntegrityConstraintViolationException, SQLException, Exception {        
        try 
        {
            getEntityManager().remove(getEntityManager().merge(entity));  
            System.out.println("Delete Entity: " + entity.toString());
//            getEntityManager().flush();
        }

        catch (Exception e) 
        {
            Throwable th1 = e.getCause();
            Throwable th2 = e.getCause().getCause();
            System.out.println("EXCEPTION CLASS NAME E: " + e.getClass().getName());
	    System.out.println("THROWABLE CLASS NAME E: " + e.getCause().getClass().getName());

            System.out.println("THROWABLE INFO TH1: " + th1.toString());
            System.out.println("EXCEPTION CLASS NAME TH1: " + th1.getClass().getName());

            if (th2 == null) {
                switch (th1.getClass().getName()) {
                    case "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException":
                        throw new MySQLIntegrityConstraintViolationException(th1.toString());
                    case "java.sql.SQLException":
                        throw new java.sql.SQLException(th1.toString());
                    case "org.hibernate.id.IdentifierGenerationException":
                        throw new org.hibernate.id.IdentifierGenerationException(th1.toString());
                    default:
                        throw new Exception(th1.toString());
                }
            } else {
                System.out.println("THROWABLE INFO TH2: " + th2.toString());
                System.out.println("EXCEPTION CLASS NAME TH2: " + th2.getClass().getName());
                switch (th2.getClass().getName()) {
                    case "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException":
                        throw new MySQLIntegrityConstraintViolationException(th1.toString());
                    case "java.sql.SQLException":
                        throw new java.sql.SQLException(th1.toString());
                    case "org.hibernate.id.IdentifierGenerationException":
                        throw new org.hibernate.id.IdentifierGenerationException(th1.toString());
                    default:
                        throw new Exception(th2.toString());
                }
            }
        }
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
