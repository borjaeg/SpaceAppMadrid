package es.tony.persistence;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JSON_converter {
	
	@SuppressWarnings("unchecked")
	public static JSONObject listToJSON(List<AggregateSolarRadiationVO> list) {
		JSONObject json = new JSONObject();
		JSONArray max = new JSONArray();
		JSONArray min = new JSONArray();
		JSONArray avg = new JSONArray();
		
		Iterator<AggregateSolarRadiationVO> it = list.iterator();		
		while (it.hasNext()) {
			AggregateSolarRadiationVO data = it.next();
			max.add(data.getSunRadiationMax());
			min.add(data.getSunRadiationMin());
			avg.add(data.getSunRadiationAvg());
		}
		
		json.put("max", max);
		json.put("min", min);
		json.put("avg", avg);
		
		return json;
	}
}
