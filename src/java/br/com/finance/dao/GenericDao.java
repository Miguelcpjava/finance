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

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class GenericDao {

    protected Session session;

    protected Session getSesseion() {
        return HibernateUtil.getSession();
    }

    protected void savingPojo(Serializable pojo) {
        Session sessao = getSesseion();
        sessao.saveOrUpdate(pojo);
        sessao.beginTransaction().commit();
        sessao.close();
    }

    protected void updatePojo(Serializable pojo) {
        this.session = getSesseion();
        this.session.beginTransaction();
        this.session.update(pojo);
        this.session.flush();
        this.session.getTransaction().commit();
    }

    protected <T extends Serializable> T gettingPojo(Class<T> Searchclass, Serializable key) {
        Session sessao = getSesseion();
        Serializable ReturnToObject = (Serializable) sessao.get(Searchclass, key);
        sessao.beginTransaction().commit();
        sessao.close();
        return (T) ReturnToObject;
    }

    protected void removePojo(Serializable pojotoRemove) {
        Session sessao = getSesseion();
        sessao.delete(pojotoRemove);
        sessao.beginTransaction().commit();
        sessao.close();
    }

    protected Serializable getCleanPojo(String query, Object[] params) {
        Session sessao = getSesseion();

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
        Session sessao = getSesseion();

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
        Session sessao = getSesseion();

        Query qr = sessao.createSQLQuery(query);

        Object returnObject = qr.uniqueResult();

        sessao.getTransaction().commit();
        sessao.close();
        return (Serializable) returnObject;
    }

    protected <T extends Serializable> List<T> getCleanList(Class<T> classToCast, String query, Object[] params) {
        Session sessao = getSesseion();

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
        Session sessao = getSesseion();

        Query qr = sessao.createQuery(query);

        List returnObject = qr.list();

        sessao.getTransaction().commit();
        sessao.close();
        return returnObject;
    }

    protected <T extends Serializable> List<T> getCleanListOfObjectsSQL(String query) {
        Session sessao = getSesseion();

        Query qr = sessao.createSQLQuery(query);

        List returnObject = qr.list();

        sessao.getTransaction().commit();
        sessao.close();
        return returnObject;
    }

    public <T extends Serializable> T SearchObejtct(String query) {
        Session sessao = getSesseion();
        Query q = sessao.createSQLQuery(query);
        sessao.getTransaction().commit();
        sessao.close();

        return (T) q.uniqueResult();
    }
}
