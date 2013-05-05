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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class DividaFaces implements Serializable{
    
    private static final long serialVersionUID = -3748575229618280679L;
    FacesMessage msg;
    private List<Divida> ListOfDividas;
    private DividaDAO dividaDAO = new DividaDAO();
    private PagamentoDAO pagDao = new PagamentoDAO();
    private Divida selectedDivida = new Divida();
    private Pagamento selectPagamento = new Pagamento();
    private int dividaID;
    private boolean active = true;
    private boolean act;
    private String opcao;
    /**
     * Váriaveis para o jogo de criação das dividas a partir das parcelas
     */    
    private String novadescricao;
    private String novaempresa;
    private String novoexercicio;
    private String novaobservaocao;
    private String novaoperacaobancaria;
    private int novaparcela;
    private String novotipolancamento;
    private Date novovencimento;
    private Date novodatainicio;
    private Calendar cal;
    
            
    
    /**
     * Creates a new instance of DividaFaces
     */
    public DividaFaces() {
        System.out.println("Mudando "+act);
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
      /**
       * Método para auxiliar na geração das dividas quando há mais ou igual a
       * uma parcela informada.
       * @param parcela
       * @return 
       */
     public int valorRetornoData(int parcela){
         int resultado = parcela*30;
         return resultado;
     }
     
     public boolean resultadoAtivador(){
         if (selectedDivida.isAtivador() == true){
             act = true;
         }else{
             act = false;
         }
         return act;
     }
   
     /**
      * Neste método é utilizado uma logica a depender das parcelas.
      * Acontece que quando a parcela é 0 é gerado a dívida e o pagamento, mas
      * quando a parcela é maior que zero o sistema pode gerar automáticamente 
      * as parcelas na tabela dívida.
      * 
      * Lembrar que quando é receita o campo vencimento não existe bem como 
      * o campo parcela, além do mais lembrar de fazer uma condição que não grave
      * as parcelas ou melhor as dívidas pois não trata de pagamento e sim de uma
      * receita.
      * @throws Exception 
      */
     public void addingDivida() throws Exception {
         
        try {
            novodatainicio = selectedDivida.getDatadeinicio();
            novadescricao = selectedDivida.getDescricao();
            novaempresa = selectedDivida.getEmpresa();
            novoexercicio = selectedDivida.getExercicio();
            novaobservaocao = selectedDivida.getObservacao();
            novaoperacaobancaria = selectedDivida.getOperacao();
            novaparcela = selectedDivida.getParcelas();
            novotipolancamento = selectedDivida.getTipolancamento();
            novovencimento = selectedDivida.getVencimento();
            System.out.println("NOVO DADO DE DATA: "+novodatainicio);
            selectPagamento.setDivida(selectedDivida);
            dividaDAO.addDivida(selectedDivida);
            if(selectedDivida.getParcelas() == 0){
                pagDao.addPagamento(selectPagamento);
            }
            if(selectedDivida.getParcelas()>0){
                for(int i = 1;i<=selectedDivida.getParcelas();i++){
                  //calculando nova data de inicio
                    selectedDivida = new Divida();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(novodatainicio);
                    cal.add(Calendar.DATE, valorRetornoData(i));
                    novodatainicio = cal.getTime();
                    selectedDivida.setDatadeinicio(novodatainicio);
                    selectedDivida.setDescricao(novadescricao);
                    selectedDivida.setEmpresa(novaempresa);
                    selectedDivida.setExercicio(novoexercicio);
                    selectedDivida.setObservacao(novaobservaocao);
                    selectedDivida.setOperacao(novaoperacaobancaria);
                    selectedDivida.setParcelas(novaparcela);
                    selectedDivida.setTipolancamento(novotipolancamento);
                    //calculando novo vencimento
                    //------INICIO---------
                    cal.setTime(novovencimento);
                    cal.add(Calendar.DATE,valorRetornoData(i));
                    novovencimento = cal.getTime();
                    //-------FIM-----------
                    selectedDivida.setVencimento(novovencimento);
                    dividaDAO.addDivida(selectedDivida);
                }
            }
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
         public void addingPay() throws Exception {

        try {
            System.out.println("Opção "+opcao+"!");
             System.out.println("PAGAMENTO "+selectPagamento.toString());
            selectPagamento.setDatapagamento(selectPagamento.getDatapagamento());
            selectPagamento.setFormapagamento(selectPagamento.getFormapagamento());
            selectPagamento.setValor(selectPagamento.getValor());
            selectPagamento.setId(selectPagamento.getId());
            pagDao.addPagamento(selectPagamento);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados gravados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            
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
     
    public void fornecedordeID(){ 
        System.out.println("ID É: "+selectedDivida.getId());
        dividaID = selectedDivida.getId();
        
        try{
            System.out.println("DIVIDAID É: "+dividaID);
            selectPagamento = pagDao.getPagamentodaDivida(dividaID);
        
        }catch (Exception e){
            e.getMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"O ID foi retornado "+selectPagamento.getId()+ " e não existe este ID no banco",null);
           FacesContext.getCurrentInstance().addMessage("add", msg);
        }
            
    }
    
    public void gerarSimParcelas(ActionEvent actionEvent){ 
        opcao = "Sim";
        System.out.println("Opção "+opcao+"!");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmando a operação!",  "Adicionado as parcelas no banco de dados.");  
        FacesContext.getCurrentInstance().addMessage(null, message); 
    }
    public void gerarNaoParcelas(ActionEvent actionEvent){ 
        opcao = "Nao";
        System.out.println("Opção "+opcao+"!");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação Recusada!",  "Possívelmente exista apenas uma parcela.");  
        FacesContext.getCurrentInstance().addMessage(null, message); 
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

    public Pagamento getSelectPagamento() {
        return selectPagamento;
    }

    public void setSelectPagamento(Pagamento selectPagamento) {
        this.selectPagamento = selectPagamento;
    }

    public PagamentoDAO getPagDao() {
        return pagDao;
    }

    public void setPagDao(PagamentoDAO pagDao) {
        this.pagDao = pagDao;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getNovadescricao() {
        return novadescricao;
    }

    public void setNovadescricao(String novadescricao) {
        this.novadescricao = novadescricao;
    }

    public String getNovaempresa() {
        return novaempresa;
    }

    public void setNovaempresa(String novaempresa) {
        this.novaempresa = novaempresa;
    }

    public String getNovoexercicio() {
        return novoexercicio;
    }

    public void setNovoexercicio(String novoexercicio) {
        this.novoexercicio = novoexercicio;
    }

    public String getNovaobservaocao() {
        return novaobservaocao;
    }

    public void setNovaobservaocao(String novaobservaocao) {
        this.novaobservaocao = novaobservaocao;
    }

    public String getNovaoperacaobancaria() {
        return novaoperacaobancaria;
    }

    public void setNovaoperacaobancaria(String novaoperacaobancaria) {
        this.novaoperacaobancaria = novaoperacaobancaria;
    }

    public int getNovaparcela() {
        return novaparcela;
    }

    public void setNovaparcela(int novaparcela) {
        this.novaparcela = novaparcela;
    }

    public String getNovotipolancamento() {
        return novotipolancamento;
    }

    public void setNovotipolancamento(String novotipolancamento) {
        this.novotipolancamento = novotipolancamento;
    }

    public Date getNovovencimento() {
        return novovencimento;
    }

    public void setNovovencimento(Date novovencimento) {
        this.novovencimento = novovencimento;
    }

    public Date getNovodatainicio() {
        return novodatainicio;
    }

    public void setNovodatainicio(Date novodatainicio) {
        this.novodatainicio = novodatainicio;
    }

    public boolean isAct() {
        return act;
    }

    public void setAct(boolean act) {
        this.act = act;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    
    
}
