/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Pagamento;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class PagamentoDAO extends GenericDao {

  
    public PagamentoDAO() {
    }
    
     public int addPagamento(Pagamento pagamentos){
       savingPojo(pagamentos);
        return pagamentos.getId();
    }
    
    public void removePagamento(Pagamento pagamentos){
        removePojo(pagamentos);
    }
    
    public void updatePagamento(Pagamento pagamentos){
        updatePojo(pagamentos);
    }
    
     public Pagamento getPagamento(Integer PagamentoID){
        Pagamento pagamentos = (Pagamento)gettingPojo(Pagamento.class, PagamentoID);
        return pagamentos;
    }
       public List<Pagamento> getPays(){
        return getCleanListOfObjects(Pagamento.class,"from Pagamento pag");
    }
}
