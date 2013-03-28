/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class Datas {

     static Date dia = new Date(System.currentTimeMillis());
    public static String horaAtual = new SimpleDateFormat("HH:mm").format(dia);
    public static String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(dia);
    
    public Datas() {
    
    }
}
