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
public class Sistema {
    
    public String VERSION = "0.2";
    public String NOMESISTEMA = "FINANCÉ";
    public String VERSION_UPDATE = "2";
    public String LAST_UPDATE = "19/03/2013";
    public String DATA_UPDATE = "23/04/2013";
    
    public Sistema() {
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getNOMESISTEMA() {
        return NOMESISTEMA;
    }

    public void setNOMESISTEMA(String NOMESISTEMA) {
        this.NOMESISTEMA = NOMESISTEMA;
    }

    public String getVERSION_UPDATE() {
        return VERSION_UPDATE;
    }

    public void setVERSION_UPDATE(String VERSION_UPDATE) {
        this.VERSION_UPDATE = VERSION_UPDATE;
    }

    public String getDATA_UPDATE() {
        return DATA_UPDATE;
    }

    public void setDATA_UPDATE(String DATA_UPDATE) {
        this.DATA_UPDATE = DATA_UPDATE;
    }

    public String getLAST_UPDATE() {
        return LAST_UPDATE;
    }

    public void setLAST_UPDATE(String LAST_UPDATE) {
        this.LAST_UPDATE = LAST_UPDATE;
    }
}
