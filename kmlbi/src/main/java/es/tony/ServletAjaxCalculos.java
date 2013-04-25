package es.tony;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tony.dataProcesing.RelevantInfo;
import es.tony.persistence.ConnectionManager;
import es.tony.persistence.MeteofactDAO;
import es.tony.persistence.TypeMeasure;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;



public class ServletAjaxCalculos extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(ServletAjaxCalculos.class);
	
	
	public ServletAjaxCalculos() {
		super();
	} 
	
    @SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {	
    	String strSurface		= request.getParameter("surface");
        String strGenerators	= request.getParameter("generators");
        String strLatitude		= request.getParameter("latitude");
        String strLongitude		= request.getParameter("longitude");
        String strEfficiency	= request.getParameter("efficiency");
        
        double surface, latitude, longitude;
        int generators, efficiency;
        
        if (strSurface != null && strSurface != "" && 
    		strGenerators != null && strGenerators != "" &&
    		strLatitude != null && strLatitude != "" &&
    		strLongitude != null && strLongitude != "" &&
    		strEfficiency != null && strEfficiency != "" &&
    		(surface = isDouble(strSurface)) != -1 &&
    		(generators = isNumeric(strGenerators)) != -1 &&
    		(latitude = isDouble(strLatitude)) != -1 &&
    		(longitude = isDouble(strLongitude))!= -1 &&
    		(efficiency = isNumeric(strEfficiency)) != -1 &&
    		efficiency >= 0 && efficiency <= 2)
        {
        	// Redondeamos los datos
        	BigDecimal bd;
        	bd = new BigDecimal(latitude);
        	int rlatitude = bd.intValue();
        	bd = new BigDecimal(longitude);
        	int rlongitude = bd.intValue();
        	
        	// Recoge los datos de la BD en un objeto MeteofactVO
        	Connection connection;
    		try {
    			connection = ConnectionManager.getConnection();
    			
    			double solarRadiation, windSpeed;

    			solarRadiation = MeteofactDAO.getMeasure(connection, TypeMeasure.SOLAR_RADIATION, rlatitude, rlongitude);
    			windSpeed = MeteofactDAO.getMeasure(connection, TypeMeasure.WIND_SPEED, rlatitude, rlongitude);
                
    			
    			double energyGenerated;
    			double homeEquivalent;
    			double monetaryEquivalent;
    			double installationCos;
    			int timeRecover;
    			
    			// Realizamos los calculos
    			RelevantInfo calculus = new RelevantInfo(efficiency);
    			
    			energyGenerated = calculus.energyGenerated(surface, solarRadiation, windSpeed, generators);
    			homeEquivalent = calculus.homeEquivalent();
    			monetaryEquivalent = calculus.monetaryEquivalent();
    			installationCos = calculus.facilityCost(surface, generators);
    			timeRecover = calculus.timeToRecoverTheInversion();

    			
                // Creamos el objeto JSON con los resultados
    			JSONObject o = new JSONObject();
    	    	
    	    	response.setContentType("text/json");
    	        PrintWriter out = response.getWriter();
    	        o.put("energyGenerated", energyGenerated);
    	        o.put("homeEquivalent", homeEquivalent);
    	        o.put("monetaryEquivalent", monetaryEquivalent);
    	        o.put("installationCos", installationCos);
    	        o.put("timeRecover", timeRecover);
    	        out.print(o);
  
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
    
    
    private static double isDouble(String cadena) {
		try {
			return Double.parseDouble(cadena);
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
