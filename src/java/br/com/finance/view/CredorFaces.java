/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.view;

import br.com.finance.dao.CredorDAO;
import br.com.finance.model.Credores;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Miguel Lima
 */
@ManagedBean
@ViewScoped
public class CredorFaces {

    private Credores selectedCredor;
    private CredorDAO credorDAO;
    public FacesMessage message;
    private List<Credores> credores;
    
    /**
     * Creates a new instance of CredorFaces
     */
    public CredorFaces() {
    }
    
    @PostConstruct
    public void init(){
        selectedCredor = new Credores();
        credorDAO = new CredorDAO();
        credores = new ArrayList<Credores>();
    }
    
    public void novoCredor(){
        selectedCredor = null;
         message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Já pode adicionar um novo credor!", null);
         FacesContext.getCurrentInstance().addMessage(null, message);
         RequestContext.getCurrentInstance().execute("formcredor");
        selectedCredor = new Credores();
    }
    
     public String readJsonSimpleStream(String urlStr) throws Exception {
        BufferedReader reader = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
             HttpGet request = new HttpGet(urlStr);
            //URL url = new URL(urlStr);
            HttpResponse response = client.execute(request);
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
                
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }
    
    public void buscarEnderecoCredor() throws Exception{
        String urlBusca = "http://apps.widenet.com.br/busca-cep/api/cep.json?code=";
        urlBusca += selectedCredor.getCepcredor().replace("-", "");
        System.out.println("URL: "+urlBusca);
        String result = readJsonSimpleStream(urlBusca);
        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj  = parser.parse(result);
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONObject firstGenre = (JSONObject) array.get(0);
        
        selectedCredor.setBairrocredor(firstGenre.get("district").toString().isEmpty() ? "" : firstGenre.get("district").toString() );
        selectedCredor.setCidade(firstGenre.get("city").toString().isEmpty() ? "" : firstGenre.get("city").toString());
        selectedCredor.setUfcredor(firstGenre.get("state").toString().isEmpty() ? "" : firstGenre.get("state").toString());
        selectedCredor.setEnderecocredor(firstGenre.get("address").toString().isEmpty() ? "" : firstGenre.get("address").toString());
        
        RequestContext.getCurrentInstance().execute("formcredor");
        
    }
    
    public void adicionarCredor(){
        try{
            if(selectedCredor.getTipopessoa().equals("O")){
                selectedCredor.setCpfcnpj("999.999.999-99");
            }
            credorDAO.addCredor(selectedCredor);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Credor adicionado com sucesso!", null);
         FacesContext.getCurrentInstance().addMessage(null, message);
        }catch(Exception e){
            e.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar o credor!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public Credores getSelectedCredor() {
        return selectedCredor;
    }

    public void setSelectedCredor(Credores selectedCredor) {
        this.selectedCredor = selectedCredor;
    }

    public CredorDAO getCredorDAO() {
        return credorDAO;
    }

    public void setCredorDAO(CredorDAO credorDAO) {
        this.credorDAO = credorDAO;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public void setMessage(FacesMessage message) {
        this.message = message;
    }

    public List<Credores> getCredores() {
        return credores;
    }

    public void setCredores(List<Credores> credores) {
        this.credores = credores;
    }
    
}
