/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Cartao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miguel Lima
 */
public class CartaoDAO extends GenericDao implements Serializable{
    
    private static final long serialVersionUID = -2642719708619160828L;
    
    public Long addCartao(Cartao cartao){
        savingPojo(cartao);
        return cartao.getIdcartao();
    }
    
    public void updateCartao(Cartao cartao){
        
    }
    public void deleteCartao(Cartao cartao){
        
    }
    /**
     * Metodo para buscar o cartao de credito por ID
     * @param cartaoID
     * @return card
     */
      public Cartao getCartaoByID(Long cartaoID){
        Cartao card = (Cartao) gettingPojo(Cartao.class, cartaoID);
        return card;
    }
     
      public List<Cartao> getListCardByUsers(String usuario){
          Session sessao = getSession();
          List<Cartao> cards = null;
          try{
              Criteria cr = sessao.createCriteria(Cartao.class);
              cr.add(Restrictions.eq("usuario", usuario));
              cards = new ArrayList<Cartao>();
              cards = cr.list();
          }catch(Exception e){
              e.printStackTrace();
              cards = null;
          }finally{
              sessao.close();
          }
          return cards;
      }
}
