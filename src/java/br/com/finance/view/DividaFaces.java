/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.view;

import br.com.finance.dao.DividaDAO;
import br.com.finance.model.Divida;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class DividaFaces {

    FacesMessage msg;
    private List<Divida> ListOfDividas;
    private DividaDAO dividaDAO = new DividaDAO();
    private Divida selectedDivida = new Divida();
    private int dividaID;
    private boolean active = true;
    /**
     * Creates a new instance of DividaFaces
     */
    public DividaFaces() {
    }
    
      public void cleanUser(){
         selectedDivida = null;
     }
      public List<Divida> getListofDivida() {
        System.out.println("Lista de Dividas: " + ListOfDividas);
        if ((ListOfDividas == null) || ListOfDividas.isEmpty()) {
            ListOfDividas = dividaDAO.getDividas();
            //ListOfDocumentos = documentDAO.listar(filtroAno, filtrocodDocumento, filtrotipo, filtroEntidade, filtroMes, filtrovalidade, filtroCliente);
        }
        return ListOfDividas;
    }
     
       public void addingDivida() throws Exception {

        try {
            dividaDAO.addDivida(selectedDivida);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados gravados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            ListOfDividas = null;
            active = true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getLocalizedMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi inserido com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);


        }
       }
        
         public void updateDivida() throws Exception {
        try {
            dividaDAO.addDivida(selectedDivida);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Dados atualizados com sucesso!", ""));
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi atualizados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
     
     public void deleteDivida() throws Exception {
        try {
            dividaDAO.removeDivida(selectedDivida);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados foi deletado com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            cleanUser();
        } catch (Exception e) {
            e.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi deletados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
    } 
    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public List<Divida> getListOfDividas() {
        return ListOfDividas;
    }

    public void setListOfDividas(List<Divida> ListOfDividas) {
        this.ListOfDividas = ListOfDividas;
    }

    public DividaDAO getDividaDAO() {
        return dividaDAO;
    }

    public void setDividaDAO(DividaDAO dividaDAO) {
        this.dividaDAO = dividaDAO;
    }

    public Divida getSelectedDivida() {
        return selectedDivida;
    }

    public void setSelectedDivida(Divida selectedDivida) {
        this.selectedDivida = selectedDivida;
    }

    public int getDividaID() {
        return dividaID;
    }

    public void setDividaID(int dividaID) {
        this.dividaID = dividaID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
