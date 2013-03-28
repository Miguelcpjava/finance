/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
    
}
