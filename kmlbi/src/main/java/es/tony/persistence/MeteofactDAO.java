package es.tony.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MeteofactDAO {
	
	public static List<AggregateSolarRadiationVO> getDataGraphics(Connection connection, 
			int yearBegin, int yearEnd) {
		ArrayList<AggregateSolarRadiationVO> list = null;
		
		try {	
			/* Create "preparedStatement". */
			String queryString = 
					"SELECT " +
						"MAX(horizonalsolarradiation) AS max," +
						"MIN(horizonalsolarradiation) AS min," +
						"AVG(horizonalsolarradiation) AS avg " +
					"FROM time_dimension, meteofact " +
					"WHERE anyo BETWEEN ? AND ? " +
						"AND meteofact.idtime = time_dimension.idtime " +
					"GROUP BY anyo " +
					"ORDER BY anyo";
			/*String queryString = 
					"SELECT " +
						"MAX(horizonalsolarradiation) as max " +
						"MIN(horizonalsolarradiation) as min " +
						"AVG(horizonalsolarradiation) as avg " +
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
			list = new ArrayList<AggregateSolarRadiationVO>();
			while (resultSet.next()) {
				AggregateSolarRadiationVO aggregate = new AggregateSolarRadiationVO();
				aggregate.setSunRadiationMax(resultSet.getDouble(1));
				aggregate.setSunRadiationMin(resultSet.getDouble(2));
				aggregate.setSunRadiationAvg(resultSet.getDouble(3));
				list.add(aggregate);
			}
			if (list.size() == 0) list = null;
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		return list;
	}
	
	
	public static double getSolarRadiation(Connection connection, 
			int latitude, int longitude) {
		double result = 0;
		
		try {	
			/* Create "preparedStatement". */
			String queryString = 
					"SELECT " +
						"AVG(horizonalsolarradiation) AS avg " +
					"FROM location_dimension, meteofact " +
					"WHERE latitude = ? AND longitude = ? " +
						"AND meteofact.idlocation = location_dimension.idlocation " +
						"AND NOT (horizonalsolarradiation = 0)";
			
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
	
	
	public static double getWindSpeed(Connection connection, 
			int latitude, int longitude) {
		double result = 0;
		
		try {	
			/* Create "preparedStatement". */
			String queryString = 
					"SELECT " +
						"AVG(windspeed) AS avg " +
					"FROM location_dimension, meteofact " +
					"WHERE latitude = ? AND longitude = ? " +
						"AND meteofact.idlocation = location_dimension.idlocation " +
						"AND NOT (windspeed = 0)";
			
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
}
