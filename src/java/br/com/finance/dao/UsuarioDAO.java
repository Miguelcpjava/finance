/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Usuario;
import br.com.finance.util.Criptografia;
import br.com.finance.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miguel
 */

public class UsuarioDAO extends GenericDao implements Serializable{
    private static final long serialVersionUID = -5005808735140804037L;

    /**
     * Creates a new instance of UsuarioDAO
     */
    Criptografia c = new Criptografia();
     String pass;
      String passclean ;
    public UsuarioDAO() {
    }
    
    public int addUsuario(Usuario usuarios){
       savingPojo(usuarios);
        return usuarios.getId();
    }
    
    public void removeUsuario(Usuario usuarios){
        removePojo(usuarios);
    }
    
    public void updateUsuario(Usuario usuarios){
        updatePojo(usuarios);
    }
    
     public Usuario getUsuario(Integer UsuarioID){
        Usuario usuarios = (Usuario)gettingPojo(Usuario.class, UsuarioID);
        return usuarios;
    }
       public List<Usuario> getUsers(){
        return getCleanListOfObjects(Usuario.class,"from Usuario usr");
    }
    public Usuario validarLoginUser(String user, String password) throws Throwable {

        String jsql = "SELECT u FROM Usuario u WHERE u.login=:loginname and u.status='true'";
        try {
            Criteria query = HibernateUtil.getSession().createCriteria(Usuario.class);
            query.add(Restrictions.eq("login",user));
            query.add(Restrictions.eq("password", password));

            List l = query.list();

            if (l == null || l.isEmpty() || l.size() > 1) {
                 FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuário desativado", "Caso queira desativar contacte o admnistrador do Sistema.");
                FacesContext.getCurrentInstance().addMessage("add", msg);
                return null;
            }
            return (Usuario) l.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}
