/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Miguel Lima
 * Reponsavel pelo cadastro de cartão, pois quando você cadastra uma divida
 * tem um check para dizer se é um gasto do cartão, agora com esta classe você
 * vai dizer em qual cartão foi o gasto;
 */
@Entity
@Table(name = "cartao")
public class Cartao implements Serializable{

    private static final long serialVersionUID = -7302167357172967332L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcartao;
    @Column(name = "descricao")
    private String descricao; //apelido do cartao
    @Column(name = "bandeira")
    private String bandeira; //Bandeira do cartão
    @Column(name = "ativo")
    private boolean ativo;
    @Column(name="usuario")
    private String usuario;

    public Long getIdcartao() {
        return idcartao;
    }

    public void setIdcartao(Long idcartao) {
        this.idcartao = idcartao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.idcartao != null ? this.idcartao.hashCode() : 0);
        hash = 79 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        hash = 79 * hash + (this.bandeira != null ? this.bandeira.hashCode() : 0);
        hash = 79 * hash + (this.ativo ? 1 : 0);
        hash = 79 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
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
        final Cartao other = (Cartao) obj;
        if (this.ativo != other.ativo) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        if ((this.bandeira == null) ? (other.bandeira != null) : !this.bandeira.equals(other.bandeira)) {
            return false;
        }
        if ((this.usuario == null) ? (other.usuario != null) : !this.usuario.equals(other.usuario)) {
            return false;
        }
        if (this.idcartao != other.idcartao && (this.idcartao == null || !this.idcartao.equals(other.idcartao))) {
            return false;
        }
        return true;
    }

        
}
