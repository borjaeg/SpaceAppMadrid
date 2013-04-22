package es.tony.datewarehouseII;

import ch.hsr.geohash.GeoHash;
import es.tony.configuration.ETLConf;
import es.tony.dao.LocationDao;

public class LocationDimensionCreator {

	public static void createDimension() {
		int longitud = Integer.parseInt(ETLConf.getLongMin());
		int latitud = Integer.parseInt(ETLConf.getLatMin());

		for (int i = Integer.parseInt(ETLConf.getLatMin()); i <= Integer
				.parseInt(ETLConf.getLatMax()); i++) {
			longitud = Integer.parseInt(ETLConf.getLongMin());
			for (int k = Integer.parseInt(ETLConf.getLongMin()); k <= Integer
					.parseInt(ETLConf.getLongMax()); k++) {
				LocationDao locatioDao = new LocationDao();
				locatioDao.insertarDatos(
						GeoHash.withCharacterPrecision(latitud, longitud, 12)
								.toBase32().toString(), latitud, longitud);
				longitud += 1;
			}
			latitud += 1;
		}
	}
}
