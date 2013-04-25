package es.tony;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

import es.tony.persistence.AggregateResultsVO;
import es.tony.persistence.ConnectionManager;
import es.tony.persistence.JSON_converter;
import es.tony.persistence.MeteofactDAO;
import es.tony.persistence.TypeMeasure;


public class ServletAjaxGraficos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int MIN_YEAR = 1983;
	
	private final static Logger log = Logger.getLogger(ServletAjaxGraficos.class);
	
	public ServletAjaxGraficos() {
		super();
	} 
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {	
    	String strMeasure	= request.getParameter("measure");
    	String strYearFrom	= request.getParameter("yearFrom");
        String strYearTo	= request.getParameter("yearTo");
        String strScale		= request.getParameter("scale");
        String strLatitude		= request.getParameter("latitude");
        String strLongitude		= request.getParameter("longitude");
        
        int measure, yearFrom, yearTo, scale;
        double latitude = 0;
        double longitude = 0;
        
        // Comprobamos los datos        
        if (strYearFrom != null && strYearFrom != "" && 
        		strYearTo != null && strYearTo != "" &&
        		(yearFrom = isNumeric(strYearFrom)) != -1 &&
        		(yearTo = isNumeric(strYearTo)) != -1 &&
        		correctYears(yearFrom, yearTo) &&
        		
        		strMeasure != null && strMeasure != "" &&
        		(measure = isNumeric(strMeasure)) != -1 &&
        		measure >=1 && measure <=6 &&
        		
        		strScale != null && strScale != "" &&
        		(scale = isNumeric(strScale)) != -1 &&
        		(scale == 1 || (scale == 2 && 
        		(latitude = isDouble(strLatitude)) != -1 &&
        	    (longitude = isDouble(strLongitude))!= -1)))
        {
        	// Recoge los datos de la BD en una lista
        	Connection connection;
    		try {
    			TypeMeasure m;
    			switch (measure) {
    				case 1:
    					m = TypeMeasure.SOLAR_RADIATION;
    					break;
    				case 2:
    					m = TypeMeasure.DEW_FROST;
    					break;
    				case 3:
    					m = TypeMeasure.RELATIVE_HUMIDITY;
    					break;
    				case 4:
    					m = TypeMeasure.WIND_SPEED;
    					break;
    				case 5:
    					m = TypeMeasure.PRECIPITATION;
    					break;
    				case 6:
    					m = TypeMeasure.TEMPERATURE;
    					break;
    				default:
    					m = TypeMeasure.ERROR;
    			}
    			
				// Redondeamos los datos
	        	BigDecimal bd;
	        	bd = new BigDecimal(latitude);
	        	int rlatitude = bd.intValue();
	        	bd = new BigDecimal(longitude);
	        	int rlongitude = bd.intValue();
    			
    			connection = ConnectionManager.getConnection();
    			List<AggregateResultsVO> list = 
    					MeteofactDAO.getDataGraphics(connection, m, yearFrom, yearTo,
    							scale, rlatitude, rlongitude);
    			
    			if (list != null) {
    				response.setContentType("text/json");
    				PrintWriter out = response.getWriter();
                
    				// Devolvemos los resultados de la lista de 
    				//   AggregateResultsVO como objectos JSON
    				out.print(JSON_converter.listToJSON(list));
    			} else {
    				log.error("Error recogiendo los datos de la BD. " + 
    						measure + " - " + yearFrom + " - " + yearTo);
        			errorJson(response); return;
    			}
  
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
    
    
    private static boolean correctYears(int yearFrom, int yearTo) {
        Calendar calendar = Calendar.getInstance();
        int year_now = calendar.get(Calendar.YEAR);
        return (yearTo >= yearFrom && yearFrom >= MIN_YEAR && yearTo <= year_now);
    }
} 