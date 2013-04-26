package es.tony.modelView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import es.tony.dataProcesing.Calculus;
import es.tony.persistence.AggregateResultsVO;
import es.tony.persistence.ConnectionManager;
import es.tony.persistence.MeteofactDAO;
import es.tony.persistence.TypeMeasure;

public class Facade {
	
	private final static Logger log = Logger.getLogger(Facade.class);
	
	
	public JSONObject processGraphicRequest(GraphicRequest request) {		
		Connection connection;
		try {
			connection = ConnectionManager.getConnection();
			List<AggregateResultsVO> list = 
					MeteofactDAO.getDataGraphics(
							connection, 
							request.getTypeMeasure(),
							request.getYearFrom(),
							request.getYearTo(),
							request.getScale(),
							request.getLatitude(),
							request.getLongitude()
					);
			
			if (list != null) {
				return listToJSON(list);
				
			} else {
				log.error("Error recogiendo los datos de la BD. " + 
						request.getTypeMeasure()  + " - " + 
						request.getYearFrom() + " - " + request.getYearTo());
    			return null;
			}
		
		} catch (SQLException e) {
			log.error("Error AJAX. Acceso a la BD");
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject processCalculusRequest(CalculusRequest request) {
		Connection connection;
		try {
			connection = ConnectionManager.getConnection();
			
			double solarRadiation, windSpeed;
	
			solarRadiation = MeteofactDAO.getMeasure(connection, 
					TypeMeasure.SOLAR_RADIATION, request.getLatitude(), 
					request.getLongitude());
			windSpeed = MeteofactDAO.getMeasure(connection, 
					TypeMeasure.WIND_SPEED, request.getLatitude(), 
					request.getLongitude());
	        
			
			double energyGeneratedSolar, energyGeneratedWind, energyGeneratedTotal;
			double homeEquivalentSolar, homeEquivalentWind, homeEquivalentTotal;
			double monetaryEquivalentSolar, monetaryEquivalentWind, monetaryEquivalentTotal;
			double installationCosSolar, installationCosWind, installationCosTotal;
			int timeRecoverSolar, timeRecoverWind, timeRecoverTotal;
	
			
			// Realizamos los calculos
			
			// Solar
			energyGeneratedSolar = 
					Calculus.energyGeneratedSolar(request.getSurface(), 
							solarRadiation, request.getEfficiency());
			homeEquivalentSolar = 
					Calculus.homeEquivalent(energyGeneratedSolar);
			monetaryEquivalentSolar = 
					Calculus.monetaryEquivalent(energyGeneratedSolar);
			installationCosSolar = 
					Calculus.facilitySolarCost(request.getSurface(), 
							request.getEfficiency());
			timeRecoverSolar = 
					Calculus.timeToRecoverTheInversion(installationCosSolar, monetaryEquivalentSolar);
			
			// Wind
			energyGeneratedWind = 
					Calculus.energyGeneratedWind(windSpeed, 
							request.getGenerators(), request.getEfficiency());
			homeEquivalentWind = 
					Calculus.homeEquivalent(energyGeneratedWind);
			monetaryEquivalentWind = 
					Calculus.monetaryEquivalent(energyGeneratedWind);
			installationCosWind = 
					Calculus.facilityWindCost(request.getGenerators(), request.getEfficiency());
			timeRecoverWind = 
					Calculus.timeToRecoverTheInversion(installationCosWind, monetaryEquivalentWind);
			
			// Total
			energyGeneratedTotal = 
					energyGeneratedSolar + energyGeneratedWind;
			homeEquivalentTotal = 
					Calculus.homeEquivalent(energyGeneratedTotal);
			monetaryEquivalentTotal = 
					Calculus.monetaryEquivalent(energyGeneratedTotal);
			installationCosTotal = 
					installationCosSolar + installationCosWind;
			timeRecoverTotal = 
					Calculus.timeToRecoverTheInversion(installationCosTotal, monetaryEquivalentTotal);
			
	
			
	        // Creamos el objeto JSON con los resultados
			JSONObject o = new JSONObject();
	        
	        o.put("energyGeneratedSolar", energyGeneratedSolar/1000);	// /1000 MWh
	        o.put("homeEquivalentSolar", homeEquivalentSolar);
	        o.put("monetaryEquivalentSolar", monetaryEquivalentSolar);
	        o.put("installationCosSolar", installationCosSolar);
	        o.put("timeRecoverSolar", timeRecoverSolar);
	        
	        o.put("energyGeneratedWind", energyGeneratedWind/1000);		// /1000 MWh
	        o.put("homeEquivalentWind", homeEquivalentWind);
	        o.put("monetaryEquivalentWind", monetaryEquivalentWind);
	        o.put("installationCosWind", installationCosWind);
	        o.put("timeRecoverWind", timeRecoverWind);
	        
	        o.put("energyGeneratedTotal", energyGeneratedTotal/1000);	// /1000 MWh
	        o.put("homeEquivalentTotal", homeEquivalentTotal);
	        o.put("monetaryEquivalentTotal", monetaryEquivalentTotal);
	        o.put("installationCosTotal", installationCosTotal);
	        o.put("timeRecoverTotal", timeRecoverTotal);
	        
			return o;
			
		} catch (SQLException e) {
			log.error("Error AJAX. Acceso a la BD");
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private static JSONObject listToJSON(List<AggregateResultsVO> list) {
		JSONObject json = new JSONObject();
		JSONArray max = new JSONArray();
		JSONArray min = new JSONArray();
		JSONArray avg = new JSONArray();
		
		Iterator<AggregateResultsVO> it = list.iterator();		
		while (it.hasNext()) {
			AggregateResultsVO data = it.next();
			max.add(data.getMax());
			min.add(data.getMin());
			avg.add(data.getAvg());
		}
		
		json.put("max", max);
		json.put("min", min);
		json.put("avg", avg);
		
		return json;
	}
}
