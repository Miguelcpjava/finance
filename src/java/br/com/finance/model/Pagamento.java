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
    private static final long serialVersionUID = 8582239242058407438L;

   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="idpagamento")
   private Integer id;
   @Temporal(javax.persistence.TemporalType.DATE)
   @Column(name="datapagamento")
   private Date datapagamento;
   @Column(name="valorpagamento")
   private double valor;
   @Column(name="status")
   private String statuspagamento;
   @Column(name="formapgto")
   private String formapagamento;
   @ManyToOne()
   @JoinColumn(name = "iddivida")
   private Divida divida;
   @Column(name = "observacao")
   private String observacao;
 
    public Pagamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getStatuspagamento() {
        return statuspagamento;
    }

    public void setStatuspagamento(String statuspagamento) {
        this.statuspagamento = statuspagamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 83 * hash + (this.datapagamento != null ? this.datapagamento.hashCode() : 0);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 83 * hash + (this.statuspagamento != null ? this.statuspagamento.hashCode() : 0);
        hash = 83 * hash + (this.formapagamento != null ? this.formapagamento.hashCode() : 0);
        hash = 83 * hash + (this.divida != null ? this.divida.hashCode() : 0);
        hash = 83 * hash + (this.observacao != null ? this.observacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagamento other = (Pagamento) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if ((this.statuspagamento == null) ? (other.statuspagamento != null) : !this.statuspagamento.equals(other.statuspagamento)) {
            return false;
        }
        if ((this.formapagamento == null) ? (other.formapagamento != null) : !this.formapagamento.equals(other.formapagamento)) {
            return false;
        }
        if ((this.observacao == null) ? (other.observacao != null) : !this.observacao.equals(other.observacao)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.datapagamento != other.datapagamento && (this.datapagamento == null || !this.datapagamento.equals(other.datapagamento))) {
            return false;
        }
        if (this.divida != other.divida && (this.divida == null || !this.divida.equals(other.divida))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
