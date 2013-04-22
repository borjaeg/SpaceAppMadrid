package es.tony.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocationDao extends ConexionBaseDatos {

	public void insertarDatos(String keyLocation, int latitud,
			int longitud) {
		String query = "INSERT INTO Location_Dimension VALUES " + "(?,?,?,?,?," +
				"?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement psInsert = null;
		Connection conn;
		String[] aux = keyLocation.split("(?!^)");
		conn = this.getConnection();
		try {
			psInsert = conn.prepareStatement(query);
			psInsert.setString(1, keyLocation);
			psInsert.setString(2, aux[0]);
			psInsert.setString(3, aux[1]);
			psInsert.setString(4, aux[2]);
			psInsert.setString(5, aux[3]);
			psInsert.setString(6, aux[4]);
			psInsert.setString(7, aux[5]);
			psInsert.setString(8, aux[6]);
			psInsert.setString(9, aux[7]);
			psInsert.setString(10, aux[8]);
			psInsert.setString(11, aux[9]);
			psInsert.setString(12, aux[10]);
			psInsert.setString(13, aux[11]);
			psInsert.setInt(14, latitud);
			psInsert.setInt(15, longitud);
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeQuiet(conn);
		}
	}
}
