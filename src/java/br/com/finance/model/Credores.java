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
 */
@Entity
@Table(name = "credores")
public class Credores implements Serializable{

    private static final long serialVersionUID = 3759325897627325585L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcredor;
    @Column(name = "nomecredor")
    private String nomeCredor;
    @Column(name = "cpfcnpj",unique = true, nullable = false, length = 18)
    private String cpfcnpj;
    @Column(name = "tipopessoa")
    private String tipopessoa;
    @Column(name = "uf")
    private String ufcredor;
    @Column(name = "enderco")
    private String enderecocredor;
    @Column(name = "numero")
    private String numeroendereco;
    @Column(name = "bairro")
    private String bairrocredor;
    @Column(name = "cep")
    private String cepcredor;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @Column(name = "dataincluscao")
    private String datainclusao;
    
    public Long getIdcredor() {
        return idcredor;
    }

    public void setIdcredor(Long idcredor) {
        this.idcredor = idcredor;
    }

    public String getNomeCredor() {
        return nomeCredor;
    }

    public void setNomeCredor(String nomeCredor) {
        this.nomeCredor = nomeCredor;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getUfcredor() {
        return ufcredor;
    }

    public void setUfcredor(String ufcredor) {
        this.ufcredor = ufcredor;
    }

    public String getBairrocredor() {
        return bairrocredor;
    }

    public void setBairrocredor(String bairrocredor) {
        this.bairrocredor = bairrocredor;
    }

    public String getCepcredor() {
        return cepcredor;
    }

    public void setCepcredor(String cepcredor) {
        this.cepcredor = cepcredor;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatainclusao() {
        return datainclusao;
    }

    public void setDatainclusao(String datainclusao) {
        this.datainclusao = datainclusao;
    }

    public String getEnderecocredor() {
        return enderecocredor;
    }

    public void setEnderecocredor(String enderecocredor) {
        this.enderecocredor = enderecocredor;
    }

    public String getNumeroendereco() {
        return numeroendereco;
    }

    public void setNumeroendereco(String numeroendereco) {
        this.numeroendereco = numeroendereco;
    }

    public String getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(String tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.idcredor != null ? this.idcredor.hashCode() : 0);
        hash = 41 * hash + (this.nomeCredor != null ? this.nomeCredor.hashCode() : 0);
        hash = 41 * hash + (this.cpfcnpj != null ? this.cpfcnpj.hashCode() : 0);
        hash = 41 * hash + (this.tipopessoa != null ? this.tipopessoa.hashCode() : 0);
        hash = 41 * hash + (this.ufcredor != null ? this.ufcredor.hashCode() : 0);
        hash = 41 * hash + (this.enderecocredor != null ? this.enderecocredor.hashCode() : 0);
        hash = 41 * hash + (this.numeroendereco != null ? this.numeroendereco.hashCode() : 0);
        hash = 41 * hash + (this.bairrocredor != null ? this.bairrocredor.hashCode() : 0);
        hash = 41 * hash + (this.cepcredor != null ? this.cepcredor.hashCode() : 0);
        hash = 41 * hash + (this.cidade != null ? this.cidade.hashCode() : 0);
        hash = 41 * hash + (this.telefone != null ? this.telefone.hashCode() : 0);
        hash = 41 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 41 * hash + (this.datainclusao != null ? this.datainclusao.hashCode() : 0);
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
        final Credores other = (Credores) obj;
        if ((this.nomeCredor == null) ? (other.nomeCredor != null) : !this.nomeCredor.equals(other.nomeCredor)) {
            return false;
        }
        if ((this.cpfcnpj == null) ? (other.cpfcnpj != null) : !this.cpfcnpj.equals(other.cpfcnpj)) {
            return false;
        }
        if ((this.tipopessoa == null) ? (other.tipopessoa != null) : !this.tipopessoa.equals(other.tipopessoa)) {
            return false;
        }
        if ((this.ufcredor == null) ? (other.ufcredor != null) : !this.ufcredor.equals(other.ufcredor)) {
            return false;
        }
        if ((this.enderecocredor == null) ? (other.enderecocredor != null) : !this.enderecocredor.equals(other.enderecocredor)) {
            return false;
        }
        if ((this.numeroendereco == null) ? (other.numeroendereco != null) : !this.numeroendereco.equals(other.numeroendereco)) {
            return false;
        }
        if ((this.bairrocredor == null) ? (other.bairrocredor != null) : !this.bairrocredor.equals(other.bairrocredor)) {
            return false;
        }
        if ((this.cepcredor == null) ? (other.cepcredor != null) : !this.cepcredor.equals(other.cepcredor)) {
            return false;
        }
        if ((this.cidade == null) ? (other.cidade != null) : !this.cidade.equals(other.cidade)) {
            return false;
        }
        if ((this.telefone == null) ? (other.telefone != null) : !this.telefone.equals(other.telefone)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.datainclusao == null) ? (other.datainclusao != null) : !this.datainclusao.equals(other.datainclusao)) {
            return false;
        }
        if (this.idcredor != other.idcredor && (this.idcredor == null || !this.idcredor.equals(other.idcredor))) {
            return false;
        }
        return true;
    }

    
    
    
}
