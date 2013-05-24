/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Usuario;
import br.com.finance.util.HibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class UsuarioDAO extends GenericDao {

    /**
     * Creates a new instance of UsuarioDAO
     */
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
    public String validarLoginUser(String user, String password ){
        String resultado = null;
        String jsql = "SELECT u.login,u.password,u.status FROM Usuario u WHERE u.login=:loginname and u.password=:pass and u.status='true'";
        try{
        Query query = HibernateUtil.getSession().createQuery(jsql);
        query.setParameter("loginname", user);
        query.setParameter("pass", password);
        if (query.uniqueResult() != null){
             resultado= "Sucess";
        }else
            resultado = "NotSucess";
        }catch (Exception e){
            e.getMessage();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuário desativado", "Caso queira desativar contacte o admnistrador do Sistema.");
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
        
        return resultado;
    }
}
