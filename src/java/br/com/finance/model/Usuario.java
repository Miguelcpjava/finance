/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.model;

import br.com.finance.util.Datas;
import br.com.ltn4java.data.DataCompare;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
/**
 *
 * @author Miguel
 */

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 2513289174030204540L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int id;
    @Column(name="loginuser", nullable = false, unique = true)
    private String login;
    @Column(name="passuser", nullable = false)
    private String password;
    @Column(name="nomeusuario")
    private String nome;
    @Column(name="sobrenomeusuario")
    private String sobrenome;
    @Column(name="datacadastro")
    private String datadecadastro = Datas.dataAtual;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="nascimento")
    private Date datanascimento;
    @Column(name="statususuario")
    private String status;
    @Column(name = "regra")
    private String regra;
    
    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {         
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDatadecadastro() {
        return datadecadastro;
    }

    public void setDatadecadastro(String datadecadastro) {
        this.datadecadastro = datadecadastro;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int idadeUser() throws ParseException{
        
        DataCompare dt = new DataCompare();
        String datacomparada = new SimpleDateFormat("dd/MM/yyyy").format(getDatanascimento());
        //dt.compareWithTwoDatesString(datacomparada,Datas.dataAtual ,"dd/MM/yyyy" );
        double valor = dt.compareWithTwoDatesString(Datas.dataAtual,datacomparada ,"dd/MM/yyyy" )/365;
        int idade =  (int)Math.floor(valor);
        return idade;
    }

    public String getRegra() {
        return regra;
    }

    public void setRegra(String regra) {
        this.regra = regra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        hash = 19 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 19 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 19 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 19 * hash + (this.sobrenome != null ? this.sobrenome.hashCode() : 0);
        hash = 19 * hash + (this.datadecadastro != null ? this.datadecadastro.hashCode() : 0);
        hash = 19 * hash + (this.datanascimento != null ? this.datanascimento.hashCode() : 0);
        hash = 19 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 19 * hash + (this.regra != null ? this.regra.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.sobrenome == null) ? (other.sobrenome != null) : !this.sobrenome.equals(other.sobrenome)) {
            return false;
        }
        if ((this.datadecadastro == null) ? (other.datadecadastro != null) : !this.datadecadastro.equals(other.datadecadastro)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if ((this.regra == null) ? (other.regra != null) : !this.regra.equals(other.regra)) {
            return false;
        }
        if (this.datanascimento != other.datanascimento && (this.datanascimento == null || !this.datanascimento.equals(other.datanascimento))) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return  id + "-" + nome + " " + sobrenome;
    }
    
    
}
