/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Divida;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class DividaDAO extends GenericDao{

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

}
