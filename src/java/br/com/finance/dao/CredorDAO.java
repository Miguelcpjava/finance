/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.dao;

import br.com.finance.model.Credores;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miguel Lima
 */
public class CredorDAO extends GenericDao implements Serializable{

    private static final long serialVersionUID = -6212517518373507845L;
    
    
    public Long addCredor(Credores credor){
        savingPojo(credor);
        return credor.getIdcredor();
    }
    
    public void updateCredor(Credores credor){
        updatePojo(credor);
    }
    public void deleteCredor(Credores credor){
        removePojo(credor);
    }
    
    public Credores getCredorById(Long CredorID){
        Session sessao = getSession();
        Credores credor = null;
        try{
             Criteria cr = sessao.createCriteria(Credores.class);
             cr.add(Restrictions.eq("idcredor", CredorID));
             credor = new Credores();
             credor = (Credores) cr.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
            credor = null;
        }finally{
            sessao.close();
        }
        return credor;
    }
    
    public List<Credores> getCredores(){
        return getCleanListOfObjects(Credores.class, "From Credores cr");
    }
    
}
