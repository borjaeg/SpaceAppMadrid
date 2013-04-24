package es.tony.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MeteofactDAO {
	
	public static List<AggregateResultsVO> getDataGraphics(Connection connection, 
			TypeMeasure measure, int yearBegin, int yearEnd) {
		ArrayList<AggregateResultsVO> list = null;
		
		try {	
			/* Create "preparedStatement". */
			String field = getFieldEquivalent(measure);
			
			String queryString = 
					"SELECT " +
						"MAX(" + field + ") AS max," +
						"MIN(" + field + ") AS min," +
						"AVG(" + field + ") AS avg " +
					"FROM time_dimension, meteofact " +
					"WHERE anyo BETWEEN ? AND ? " +
						"AND meteofact.idtime = time_dimension.idtime " +
					"GROUP BY anyo " +
					"ORDER BY anyo";
			/*String queryString = 
					"SELECT " +
						"MAX(" + field + ") as max " +
						"MIN(" + field + ") as min " +
						"AVG(" + field + ") as avg " +
					"FROM meteofact " +
					"INNER JOIN time_dimension " +
						"ON time_dimension.idtime = meteofact.idtime " +
					"WHERE anyo BETWEEN ? AND ? " +
					"GROUP BY anyo " +
					"ORDER BY anyo";*/

			PreparedStatement preparedStatement = connection.prepareStatement(queryString,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			
			/* Fill "preparedStatement". */    
			preparedStatement.setInt(1, yearBegin);
			preparedStatement.setInt(2, yearEnd);
			
			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();
			
			/* Iterate over matched rows. */
			list = new ArrayList<AggregateResultsVO>();
			while (resultSet.next()) {
				AggregateResultsVO aggregate = new AggregateResultsVO();
				aggregate.setMax(resultSet.getDouble(1));
				aggregate.setMin(resultSet.getDouble(2));
				aggregate.setAvg(resultSet.getDouble(3));
				list.add(aggregate);
			}
			if (list.size() == 0) list = null;
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		return list;
	}
	
	
	public static double getMeasure(Connection connection, 
			TypeMeasure measure, int latitude, int longitude) {
		double result = 0;
		
		try {	
			/* Create "preparedStatement". */
			String field = getFieldEquivalent(measure);
			
			String queryString = 
					"SELECT " +
						"AVG(" + field + ") AS avg " +
					"FROM location_dimension, meteofact " +
					"WHERE latitude = ? AND longitude = ? " +
						"AND meteofact.idlocation = location_dimension.idlocation " +
						"AND NOT (" + field + " = 0)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(queryString,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			
			/* Fill "preparedStatement". */
			preparedStatement.setInt(1, latitude);
			preparedStatement.setInt(2, longitude);
			
			
			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();
			
			/* Iterate over matched rows. */
			if (resultSet != null && resultSet.first()) {
				result = resultSet.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		return result;
	}
	
	
	private static String getFieldEquivalent(TypeMeasure measure) {
		switch (measure) {
			case SOLAR_RADIATION:
				return new String("horizonalsolarradiation");
			case DEW_FROST:
				return new String("dewfrost");
			case RELATIVE_HUMIDITY:
				return new String("relativehumidity");
			case WIND_SPEED:
				return new String("windspeed");
			case PRECIPITATION:
				return new String("precipitation");
			case TEMPERATURE:
				return new String("temperature");
			default:
				return new String();
		}
	}
}
