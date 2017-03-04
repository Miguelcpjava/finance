/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class GenericDao {

    protected Session session;

    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    protected void savingPojo(Serializable pojo)  {
      Session sessao = getSession();
        try {
            sessao.save(pojo);
            sessao.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            sessao.getTransaction().rollback();
        } 
        finally {
            sessao.flush();
            sessao.close();
        }
    }

    
    protected void updatePojo(Serializable pojo) {
       Session sessao = getSession();
        try {
            sessao.getTransaction().begin();
            sessao.update(pojo);
            sessao.flush();
            sessao.getTransaction().commit();
        }catch(Exception e){
            sessao.getTransaction().rollback();
        }  finally {
            sessao.flush();
            sessao.close();
        }
    }

    protected <T extends Serializable> T gettingPojo(Class<T> Searchclass, Serializable key) {
        Session sessao = getSession();
        Serializable ReturnToObject = null;
        try {
            //sessao.getTransaction().begin();
            ReturnToObject = (Serializable) sessao.get(Searchclass, key);
            //sessao.beginTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.flush();
            sessao.close();
        }
        return (T) ReturnToObject;
    }

    protected void removePojo(Serializable pojotoRemove) {
       Session sessao = getSession();
        try {
            sessao.getTransaction().begin();
            sessao.delete(pojotoRemove);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            sessao.getTransaction().rollback();
        }  finally {
            sessao.flush();
            sessao.close();
        }
    }

    protected Serializable getCleanPojo(String query, Object[] params) {
        Session sessao = getSession();

        Query qr = sessao.createQuery(query);

        for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i, params[(i - 1)]);
        }

        Object returnObject = qr.uniqueResult();

        sessao.getTransaction().commit();
        sessao.close();
        return (Serializable) returnObject;
    }

    protected Serializable getCleanPojoSQLQuery(String query, Object[] params) {
        Session sessao = getSession();

        Query qr = sessao.createSQLQuery(query);

        for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i, params[(i - 1)]);
        }

        Object returnObject = qr.uniqueResult();

        sessao.getTransaction().commit();
        sessao.close();
        return (Serializable) returnObject;
    }

    protected Serializable getCleanPojoSQLQuery(String query) {
        Session sessao = getSession();

        Query qr = sessao.createSQLQuery(query);

        Object returnObject = qr.uniqueResult();

        sessao.getTransaction().commit();
        sessao.close();
        return (Serializable) returnObject;
    }

    protected <T extends Serializable> List<T> getCleanList(Class<T> classToCast, String query, Object[] params) {
        Session sessao = getSession();

        Query qr = sessao.createQuery(query);

        for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i, params[(i - 1)]);
        }

        List returnObject = qr.list();

        sessao.getTransaction().commit();
        sessao.close();
        return returnObject;
    }

    protected <T extends Serializable> List<T> getCleanListOfObjects(Class<T> classToCast, String query) {
        Session sessao = getSession();

        Query qr = sessao.createQuery(query);

        List returnObject = qr.list();

        sessao.getTransaction().commit();
        sessao.close();
        return returnObject;
    }

    protected <T extends Serializable> List<T> getCleanListOfObjectsSQL(String query) {
        Session sessao = getSession();

        Query qr = sessao.createSQLQuery(query);

        List returnObject = qr.list();

        sessao.getTransaction().commit();
        sessao.close();
        return returnObject;
    }

    public <T extends Serializable> T SearchObejtct(String query) {
        Session sessao = getSession();
        Query q = sessao.createSQLQuery(query);
        sessao.getTransaction().commit();
        sessao.close();

        return (T) q.uniqueResult();
    }
}
