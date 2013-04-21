package es.tony;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import es.tony.persistence.AggregateSolarRadiationVO;
import es.tony.persistence.ConnectionManager;
import es.tony.persistence.JSON_converter;
import es.tony.persistence.MeteofactDAO;


public class ServletAjaxGraficos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int MIN_YEAR = 1983;
	
	private final static Logger log = Logger.getLogger(ServletAjaxGraficos.class);
	
	public ServletAjaxGraficos() {
		super();
	} 
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {	
    	String strYearBegin	= request.getParameter("yearBegin");
        String strYearEnd	= request.getParameter("yearEnd");
        
        int yearBegin, yearEnd;
        
        // Comprobamos los datos
        Calendar calendar = Calendar.getInstance();
        int year_now = calendar.get(Calendar.YEAR);
        
        if (strYearBegin != null && strYearBegin != "" && 
        		strYearEnd != null && strYearEnd != "" &&
        		(yearBegin = isNumeric(strYearBegin)) >= MIN_YEAR &&
        		(yearEnd = isNumeric(strYearEnd)) <= year_now &&
        		yearEnd >= yearBegin) 
        {
        	// Recoge los datos de la BD en una lista
        	Connection connection;
    		try {
    			connection = ConnectionManager.getConnection();
    			List<AggregateSolarRadiationVO> list = 
    					MeteofactDAO.getDataGraphics(connection, yearBegin, yearEnd);
    			
    			response.setContentType("text/json");
                PrintWriter out = response.getWriter();
                
                // Devolvemos los resultados de la lista de 
                //   AggregateSolarRadiationVO como objectos JSON
                out.print(JSON_converter.listToJSON(list));  
  
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			log.error("Error AJAX. Acceso a la BD");
    			errorJson(response); return;
    		}
        	
        } else {
        	log.error("Error AJAX. Tipos invalidos");
        	errorJson(response); return;
        }
    }
    
    
    private static int isNumeric(String cadena) {
		try {
			return Integer.parseInt(cadena);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}
    
    
    @SuppressWarnings("unchecked")
	private static void errorJson(HttpServletResponse response) {
    	JSONObject o = new JSONObject();
    	o.put("error", "error");
    	response.setContentType("text/json");
        PrintWriter out;
		try {
			out = response.getWriter();
			out.print(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
} 