/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.convertpdf;

import java.io.*;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.apache.log4j.Logger;
import ru.athena.ws.edss.EdssPortType;
import ru.athena.ws.edss.EdssService;

/**
 *
 * @author panfilov_ms
 */
@WebServlet
public class MainServlet extends HttpServlet {
    
    private static Logger logger = Logger.getLogger(MainServlet.class.getName());
    private static String TXT_FILE_PATH;
    private static String TXT_IN_NAME;
    Loader ld = null;
    
    @WebServiceRef(wsdlLocation = "http://v-ibank-gate:9080/ws/edss/EdssService/EdssService.wsdl")
    public EdssService service;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    
    @Override
    public void init() throws ServletException {
        //super.init();
        
        Properties props = new Properties();
		try {
			System.out.println(new File(WebInfGetter.getWebInfPath()
					+ "/ArchivePay.properties").getAbsolutePath());
			props.load(new FileInputStream(new File(WebInfGetter
					.getWebInfPath() + "/ArchivePay.properties")));
			TXT_FILE_PATH = props.getProperty("TXTPATH");
                        TXT_IN_NAME = props.getProperty("TXTINNAME");
		} catch (FileNotFoundException e) {
			logger.error("Exception loading xml file" + e.getMessage());
		} catch (IOException e) {
			logger.error("Exception loading properties file" + e.getMessage());
		}
                
                    EdssPortType port = service.getEdssPort();
                    ld = new Loader(TXT_FILE_PATH,TXT_IN_NAME,port);
                    ld.start();
                    
        }

    @Override
    public void destroy() {
        //super.destroy();
        logger.info("exit");
        ld.stopMe();
        
    }
    
    
        

    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
