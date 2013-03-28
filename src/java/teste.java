
import br.com.finance.model.Usuario;
import br.com.finance.util.HibernateUtil;
import java.text.ParseException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel
 */
public class teste {

    public static void main(String [] args) throws ParseException{
        System.out.println("Session: "+HibernateUtil.getSession().isOpen());
        System.out.println("SessionFactory: "+HibernateUtil.getSessionFactory().isClosed());
        Usuario us = new Usuario();
        us.idadeUser();
    }
}
