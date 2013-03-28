/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class Navegacao {

 
    public Navegacao() {
    }
    
    public String pageDivida(){
        return "nextToDivida";
    }
    public String pageDividaToMenu(){
        return "backToMenu";
    }
    public String pageUser(){
        return "nextToUser";
    }
    public String pageUserToMenu(){
        return "backPageToMenu";
    }
}
