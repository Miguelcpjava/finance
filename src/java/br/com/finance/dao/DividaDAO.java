/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Divida;
import br.com.finance.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
         Session sessao = getSession();
         List<Divida> dividas = null;
        /* Date data = new Date();
           GregorianCalendar dataCal = new GregorianCalendar();
           dataCal.setTime(data);
           mes = dataCal.get(Calendar.MONTH);*/
         try{
             Query query = sessao.createSQLQuery("SELECT * FROM divida d where date_part('month',d.datadeinicio)=:mes and date_part('year',d.datadeinicio)=:ano and d.tipolancamento='D'"
                + "and status='Não Pago'").addEntity(Divida.class);
             query.setParameter("mes", mes);
             query.setParameter("ano", ano);
             dividas = query.list();
         }catch(Exception e){
             e.printStackTrace();
             dividas = null;
         }finally{
             sessao.close();
         }
         return dividas;
     }

    public int getIddivida(int id){
        Session sessao = getSession();
        Divida div = null;
        try{
            Criteria cr = sessao.createCriteria(Divida.class);
            cr.add(Restrictions.eq("id", id));
            div = new Divida();
            div = (Divida) cr.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
            div = null;
        }finally{
            sessao.close();
        }
         return div.getId();
    }

    public List<Divida> getListCadastradasNaoPagas(){
        Session sessao = getSession();
        List<Divida> divis = null;
        try{
            Criteria cr = sessao.createCriteria(Divida.class);
            cr.add(Restrictions.eq("status", "Não Pago"));
            cr.add(Restrictions.eq("tipolancamento", "D"));
            divis = new ArrayList<Divida>();
            divis = cr.list();
        }catch(Exception e){
            e.printStackTrace();
            divis = null;
        }finally{
            sessao.close();
        }
        return divis;
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

    public double verificaoDeReceitaRecebida(int mes, int ano) {
        double valortotalmes = 0.0;
        Session sessao = getSession();
        try {
            Query qr = sessao.createSQLQuery("SELECT COALESCE(SUM(divi.valorpagamentodiv),0) FROM divida divi where date_part('MONTH', divi.datadeinicio)=:mes and date_part('YEAR', divi.datadeinicio)=:ano and divi.tipolancamento='R'");
            qr.setParameter("mes", mes);
            qr.setParameter("ano", ano);
            valortotalmes = (Double) qr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro no método verificação de Gasto de acordo com o periodo escolhido na classe DividaDAO");
        }finally{
            sessao.close();
        }
        
        return valortotalmes;
    }

}