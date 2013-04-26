package es.tony.persistence;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class JSON_converter {
	
	@SuppressWarnings("unchecked")
	public static JSONObject listToJSON(List<AggregateResultsVO> list) {
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
