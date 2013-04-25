package es.tony.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MeteofactDAO {
	
	public static List<AggregateResultsVO> getDataGraphics(Connection connection, 
			TypeMeasure measure, int yearFrom, int yearTo, 
			int scale, int latitude, int longitude) 
	{
		ArrayList<AggregateResultsVO> list = null;
		
		try {	
			/* Create "preparedStatement". */
			String field = getFieldEquivalent(measure);
			
			String queryString;
			PreparedStatement preparedStatement = null;
			
			switch (scale) {
				case 1: // national
					queryString = 
					"SELECT " +
						"MAX(" + field + ") AS max," +
						"MIN(" + field + ") AS min," +
						"AVG(" + field + ") AS avg " +
					"FROM time_dimension, meteofact " +
					"WHERE NOT (" + field + " = 0) " +
						"AND anyo BETWEEN ? AND ? " +
						"AND meteofact.idtime = time_dimension.idtime " +

					"GROUP BY anyo " +
					"ORDER BY anyo";

					preparedStatement = connection.prepareStatement(queryString,
									ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_READ_ONLY);
					
					/* Fill "preparedStatement". */    
					preparedStatement.setInt(1, yearFrom);
					preparedStatement.setInt(2, yearTo);
					break;
					
					
				case 2: // concrete place
					queryString = 
					"SELECT " +
						"MAX(" + field + ") AS max," +
						"MIN(" + field + ") AS min," +
						"AVG(" + field + ") AS avg " +
					"FROM time_dimension, location_dimension, meteofact " +
					"WHERE NOT (" + field + " = 0) " +
						"AND anyo BETWEEN ? AND ? " +
						"AND meteofact.idtime = time_dimension.idtime " +
						"AND latitude = ? AND longitude = ? " +
						"AND meteofact.idlocation = location_dimension.idlocation " +
					"GROUP BY anyo " +
					"ORDER BY anyo";

					preparedStatement = connection.prepareStatement(queryString,
									ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_READ_ONLY);
					
					/* Fill "preparedStatement". */    
					preparedStatement.setInt(1, yearFrom);
					preparedStatement.setInt(2, yearTo);
					preparedStatement.setInt(3, latitude);
					preparedStatement.setInt(4, longitude);
					break;
			}
			
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
					"WHERE NOT (" + field + " = 0) " +
						"AND latitude = ? AND longitude = ? " +
						"AND meteofact.idlocation = location_dimension.idlocation";
			
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
