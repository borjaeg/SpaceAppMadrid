package es.tony.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import es.tony.configuration.ETLConf;

public class ConexionBaseDatos {

	private static DataSource dataSource = null;

	/**
	 * Inicializa los recursos comunes para todos los daos.
	 * 
	 * @param properties
	 */
	public static void init() {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(ETLConf.getURLName());
		dataSource.setDriverClassName(ETLConf.getDriverName());
		dataSource.setUsername(ETLConf.getUser());
		dataSource.setPassword(ETLConf.getPass());

		ConexionBaseDatos.dataSource = dataSource;

	} // init.

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} // getConnection.

	protected void closeQuiet(Connection conn) {

		if (null != conn) {

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // closeQuiet.

}
