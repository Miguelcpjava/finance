/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.view;

import br.com.finance.dao.CartaoDAO;
import br.com.finance.dao.CredorDAO;
import br.com.finance.dao.DividaDAO;
import br.com.finance.dao.PagamentoDAO;
import br.com.finance.model.Cartao;
import br.com.finance.model.Credores;
import br.com.finance.model.Divida;
import br.com.finance.model.Pagamento;
import br.com.finance.util.Reportutil;
import br.com.ltn4java.data.DataCompare;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class DividaFaces implements Serializable {

    /**
     * OBSERVAÇÕES: 1 - VALORES DOS MESES ESTÁ EM DESACORDO(Resolvido) 2 - CRIAR O DATA
     * TABLE DAS DIVIDAS PARA ALTERAÇÃO 3 - QUANDO É DIVIDO O VALOR BRUTO 
     * PERMANECE (Resolvido de outra maneira)
     */
    private static final long serialVersionUID = -3748575229618280679L;
    private String statuspagamento;
    FacesMessage msg;
    int parcelasparaoArray; //para poder setar as parcelas de acordo com a correspondente se x data a parcela é 1, y data a parcela é 2 e assim por diante.
    Reportutil reportUtil;
    private List<Divida> ListOfDividas;
    private DividaDAO dividaDAO;
    private PagamentoDAO pagDao;
    private CartaoDAO cartDAO;
    private CredorDAO credorDAO;
    private Divida selectedDivida;
    private Pagamento selectPagamento;
    private int dividaID;
    private boolean active = true;
    private boolean act;
    private boolean dividanestemes;//Se a divida já conta no mes da data de inicio, depende do dia do cartão;
    private String opcao;
    private List<Divida> dividaSelect;
    private List<Credores> credores;
    List<Divida> sugestoes;
    private List<Cartao> cartoes;
    DataCompare dt;
    //Argumentos para os relatórios
    private int mesdesejado;
    private int anodesejado;
    private String nomeRelatorio;
    
    /**
     * Váriaveis para o jogo de criação das dividas a partir das parcelas
     */
    private String novadescricao;
    private Credores novaempresa;
    private String novoexercicio;
    private String novaobservaocao;
    private String novaoperacaobancaria;
    private String novonilvel; //Para difenrenciar o total das parcelas;
    private int novaparcela;
    private double novovalor;
    private String novotipolancamento;
    private Date novovencimento;
    private Date novodatainicio;
    private Calendar cal = Calendar.getInstance();

    /**
     * Creates a new instance of DividaFaces
     */
    public DividaFaces() {
        System.out.println("Mudando " + act);
    }
    @PostConstruct
    public void init(){
        dt = new DataCompare();
        cartoes = new ArrayList<Cartao>();
        credores = new ArrayList<Credores>();
        cartDAO = new CartaoDAO();
        credorDAO = new CredorDAO();
        dividaSelect = new ArrayList<Divida>();
        sugestoes = new ArrayList<Divida>();
        selectPagamento = new Pagamento();
        pagDao = new PagamentoDAO();
        dividaDAO = new DividaDAO();
        reportUtil = new Reportutil();
        selectedDivida = new Divida();
    }
    public void novaDivida() {
        selectedDivida = null;
          msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Já pode adicionar uma nova dívida!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            selectedDivida = new Divida();
    }
    
    public List<Cartao> completeCartao(String Name) {
        List<Cartao> nomedocartao = new ArrayList<Cartao>();
        if (cartoes == null || cartoes.isEmpty()) {
            cartoes = cartDAO.getListCardByUsers("mlima");
        }
        for (Cartao pe : cartoes) {
            if (pe.getDescricao().contains(Name)) {
                pe.setIdcartao(pe.getIdcartao()== 0 ? null : pe.getIdcartao());
                nomedocartao.add(pe);
            }
        }
        return nomedocartao;
    }
    public List<Credores> completeCredor(String Name){
        List<Credores> nomedocredor = new ArrayList<Credores>();
        if (credores == null || credores.isEmpty()){
            credores = credorDAO.getCredores();
        }
        for (Credores credor: credores){
            if(credor.getNomeCredor().contains(Name)){
                credor.setIdcredor(credor.getIdcredor() == 0 ? null : credor.getIdcredor());
                nomedocredor.add(credor);
            }
        }
        return nomedocredor;
    }
    public List<Divida> getListofDivida() {
        if ((ListOfDividas == null) || ListOfDividas.isEmpty()) {
            ListOfDividas = dividaDAO.getListCadastradasNaoPagas();
            //ListOfDocumentos = documentDAO.listar(filtroAno, filtrocodDocumento, filtrotipo, filtroEntidade, filtroMes, filtrovalidade, filtroCliente);
        }
        return ListOfDividas;
    }
    public List<Divida> getListarTodasDividasUsuario(){
        if(sugestoes.isEmpty() || sugestoes == null){
            sugestoes = dividaDAO.getDividas();
        }
        return sugestoes;
    }

    /**
     * Método para auxiliar na geração das dividas quando há mais ou igual a uma
     * parcela informada.
     *
     * @param parcela
     * @return
     */
    public int valorRetornoData(int parcela) {
        int resultado = 0;
        //Fazer a lógica de que quando o mes for x é igual a 30 mas se for y = 31
        int mesextraido = extrairMes(novodatainicio);
        int anoextarido = extrairAno(novodatainicio);
        if (mesextraido == 1 || mesextraido == 3 || mesextraido == 5 || mesextraido == 7 || mesextraido == 8 || mesextraido == 10 || mesextraido == 12) {
            resultado += 31;
            System.out.println("Mês tem 31 Dias");
        } else {
            if (mesextraido == 4 || mesextraido == 6 || mesextraido == 9 || mesextraido == 11) {
                resultado += 30;
                System.out.println("Mês tem 30 Dias");
            } else {
                if (dt.febuary29th(anoextarido)) {
                    resultado += 29;
                    System.out.println("Mês tem 29 Dias");
                } else {
                    resultado += 28;
                }
                System.out.println("Mês tem 28 Dias");
            }
        }
        return resultado;
    }
    
    public int extrairMes(Date data) {
        Calendar calendata = Calendar.getInstance();
        calendata.setTime(data);
        int mes = calendata.get(Calendar.MONTH);
        
        return mes;
    }

    public int extrairAno(Date data) {
        Calendar calendata = Calendar.getInstance();
        calendata.setTime(data);
        int ano = calendata.get(Calendar.YEAR);
        
        return ano;
    }
    
    public boolean resultadoAtivador() {
        if (selectedDivida.isAtivador() == true) {
            act = true;
        } else {
            act = false;
        }
        return act;
    }

    /**
     * Neste método é utilizado uma logica a depender das parcelas. Acontece que
     * quando a parcela é 0 é gerado a dívida e o pagamento, mas quando a
     * parcela é maior que zero o sistema pode gerar automáticamente as parcelas
     * na tabela dívida.
     *
     * Lembrar que quando é receita o campo vencimento não existe bem como o
     * campo parcela, além do mais lembrar de fazer uma condição que não grave
     * as parcelas ou melhor as dívidas pois não trata de pagamento e sim de uma
     * receita.
     *
     * @throws Exception
     */
   public void addingDivida() throws Exception {
        
        try {
             
            if(selectedDivida.getTipolancamento().equals("D")){
                selectedDivida.setStatus("Não Pago");
            }else{
                selectedDivida.setStatus("Recebido");
            }
            novodatainicio = selectedDivida.getDatadeinicio();
            novadescricao = selectedDivida.getDescricao();
            novaempresa = selectedDivida.getCredor();
            novoexercicio = selectedDivida.getExercicio();
            novaobservaocao = selectedDivida.getObservacao();
            novaoperacaobancaria = selectedDivida.getOperacao();
            novaparcela = selectedDivida.getParcelas();
            novotipolancamento = selectedDivida.getTipolancamento();
            novovencimento = selectedDivida.getVencimento();

	    selectedDivida.setNivel("S");

            if(selectedDivida.getParcelas()==0){
                novovalor = selectedDivida.getValortotal();
		System.out.println("Parcela Igual a zero!!!");
                dividaDAO.addDivida(selectedDivida);
                //pagDao.addPagamento(selectPagamento);
	        
            }

            if (selectedDivida.getParcelas() > 0) {
                setNovonilvel("A");
                parcelasparaoArray = selectedDivida.getParcelas();
                novovalor = selectedDivida.getValortotal()/selectedDivida.getParcelas();
                for (int i = 1; i <= parcelasparaoArray; i++) {
                    //calculando nova data de inicio
                    novaparcela = i;
                    selectedDivida = new Divida();
                     System.out.println("Valor da variavel fora do método: "+dividanestemes);
                    if((!dividanestemes) || (i > 1) ){
                        System.out.println("Valor da variavel no método: "+dividanestemes);
                        cal.setTime(novodatainicio);
                        cal.add(Calendar.DATE, valorRetornoData(i));
                        novodatainicio = cal.getTime();
                        cal.setTime(novovencimento);
                        cal.add(Calendar.DATE, valorRetornoData(i));
                        novovencimento = cal.getTime();
                    }
                    selectedDivida.setDatadeinicio(novodatainicio);
                    selectedDivida.setDescricao(novadescricao);
                    selectedDivida.setNivel(novonilvel);
                    selectedDivida.setCredor(novaempresa);
                    selectedDivida.setExercicio(novoexercicio);
                    selectedDivida.setObservacao(novaobservaocao);
                    selectedDivida.setOperacao(novaoperacaobancaria);
                    selectedDivida.setParcelas(novaparcela);
                    selectedDivida.setTipolancamento(novotipolancamento);
                    selectedDivida.setValortotal(novovalor);
                    selectedDivida.setVencimento(novovencimento);
                   
                    dividaDAO.addDivida(selectedDivida);
                }
            }
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados gravados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
            ListOfDividas = null;
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados atualizados com sucesso!", ""));
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
        } catch (Exception e) {
            e.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados não foi deletados com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
    }
    
    public void fornecedordeID() {
        System.out.println("ID É: " + selectedDivida.getId());
        dividaID = selectedDivida.getId();
        
        try {
            System.out.println("DIVIDAID É: " + dividaID);
            selectPagamento = pagDao.getPagamentodaDivida(dividaID);
            
        } catch (Exception e) {
            e.getMessage();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O ID foi retornado " + selectPagamento.getId() + " e não existe este ID no banco", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
        
    }
    
    public List<Divida> getAutoCompletePagamento() {
        dividaSelect = dividaDAO.getDividas();
        for (Divida d : dividaSelect) {
            sugestoes.add(d);
        }
        return sugestoes;
    }
    
    public List<Divida> getCompletemetodo() {
        dividaSelect = dividaDAO.getDividas();
        for (Divida d : dividaSelect) {
            sugestoes.add(d);
        }
        return sugestoes;
    }
    
    
    
   public void printReport() throws IOException, JRException, ClassNotFoundException, Exception{
       System.out.println("Mes: "+mesdesejado+ " Modelo: "+nomeRelatorio);
       String nomeparagravacao = nomeRelatorio+" "+mesdesejado+"-"+anodesejado;
       reportUtil.imprimirRelatorio(mesdesejado,anodesejado, nomeRelatorio,"mes",nomeparagravacao );
       
   }
   public String pagamentoFinalizado(){
       String Resultado = dividaDAO.getDividaPaga(selectedDivida.getId());
       if (Resultado.equalsIgnoreCase("Pago")){
           return "P";
       }else
           return "NP";
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

    public boolean isDividanestemes() {
        return dividanestemes;
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

    public List<Divida> getDividaSelect() {
        return dividaSelect;
    }

    public void setDividaSelect(List<Divida> dividaSelect) {
        this.dividaSelect = dividaSelect;
    }

    public List<Divida> getSugestoes() {
        return sugestoes;
    }

    public void setSugestoes(List<Divida> sugestoes) {
        this.sugestoes = sugestoes;
    }

    public double getNovovalor() {
        return novovalor;
    }

    public void setNovovalor(double novovalor) {
        this.novovalor = novovalor;
    }

    public DataCompare getDt() {
        return dt;
    }

    public void setDt(DataCompare dt) {
        this.dt = dt;
    }

    public String getNovonilvel() {
        return novonilvel;
    }

    public void setNovonilvel(String novonilvel) {
        this.novonilvel = novonilvel;
    }

    public int getParcelasparaoArray() {
        return parcelasparaoArray;
    }

    public void setParcelasparaoArray(int parcelasparaoArray) {
        this.parcelasparaoArray = parcelasparaoArray;
    }

    public Reportutil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(Reportutil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public int getMesdesejado() {
        return mesdesejado;
    }

    public void setMesdesejado(int mesdesejado) {
        this.mesdesejado = mesdesejado;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public String getStatuspagamento() {
        return statuspagamento;
    }

    public void setStatuspagamento(String statuspagamento) {
        statuspagamento = dividaDAO.getDividaPaga(12);
        this.statuspagamento = statuspagamento;
    }

    public void setDividanestemes(boolean dividanestemes) {
        this.dividanestemes = dividanestemes;
    }

    public int getAnodesejado() {
        return anodesejado;
    }

    public void setAnodesejado(int anodesejado) {
        this.anodesejado = anodesejado;
    }

    public CartaoDAO getCartDAO() {
        return cartDAO;
    }

    public void setCartDAO(CartaoDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public CredorDAO getCredorDAO() {
        return credorDAO;
    }

    public void setCredorDAO(CredorDAO credorDAO) {
        this.credorDAO = credorDAO;
    }

    public List<Credores> getCredores() {
        return credores;
    }

    public void setCredores(List<Credores> credores) {
        this.credores = credores;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public Credores getNovaempresa() {
        return novaempresa;
    }

    public void setNovaempresa(Credores novaempresa) {
        this.novaempresa = novaempresa;
    }
}
