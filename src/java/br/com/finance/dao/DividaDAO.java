/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Divida;
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
public class DividaDAO extends GenericDao implements Serializable {
    
    private static final long serialVersionUID = 4200460730610273962L;
    /**
     * Creates a new instance of DividaDAO
     */
    public DividaDAO() {
    }
    
     public int addDivida(Divida dividas){
       savingPojo(dividas);
        return dividas.getId();
    }
    
    public void removeDivida(Divida dividas){
        removePojo(dividas);
    }
    
    public void updateDivida(Divida dividas){
        updatePojo(dividas);
    }
    
     public Divida getDivida(Integer dividaID){
        Divida dividas = (Divida)gettingPojo(Divida.class, dividaID);
        return dividas;
    }
     public List<Divida> getDividas(){
        return getCleanListOfObjects(Divida.class,"from Divida divida");
    }
    // public List<Divida> getDividasNotPay(){
        /*
         * SELECT *
            FROM  finance.divida d where d.iddivida NOT IN(SELECT p.iddivida from finance.pagamento p where status='Pago' );
         */
   // }
     public List<Divida> getDividasByMonth(int mes, int ano){
        return getCleanListOfObjects(Divida.class, "from Divida divida where month(divida.datadeinicio)='"+mes+"' and year(divida.datadeinicio)='"+ano+"'");
     }
    public int getIddivida(int id){
        Query query = HibernateUtil.getSession().createSQLQuery("SELECT iddivida FROM divida where iddivida=:id");
         query.setParameter("id", id);
         return (Integer) query.uniqueResult();
    }
    public String getDividaPaga(int id){
        Query query = HibernateUtil.getSession().createSQLQuery("SELECT pag.status FROM divida di inner join pagamento pag on (di.iddivida = pag.iddivida) where di.iddivida =:id and pag.status = 'Pago'");
        query.setParameter("id", id);
        if (query.uniqueResult() == null){
            return "Não Pago";
        }else
            return "Pago";
    }
    public List<Divida> getDividasPagas(int mes){
        Query qr = HibernateUtil.getSession().createSQLQuery("SELECT D.EMPRESA as empresa,P.DATAPAGAMENTO as datapagamento,P.VALORPAGAMENTO as valor FROM DIVIDA D INNER JOIN PAGAMENTO P ON(D.IDDIVIDA = P.IDDIVIDA) WHERE MONTH(DATADEINICIO) =:mes AND P.status = 'Pago'");
        qr.setParameter("mes", mes);
        return qr.list();
    }
    
    public List<Divida> getDividasToRel(int mes){
        return getCleanListOfObjects(Divida.class, "from Divida divida where month(divida.datadeinicio)='"+mes+"'");
    }

}
