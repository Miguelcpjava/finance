/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.view;

import br.com.finance.dao.CartaoDAO;
import br.com.finance.model.Cartao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Miguel Lima
 */
@ManagedBean
@ViewScoped
public class CartaoFaces {

    
    private Cartao card;
    private CartaoDAO cardDAO;
    public FacesMessage message;
    private List<Cartao> listOfCards;
    private List<Cartao> listCardByUsers;
    
    
    /**
     * Creates a new instance of CartaoFaces
     */
    public CartaoFaces() {
    
    }
    
    @PostConstruct
    public void init(){
        card = new Cartao();
        cardDAO = new CartaoDAO();
        listOfCards = new ArrayList<Cartao>();
        listCardByUsers = new ArrayList<Cartao>();
    }
    
    public void novoCartao(){
        card = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Já pode adicionar um novo cartão!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
            card = new Cartao();
    }
    public List<Cartao> getListCardByUser(){
        if(listCardByUsers.isEmpty() || listCardByUsers == null){
            listCardByUsers = cardDAO.getListCardByUsers("mlima");
        }
        return listCardByUsers;
    }
    public List<Cartao> preenchimentoListaCards(){
        if(listOfCards.isEmpty() || listOfCards == null){
            listOfCards = cardDAO.getListCardByUsers("mlima");
        }
        return listOfCards;
    }
    
    public void adicionarCartao(){
        try{
            cardDAO.addCartao(card);
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao cadastrar o cartão!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
            System.out.println("Erro br.com.finance.view.CartaoFaces.adicionarCartao()");
            e.printStackTrace();
        }
    }
    
    public void atualizarCartao(){
        try{
            cardDAO.updateCartao(card);
              message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cartão atualizado com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        }catch(Exception e){
            e.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar o cartão!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        }
    }
    public void removerCartao(){
          try{
            cardDAO.deleteCartao(card);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cartão removido com sucesso!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        }catch(Exception e){
            e.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao remover o cartão!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        }
    }
    /**
     * Metodo para atribui as bandeiras dos cartões
     * @return 
     */
    public void bandeiraVisa(){
        card.setBandeira("Visa");
         message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bandeira Visa!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
    }
    public void bandeiraMasterCard(){
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bandeira MasterCard!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        card.setBandeira("MasterCard");
    }
    public void bandeiraDinersClub(){
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bandeira Diners Club!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        card.setBandeira("Diners Club");
    }
    public void bandeiraAmerican(){
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bandeira American Express!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        card.setBandeira("American Express");
    }
    public void bandeiraElo(){
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bandeira Elo!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        card.setBandeira("Elo");
    }
    public void bandeiraHiper(){
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bandeira HiperCard!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        card.setBandeira("HiperCard");
    }
    public void bandeiraAura(){
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bandeira Aura!", null);
            FacesContext.getCurrentInstance().addMessage("add", message);
        card.setBandeira("Aura");
    }
    
    public Cartao getCard() {
        return card;
    }

    public void setCard(Cartao card) {
        this.card = card;
    }

    public CartaoDAO getCardDAO() {
        return cardDAO;
    }

    public void setCardDAO(CartaoDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public void setMessage(FacesMessage message) {
        this.message = message;
    }

    public List<Cartao> getListOfCards() {
        return listOfCards;
    }

    public void setListOfCards(List<Cartao> listOfCards) {
        this.listOfCards = listOfCards;
    }

    public List<Cartao> getListCardByUsers() {
        return listCardByUsers;
    }

    public void setListCardByUsers(List<Cartao> listCardByUsers) {
        this.listCardByUsers = listCardByUsers;
    }
    
    
    
}
