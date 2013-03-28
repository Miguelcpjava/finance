package br.com.finance.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Miguel
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory  = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration  cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            return cfg.buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Criação inicial do objeto SessionFactory falhou! Erro: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession(){
        Session SessionToReturn = sessionFactory.openSession();
        SessionToReturn.beginTransaction();
        return SessionToReturn;

    }
    
}
