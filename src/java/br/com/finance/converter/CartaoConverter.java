/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.converter;

import br.com.finance.dao.CartaoDAO;
import br.com.finance.model.Cartao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Miguel Lima
 */
@FacesConverter(value = "cartaoConverter", forClass = Cartao.class)
public class CartaoConverter implements Converter{

    @Override
   public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0){
	           Long codigo = Long.valueOf(value);
	           try{
	                  CartaoDAO bancDAO = new CartaoDAO();
	               return bancDAO.getCartaoByID(codigo);
	           }catch(Exception e){
	               e.printStackTrace();
	               throw new ConverterException("Não foi possivel encontrar o cartao "+value+". "+e.getMessage());
	           }
	       }
	       return null;
    }

    @Override
      public String getAsString(FacesContext fc, UIComponent uic, Object value) {
           if (value == null){
	          return "";
	      }
           Cartao card = (Cartao) value;
           return String.valueOf(card.getIdcartao());
    }
    
}
