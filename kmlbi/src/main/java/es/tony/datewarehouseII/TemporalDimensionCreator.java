package es.tony.datewarehouseII;

import java.util.ArrayList;
import java.util.List;

import es.tony.auxiliar.Fecha;
import es.tony.configuration.ETLConf;
import es.tony.dao.TemporalDao;

public class TemporalDimensionCreator {

	public static void createDimension() {
		int minYear = Integer.parseInt(ETLConf.getMinYear());
		int maxYear = Integer.parseInt(ETLConf.getMaxYear());
		List<Fecha> fechas = new ArrayList<Fecha>(365);
		for (int i = minYear; i <= maxYear; i++) {
			for (int k = 1; k <= 12; k++) {
				if (k == 1 || k == 3 || k == 5 || k == 7 || k == 8 || k == 10
						|| k == 12) {
					for (int j = 1; j <= 31; j++) {
						fechas.add(new Fecha(j, k, i));
					}
				} else if (k == 4 || k == 6 || k == 9 || k == 11) {
					for (int j = 1; j <= 30; j++) {
						fechas.add(new Fecha(j, k, i));
					}
				} else if (k == 2) {
					for (int j = 1; j <= 29; j++) {
						fechas.add(new Fecha(j, k, i));
					}
				}
			}
		}
		TemporalDao temporalDao = new TemporalDao();
		temporalDao.introducirDatos(fechas);
	}

}
