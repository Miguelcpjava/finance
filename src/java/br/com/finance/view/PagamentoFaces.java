/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.view;

import br.com.finance.dao.DividaDAO;
import br.com.finance.dao.PagamentoDAO;
import br.com.finance.model.Divida;
import br.com.finance.model.Pagamento;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class PagamentoFaces implements Serializable{

    FacesMessage msg;
    private List<Pagamento> ListOfPagamento;
    private PagamentoDAO pagDAO = new PagamentoDAO();
    private Pagamento selectedPagamento = new Pagamento();
    DividaFaces df = new DividaFaces();
    Divida divi = new Divida();
    private int pagamentoID;
    
    public PagamentoFaces() {
    }
    
      public void cleanPay(){
         selectedPagamento = null;
     }
      
     public List<SelectItem> getOptionList(){
         List<Divida> ListOptionDivida = null;
          List<SelectItem> toDataReturn = new LinkedList<SelectItem>();
          DividaDAO divDAO = new DividaDAO();
          try{
              ListOptionDivida = divDAO.getDividas();
              for (Divida dvd: ListOptionDivida){
                  //dvd.getId(), dvd.getId()+' - '+dvd.getEmpresa()
                  toDataReturn.add(new SelectItem(dvd,dvd.getEmpresa()));
              }
          }catch (Exception e){
              e.printStackTrace();
              e.getMessage();
             
          }
          return toDataReturn;
     } 
      
      public void newPay(){
          selectedPagamento = new Pagamento();
           msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Já pode inserir novos dados", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
      }
      
      public void addingPayInNewDisplay() throws Exception {

        try {
            selectedPagamento.setStatuspagamento("Pago");
            pagDAO.addPagamentov2(selectedPagamento);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados gravados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            ListOfPagamento = null;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getLocalizedMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi inserido com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
       }
       public void addingPay() throws Exception {

        try {
          //System.out.println("Divida Selecionada: "+selectedPagamento.);
          //System.out.println("Pagamento Divida "+selectedPagamento.getDivida().getId());
            pagDAO.addPagamento(selectedPagamento);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados gravados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            ListOfPagamento = null;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getLocalizedMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi inserido com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
       }
        
         public void updatePay() throws Exception {
        try {
            pagDAO.addPagamento(selectedPagamento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Dados atualizados com sucesso!", ""));
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi atualizados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
     
     public void deletePay() throws Exception {
        try {
            pagDAO.removePagamento(selectedPagamento);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados foi deletado com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            cleanPay();
        } catch (Exception e) {
            e.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi deletados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
    } 
     
       public List<Pagamento> getListofPagamentos() {
        System.out.println("Lista de Pagamento: " + ListOfPagamento);
        if ((ListOfPagamento == null) || ListOfPagamento.isEmpty()) {
            ListOfPagamento = pagDAO.getPays();
            //ListOfDocumentos = documentDAO.listar(filtroAno, filtrocodDocumento, filtrotipo, filtroEntidade, filtroMes, filtrovalidade, filtroCliente);
        }
        return ListOfPagamento;
    }
   /*    public int idDividia(){
           df.fornecedordeID();
       }*/
    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public List<Pagamento> getListOfPagamento() {
        return ListOfPagamento;
    }

    public void setListOfPagamento(List<Pagamento> ListOfPagamento) {
        this.ListOfPagamento = ListOfPagamento;
    }

    public PagamentoDAO getPagDAO() {
        return pagDAO;
    }

    public void setPagDAO(PagamentoDAO pagDAO) {
        this.pagDAO = pagDAO;
    }

    public Pagamento getSelectedPagamento() {
        return selectedPagamento;
    }

    public void setSelectedPagamento(Pagamento selectedPagamento) {
        this.selectedPagamento = selectedPagamento;
    }

    public int getPagamentoID() {
        return pagamentoID;
    }

    public void setPagamentoID(int pagamentoID) {
        this.pagamentoID = pagamentoID;
    }
    private static final long serialVersionUID = -8373616954008406359L;
       
       
}
