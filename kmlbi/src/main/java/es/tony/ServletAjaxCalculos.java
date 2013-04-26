package es.tony;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tony.modelView.CalculusRequest;
import es.tony.modelView.Facade;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;



public class ServletAjaxCalculos extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(ServletAjaxCalculos.class);
	
	
	public ServletAjaxCalculos() {
		super();
	} 
	
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
        	
        	// Objeto request
        	CalculusRequest req = new CalculusRequest(
        			surface,
        			generators,
        			efficiency,
        			rlatitude,
        			rlongitude
        	);
        	
        	Facade facade = new Facade();
        	
        	JSONObject o = facade.processCalculusRequest(req);
        	
        	if (o != null) {
    	    	response.setContentType("text/json");
    	        PrintWriter out = response.getWriter();
    	        out.print(o);
        	} else {
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
