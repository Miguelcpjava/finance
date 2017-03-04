/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.converter;

import br.com.finance.dao.DividaDAO;
import br.com.finance.model.Divida;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Miguel Lima
 */
@FacesConverter(value = "dividaConverter", forClass = Divida.class)
public class DividaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0){
	           int codigo = Integer.parseInt(value);
	           try{
                       DividaDAO div = new DividaDAO();
                       return div.getDivida(codigo);
                     }catch(Exception e){
	               e.printStackTrace();
	               throw new ConverterException("Não foi possivel encontrar a dívida "+value+". "+e.getMessage());
	           }
	       }
	       return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
          if (value == null){
	          return "";
	      }
          Divida divida = (Divida) value;
          return String.valueOf(divida.getId());
          
    }
    
}
