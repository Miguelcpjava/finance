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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private int ano;
    private int mes;
    private boolean buscar;
    private List<Pagamento> ListOfPagamento;
    private List<Divida> listOfDivida;
    private PagamentoDAO pagDAO;
    private Pagamento selectedPagamento;
    Divida divi;
    DividaDAO dividaDAO;
    private int pagamentoID;
    private double valorDeGasto;
    private double valorRecebido;
   
    /* Variaveis para Planejamento financeiro*/
     double valorTrabalhado;
     double valorEssencial;
     double valorEducacao;
     double valorExtra;
     double valorParaCartao;
     double valorParaSonho;
     double valorParaAposentadoria;
    
    public PagamentoFaces() {
    }
    
    @PostConstruct
    public void init(){
        pagDAO = new PagamentoDAO();
        selectedPagamento = new Pagamento();
        divi = new Divida();
        dividaDAO = new DividaDAO();
    }
    
      public void cleanPay(){
         selectedPagamento = null;
     }
    
     public List<SelectItem> getOptionList(){
         List<Divida> ListOptionDivida = null;
          List<SelectItem> toDataReturn = new LinkedList<SelectItem>();
          DividaDAO divDAO = new DividaDAO();
          try{
              if(buscar){
                  System.out.println("Escolha da Busca "+ isBuscar());
                  ListOptionDivida = divDAO.getDividasByMonth(mes, ano);
              }else{
                  ListOptionDivida = divDAO.getDividas();
              }
              for (Divida dvd: ListOptionDivida){
                  //dvd.getId(), dvd.getId()+' - '+dvd.getEmpresa()
                  toDataReturn.add(new SelectItem(dvd,dvd.getId()+" - "+dvd.getCredor().getNomeCredor()));
              }
          }catch (Exception e){
              e.printStackTrace();
              e.getMessage();
             
          }
          return toDataReturn;
     } 
      
      public void newPay(){
          selectedPagamento = new Pagamento();
          msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Já pode adicionar um novo Pagamento!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
      }
      
      public void addingPayInNewDisplay() throws Exception {

        try {
            selectedPagamento.setStatuspagamento("Pago");
            pagDAO.addPagamento(selectedPagamento);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados gravados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            cleanPay();
            newPay();
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
       public double getValordeGastoNoPeriodo(){
           valorDeGasto = 0.0;
           try{
               valorDeGasto = pagDAO.verificaoDeGasto(mes, ano);
           }catch (Exception e){
               e.printStackTrace();
           }
           return valorDeGasto;
       }
       public double getValorRecebidoNoPeriodo(){
           valorRecebido = 0.0;
           try{
               valorRecebido = dividaDAO.verificaoDeReceitaRecebida(mes, ano);
           }catch (Exception e){
               e.printStackTrace();
           }
           return valorRecebido;
       }
       
       public void planejamentoFinanceiro(){
            valorTrabalhado = 0.0;
            valorEssencial =0.0;
            valorEducacao = 0.0;
            valorExtra=0.0;
            valorParaCartao=0.0;
           valorParaSonho =0.0;
           valorParaAposentadoria = 0.0;
           
           valorTrabalhado = getValorRecebidoNoPeriodo();
           
           valorEssencial         = (valorTrabalhado*0.55);
           valorEducacao          = (valorTrabalhado*0.05);
           valorExtra             = (valorTrabalhado*0.10);
           valorParaCartao        = (valorTrabalhado*0.30);
           valorParaSonho         = (valorTrabalhado*0.20);
           valorParaAposentadoria = (valorTrabalhado*0.10);
           
           
       }
   /*    public int idDividia(){
           df.fornecedordeID();
       }*/
   
     public void atualizarValor(){
         selectedPagamento.setValor(selectedPagamento.getDivida().getValortotal());
         msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizando o valor com sucesso!", null);
         FacesContext.getCurrentInstance().addMessage("add", msg);
         System.out.println("Valor Atualizado: "+selectedPagamento.getValor());
     }
     public List<Divida> completeDivida(String Name) {
        List<Divida> dividas = new ArrayList<Divida>();
        DividaDAO divDAO = new DividaDAO();
        if (listOfDivida == null || listOfDivida.isEmpty()) {
            if(buscar){
                listOfDivida = divDAO.getDividasByMonth(mes, ano); //Mudar este método para que só visualize as dividas no período só não pagas
            }else{
                listOfDivida = divDAO.getListCadastradasNaoPagas();
            }
            divDAO = null;
        }
        for (Divida pe : listOfDivida) {
            if (pe.getDescricao().contains(Name)) {
                pe.setId(pe.getId()== 0 ? null : pe.getId());
                dividas.add(pe);
            }
        }
        return dividas;
    }
     
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getValorDeGasto() {
        return valorDeGasto;
    }

    public void setValorDeGasto(double valorDeGasto) {
        this.valorDeGasto = valorDeGasto;
    }

    public Divida getDivi() {
        return divi;
    }

    public void setDivi(Divida divi) {
        this.divi = divi;
    }

    public boolean isBuscar() {
        return buscar;
    }

    public void setBuscar(boolean buscar) {
        this.buscar = buscar;
    }

    public List<Divida> getListOfDivida() {
        return listOfDivida;
    }

    public void setListOfDivida(List<Divida> listOfDivida) {
        this.listOfDivida = listOfDivida;
    }

    public DividaDAO getDividaDAO() {
        return dividaDAO;
    }

    public void setDividaDAO(DividaDAO dividaDAO) {
        this.dividaDAO = dividaDAO;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
    
       
}
