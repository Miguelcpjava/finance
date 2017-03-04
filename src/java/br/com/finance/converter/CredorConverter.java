/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.converter;

import br.com.finance.dao.CredorDAO;
import br.com.finance.model.Credores;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Miguel Lima
 */
@FacesConverter(value = "credorConverter", forClass = Credores.class)
public class CredorConverter implements Converter{
    
   @Override
   public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0){
	           Long codigo = Long.valueOf(value);
	           try{
	                      CredorDAO credorDAO = new CredorDAO();
	               return credorDAO.getCredorById(codigo);
	           }catch(Exception e){
	               e.printStackTrace();
	               throw new ConverterException("Não foi possivel encontrar o credor "+value+". "+e.getMessage());
	           }
	       }
	       return null;
    }

    @Override
      public String getAsString(FacesContext fc, UIComponent uic, Object value) {
           if (value == null){
	          return "";
	      }
           Credores card = (Credores) value;
           return String.valueOf(card.getIdcredor());
    }
    
}
