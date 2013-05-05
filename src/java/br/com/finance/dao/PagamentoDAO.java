/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Pagamento;
import br.com.finance.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class PagamentoDAO extends GenericDao implements Serializable {

  
    public PagamentoDAO() {
    }
    
     public int addPagamento(Pagamento pagamentos){
       savingPojo(pagamentos);
        return pagamentos.getId();
    }
    public int addPagamentov2(Pagamento pagamentos){
        savingWithOutUpdate(pagamentos);
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
         public Pagamento getPagamentodaDivida(int id) {
        Integer numero;
        Query resultado = HibernateUtil.getSession().createSQLQuery("SELECT list(pag) FROM Pagamento pag where iddivida=:id");
        resultado.setParameter("id", id);
        numero = (Integer) resultado.uniqueResult();
        System.out.println("NUMERO É: "+numero);
        if (numero > 1) {

            Query query = HibernateUtil.getSession().createSQLQuery("SELECT pag FROM Pagamento pag where iddivida=:id");
            query.setParameter("id", id);

            return (Pagamento) query.list();
        } else if (numero == 1) {
            Query query = HibernateUtil.getSession().createSQLQuery("SELECT pag FROM Pagamento pag where iddivida=:id");
            query.setParameter("id", id);

            return (Pagamento) query.uniqueResult();
        }else
            
        return null;
    }
    private static final long serialVersionUID = -7228645506114447356L;
    } 

