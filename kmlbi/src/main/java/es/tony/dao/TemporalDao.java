package es.tony.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import es.tony.auxiliar.Fecha;


public class TemporalDao extends ConexionBaseDatos {

	public void introducirDatos(List<Fecha> fechas) {
		init();
		String query = "INSERT INTO Time_Dimension VALUES " + "(?,?,?,?)";
		PreparedStatement psInsert = null;
		Connection conn = this.getConnection();

		try {
			psInsert = conn.prepareStatement(query);
			for (int i = 0; i < fechas.size(); i++) {
				psInsert.setInt(1, fechas.get(i).getAnyo() * 10000
						+ fechas.get(i).getMes() * 100 + fechas.get(i).getDia());
				psInsert.setInt(2, fechas.get(i).getAnyo());
				psInsert.setInt(3, fechas.get(i).getMes());
				psInsert.setInt(4, fechas.get(i).getDia());
				psInsert.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeQuiet(conn);
		}
	}

}
