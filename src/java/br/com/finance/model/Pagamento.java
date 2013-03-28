/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Miguel
 * Esta classe se refere ao histórico de pagamento
 */
@Entity
@Table(name="pagamento")
public class Pagamento implements Serializable {

   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="idpagamento")
   private int id;
   @Temporal(javax.persistence.TemporalType.DATE)
   @Column(name="datapagamento")
   private Date datapagamento;
   @Column(name="valorpagamento")
   private double valor;
   @Column(name="formapgto")
   private String formapagamento;
   @ManyToOne()
   @JoinColumn(name = "iddivida", referencedColumnName = "iddivida")
   private Divida divida;
 
    public Pagamento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + (this.datapagamento != null ? this.datapagamento.hashCode() : 0);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 97 * hash + (this.formapagamento != null ? this.formapagamento.hashCode() : 0);
        hash = 97 * hash + (this.divida != null ? this.divida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagamento other = (Pagamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.datapagamento != other.datapagamento && (this.datapagamento == null || !this.datapagamento.equals(other.datapagamento))) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if ((this.formapagamento == null) ? (other.formapagamento != null) : !this.formapagamento.equals(other.formapagamento)) {
            return false;
        }
        if (this.divida != other.divida && (this.divida == null || !this.divida.equals(other.divida))) {
            return false;
        }
        return true;
    }

  
    @Override
    public String toString() {
        return  datapagamento + " " + valor;
    }

}
