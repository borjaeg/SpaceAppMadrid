package es.tony.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import es.tony.auxiliar.Stage;

public class MeasureDao extends ConexionBaseDatos {

	public void insertarDatos(List<Stage> datos) {

//		String qInsert = "INSERT INTO MeteoFact VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String qInsert = "INSERT INTO MeteoFact VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement psInsert = null;
		Connection conn = this.getConnection();
		int keyTime = 0;
		String keyLocation = "";

		try {
			for (int i = 0; i < datos.size(); i++) {
				keyTime = Integer.parseInt(datos.get(i).getYear()) * 10000
						+ Integer.parseInt(datos.get(i).getMonth()) * 100
						+ Integer.parseInt(datos.get(i).getDay());
				keyLocation = datos.get(i).getGeoHash();
				
				psInsert = conn.prepareStatement(qInsert);
				psInsert.setString(1, keyLocation);
				psInsert.setInt(2, keyTime);
				psInsert.setDouble(3,Double.valueOf(datos.get(i).getHorizontalSolarRadiation()));
//				psInsert.setDouble(4,Double.valueOf(datos.get(i).getRadiativeFlux()));
//				psInsert.setDouble(5,Double.valueOf(datos.get(i).getTopInsolation()));
//				psInsert.setDouble(6,Double.valueOf(datos.get(i).getInsolationClearness()));
//				psInsert.setDouble(7,Double.valueOf(datos.get(i).getClearSkyHorizontalSolarRadiation()));
//				psInsert.setDouble(8,Double.valueOf(datos.get(i).getClearSkyInsolationClearness()));
//				psInsert.setDouble(9,Double.valueOf(datos.get(i).getAvgAtmosphericPreassure()));
//				psInsert.setDouble(10,Double.valueOf(datos.get(i).getAvgAirTemperature()));
//				psInsert.setDouble(11,Double.valueOf(datos.get(i).getMinAirTemperature()));
//				psInsert.setDouble(12,Double.valueOf(datos.get(i).getMaxAirTemperature()));
//				psInsert.setDouble(4,Double.valueOf(datos.get(i).getAvgHumidityRadio()));
				psInsert.setDouble(4,Double.valueOf(datos.get(i).getRelativeHumidity()));
				psInsert.setDouble(5,Double.valueOf(datos.get(i).getDewFrost()));
				psInsert.setDouble(6,Double.valueOf(datos.get(i).getAvgTemperature()));
				psInsert.setDouble(7,Double.valueOf(datos.get(i).getWindSpeed()));
				psInsert.setDouble(8,Double.valueOf(datos.get(i).getPrecipitation()));
				psInsert.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeQuiet(conn);
		}
	}
}
