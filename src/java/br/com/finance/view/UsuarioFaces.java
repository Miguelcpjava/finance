/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.view;

import br.com.finance.dao.UsuarioDAO;
import br.com.finance.model.Usuario;
import br.com.finance.util.Criptografia;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class UsuarioFaces implements Serializable{
    private static final long serialVersionUID = -2099476085016810021L;
    
    /**
     * Lembrar de fazer uma função de cadastrar quem está realizando a ação;
     */

    FacesMessage msg;
    private List<Usuario> ListOfUsers;
    private UsuarioDAO userDAO;
    private Usuario selectedUsuarios;
    private Usuario compareuser;
    private int UsuarioID;
    private boolean active = true;
    Criptografia cripto;
    
    public UsuarioFaces() {
    }
    @PostConstruct
    public void init(){
        userDAO = new UsuarioDAO();
        selectedUsuarios = new Usuario();
        cripto = new Criptografia();
    }
    
     public void cleanUser(){
         selectedUsuarios = null;
     }
      public List<Usuario> getListofUser() {
        if ((ListOfUsers == null) || ListOfUsers.isEmpty()) {
            ListOfUsers = userDAO.getUsers();
            //ListOfDocumentos = documentDAO.listar(filtroAno, filtrocodDocumento, filtrotipo, filtroEntidade, filtroMes, filtrovalidade, filtroCliente);
        }
        return ListOfUsers;
    }
     
       public void addingUser() throws Exception, Throwable {

        try {
            selectedUsuarios.setPassword(cripto.encriptar(cripto.CHAVE, selectedUsuarios.getPassword()));
            userDAO.addUsuario(selectedUsuarios);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados gravados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            ListOfUsers = null;
            active = true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getLocalizedMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi inserido com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);


        }
       }
        
         public void updateUser() throws Exception {
        try {
            userDAO.addUsuario(selectedUsuarios);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Dados atualizados com sucesso!", ""));
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi atualizados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
     
     public void deleteUser() throws Exception {
        try {
            userDAO.removeUsuario(selectedUsuarios);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados foi deletado com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            cleanUser();
        } catch (Exception e) {
            e.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi deletados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
    } 
     public String loginConnection() throws Throwable{
         String conecta = null;
         String descriptoDigitado = null;
         try{
             descriptoDigitado = cripto.encriptar(cripto.CHAVE, selectedUsuarios.getPassword());
             if( userDAO.validarLoginUser(selectedUsuarios.getLogin(), descriptoDigitado) != null){
                conecta = "Sucess";
             }else{
                conecta = "NotSucess";
             }
        }catch (Exception e){
              System.out.println("Login Usuário Não Aceito!");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário ou senha inválido", "Favor digitar login ou senha válida.");
            FacesContext.getCurrentInstance().addMessage("add", msg);
            e.getMessage();
        }
        return conecta;
     }
    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public List<Usuario> getListOfUsers() {
        return ListOfUsers;
    }

    public void setListOfUsers(List<Usuario> ListOfUsers) {
        this.ListOfUsers = ListOfUsers;
    }

    public UsuarioDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UsuarioDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Usuario getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(Usuario selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(int UsuarioID) {
        this.UsuarioID = UsuarioID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
     
     
}
