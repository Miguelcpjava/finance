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
import javax.persistence.OneToOne;
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
    private static final long serialVersionUID = -5021614988145432451L;

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
   @OneToOne
   @JoinColumn(name="idcredor")
   private Credores credor;
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
   @Column(name="valorpagamentodiv", nullable = true)
   private double valortotal;
   @Transient
   private boolean ativador;
   @Column(name="nivel")
   private String nivel;
   @Transient
   private String nome_mes;
   @Column(name = "status",columnDefinition = "varchar(10) default 'Não Pago'")
   private String status; //Status do pagamento,se é Não Pago ou Pago
   @OneToOne
   @JoinColumn(name = "idcartao")
   private Cartao cartao;
   @Column(name = "pagocartao")
   private boolean pagoCartao; //Se foi pago com algum cartao de Credito
   //Caso for pago por mais de um cartão cadastra n dividas apenas contendo 
   //um cartao
   
    public Divida() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Credores getCredor() {
        return credor;
    }

    public void setCredor(Credores credor) {
        this.credor = credor;
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

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public boolean isAtivador() {
        return ativador;
    }

    public void setAtivador(boolean ativador) {
        this.ativador = ativador;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNome_mes() {
        return nome_mes;
    }

    public void setNome_mes(String nome_mes) {
        this.nome_mes = nome_mes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public boolean isPagoCartao() {
        return pagoCartao;
    }

    public void setPagoCartao(boolean pagoCartao) {
        this.pagoCartao = pagoCartao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        hash = 97 * hash + (this.datadeinicio != null ? this.datadeinicio.hashCode() : 0);
        hash = 97 * hash + this.parcelas;
        hash = 97 * hash + (this.operacao != null ? this.operacao.hashCode() : 0);
        hash = 97 * hash + (this.tipolancamento != null ? this.tipolancamento.hashCode() : 0);
        hash = 97 * hash + (this.credor != null ? this.credor.hashCode() : 0);
        hash = 97 * hash + (this.vencimento != null ? this.vencimento.hashCode() : 0);
        hash = 97 * hash + (this.exercicio != null ? this.exercicio.hashCode() : 0);
        hash = 97 * hash + (this.observacao != null ? this.observacao.hashCode() : 0);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valortotal) ^ (Double.doubleToLongBits(this.valortotal) >>> 32));
        hash = 97 * hash + (this.ativador ? 1 : 0);
        hash = 97 * hash + (this.nivel != null ? this.nivel.hashCode() : 0);
        hash = 97 * hash + (this.nome_mes != null ? this.nome_mes.hashCode() : 0);
        hash = 97 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 97 * hash + (this.cartao != null ? this.cartao.hashCode() : 0);
        hash = 97 * hash + (this.pagoCartao ? 1 : 0);
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
        final Divida other = (Divida) obj;
        if (this.parcelas != other.parcelas) {
            return false;
        }
        if (Double.doubleToLongBits(this.valortotal) != Double.doubleToLongBits(other.valortotal)) {
            return false;
        }
        if (this.ativador != other.ativador) {
            return false;
        }
        if (this.pagoCartao != other.pagoCartao) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        if ((this.operacao == null) ? (other.operacao != null) : !this.operacao.equals(other.operacao)) {
            return false;
        }
        if ((this.tipolancamento == null) ? (other.tipolancamento != null) : !this.tipolancamento.equals(other.tipolancamento)) {
            return false;
        }
        if ((this.exercicio == null) ? (other.exercicio != null) : !this.exercicio.equals(other.exercicio)) {
            return false;
        }
        if ((this.observacao == null) ? (other.observacao != null) : !this.observacao.equals(other.observacao)) {
            return false;
        }
        if ((this.nivel == null) ? (other.nivel != null) : !this.nivel.equals(other.nivel)) {
            return false;
        }
        if ((this.nome_mes == null) ? (other.nome_mes != null) : !this.nome_mes.equals(other.nome_mes)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.datadeinicio != other.datadeinicio && (this.datadeinicio == null || !this.datadeinicio.equals(other.datadeinicio))) {
            return false;
        }
        if (this.credor != other.credor && (this.credor == null || !this.credor.equals(other.credor))) {
            return false;
        }
        if (this.vencimento != other.vencimento && (this.vencimento == null || !this.vencimento.equals(other.vencimento))) {
            return false;
        }
        if (this.cartao != other.cartao && (this.cartao == null || !this.cartao.equals(other.cartao))) {
            return false;
        }
        return true;
    }

  
    @Override
    public String toString() {
        return  String.valueOf(id);
         }
}
