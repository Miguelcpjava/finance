/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.util;

import br.com.finance.dao.DividaDAO;
import br.com.finance.model.Divida;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Miguel
 */
@FacesConverter(value="dividaConverter",forClass = Divida.class)
public class DividaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
       if(value != null && value.trim().length() > 0){
           Integer codigo = Integer.valueOf(value);
           System.out.println("CONVERTENDO VALOR DE STRING E BUSCANDO O CODIGO "+codigo);
           try{
               DividaDAO dividaDAO = new DividaDAO();
               return dividaDAO.getDivida(codigo);
           }catch(Exception e){
               throw new ConverterException("Não foi possivel encontrar a dívida "+value+". "+e.getMessage());
           }
       }
       return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
       if(value != null){
           System.out.println("CONVERTENDO PARA STRING " + value);
           Divida divida =  (Divida) value;
           //System.out.println("CONVERTENDO PARA STRING " + String.valueOf(divida.getId()));
           return divida.toString();
       }else
       return "";
    }
    
}
