/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Miguel
 * Essa classe representa a descrição da despesa bem como
 * a data que contraiu a divida, em quantas parcelas foi realizado a compra,
 * o valor da compra, a operação se foi transferencia, ordem bancária e/ou compra
 * com o cartão, a empresa que foi realizado a compra.
 * 
 */
@Entity
@Table(name="divida")
public class Divida implements Serializable{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "iddivida")
   private Integer id;
   @Column(name="descricaodespesa")
   private String descricao;
   @Column(name="datadeinicio")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date datadeinicio;//data de inicio da despesa
   @Column(name="parcela")
   private int parcelas;
   @Column(name="operacaobancaria")
   private String operacao;
   @Column(name="tipolancamento")
   private String tipolancamento;//Se foi Despesa ou Receita
   @Column(name="empresa")
   private String empresa;
   @Column(name="vencimento")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date vencimento;
    /**para facilitar o mecanismo de busca com isso coloca
        mes/ano , por exemplo, 01/2013
                          */
   @Column(name="exercicio")
   private String exercicio;
   @Column(name="observacao")
   private String observacao;
   @Transient
   private boolean ativador;
   /**
    * Uma divida pode ter vários pagamentos e um ou mais de um pagamento 
    * pertence a  uma divida.
    * 
    **/
  //@OneToMany(mappedBy = "id",cascade=CascadeType.ALL, fetch=FetchType.EAGER)   
   // @JoinColumn(name = "dividaid")
   // private List<Pagamento> pagamento;
   
    public Divida() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDatadeinicio() {
        return datadeinicio;
    }

    public void setDatadeinicio(Date datadeinicio) {
        this.datadeinicio = datadeinicio;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipolancamento() {
        return tipolancamento;
    }

    public void setTipolancamento(String tipolancamento) {
        System.out.println("Mudando");
        System.out.println(tipolancamento);
        if(tipolancamento.equals("D")){
            ativador = true;
            System.out.println(ativador);
        }else{
            ativador = false;
            System.out.println(ativador);}
        this.tipolancamento = tipolancamento;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getExercicio() {
        return exercicio;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    /*   public List<Pagamento> getPagamento() {
    return pagamento;
    }
    public void setPagamento(List<Pagamento> pagamento) {
    this.pagamento = pagamento;
    }*/
    public boolean isAtivador() {
        return ativador;
    }

    public void setAtivador(boolean ativador) {
        this.ativador = ativador;
    }
    
     
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        hash = 67 * hash + (this.datadeinicio != null ? this.datadeinicio.hashCode() : 0);
        hash = 67 * hash + this.parcelas;
        hash = 67 * hash + (this.operacao != null ? this.operacao.hashCode() : 0);
        hash = 67 * hash + (this.tipolancamento != null ? this.tipolancamento.hashCode() : 0);
        hash = 67 * hash + (this.empresa != null ? this.empresa.hashCode() : 0);
        hash = 67 * hash + (this.vencimento != null ? this.vencimento.hashCode() : 0);
        hash = 67 * hash + (this.exercicio != null ? this.exercicio.hashCode() : 0);
        hash = 67 * hash + (this.observacao != null ? this.observacao.hashCode() : 0);
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
        final Divida other = (Divida) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        if (this.datadeinicio != other.datadeinicio && (this.datadeinicio == null || !this.datadeinicio.equals(other.datadeinicio))) {
            return false;
        }
        if (this.parcelas != other.parcelas) {
            return false;
        }
        if ((this.operacao == null) ? (other.operacao != null) : !this.operacao.equals(other.operacao)) {
            return false;
        }
        if ((this.tipolancamento == null) ? (other.tipolancamento != null) : !this.tipolancamento.equals(other.tipolancamento)) {
            return false;
        }
        if ((this.empresa == null) ? (other.empresa != null) : !this.empresa.equals(other.empresa)) {
            return false;
        }
        if (this.vencimento != other.vencimento && (this.vencimento == null || !this.vencimento.equals(other.vencimento))) {
            return false;
        }
        if ((this.exercicio == null) ? (other.exercicio != null) : !this.exercicio.equals(other.exercicio)) {
            return false;
        }
        if ((this.observacao == null) ? (other.observacao != null) : !this.observacao.equals(other.observacao)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return  String.valueOf(id);
         }
}
