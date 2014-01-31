/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.util;

import br.com.finance.dao.DividaDAO;
import br.com.finance.model.Divida;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped
public class Reportutil {
    
    DividaDAO divDAO;
    List<Divida> listaDeDividas = new ArrayList<Divida>();
    FacesMessage msg;
    public Connection Conn;
     private static final String url = "jdbc:mysql://localhost:3306/financef";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String login = "root";
    private static final String pwd = "root";
    /**
     * Creates a new instance of Reportutil
     */
    public Reportutil() {
    }
     public Connection getConn() throws SQLException, ClassNotFoundException {
                Class.forName( driver );
		Conn = DriverManager.getConnection( url , login , pwd );
                Conn.isClosed();
        return Conn;
    }
    public void imprimirRelatorio(int argumento1,int argumento2, String nomedorelatorio, String nomedocampoparaarguemntacao, String nomedegravacaodorelatorio) throws IOException, JRException, ClassNotFoundException, Exception {
        ServletOutputStream servletOutputStream;
        File arquivoGerado;
        System.out.println("Entrou no Metodo");

        HashMap parameters = new HashMap();
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String caminhorelatorio = facesContext.getExternalContext().getRealPath("relatorio");
            System.out.println(nomedocampoparaarguemntacao + " e argumento "+argumento1);
            parameters.put(nomedocampoparaarguemntacao, argumento1);
            System.out.println("ano e argumento "+argumento2);
            parameters.put("ano", argumento2);

            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getResourceAsStream("/relatorio/" + nomedorelatorio + ".jasper"), parameters, getConn());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            String caminhoArquivoRelatorio = caminhorelatorio + File.separator + nomedegravacaodorelatorio + ".pdf";
            arquivoGerado = new File(caminhoArquivoRelatorio);

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

            exporter.exportReport();
            arquivoGerado.deleteOnExit();
            System.out.println("Fim da exportação");

            byte[] bytes = baos.toByteArray();
            System.out.println("Tamanho: " + bytes.length + " Bytes");
            if (bytes != null && bytes.length > 0) {
                System.out.println("Escrevendo bytes!");
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.reset();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=" + nomedegravacaodorelatorio + ".pdf");
                response.setContentLength(bytes.length);
                servletOutputStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                servletOutputStream.write(bytes, 0, bytes.length);
                servletOutputStream.flush();
                servletOutputStream.close();
                facesContext.renderResponse();
                facesContext.responseComplete();
                System.out.println("Relatório gerado!");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falta do arquivo para geração do relatório!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        } catch (JRException jreE) {
            jreE.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no Ireport!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Biblioteca que não Existe!", null);
            FacesContext.getCurrentInstance().addMessage("add", msg);
        }
    }
}   