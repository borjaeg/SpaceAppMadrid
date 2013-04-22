package es.tony.etl.load;

import java.util.List;

import org.apache.log4j.Logger;

import es.tony.auxiliar.Stage;
import es.tony.dao.MeasureDao;
import es.tony.datewarehouseII.ETLProcess;


public class LoadProcessMeteo implements ETLProcess {

	private final static Logger log = Logger.getLogger(LoadProcessMeteo.class);
	List<Stage> datos;

	public LoadProcessMeteo(List<Stage> datos) {
		this.datos = datos;
	}

	public Object doIt() {

		MeasureDao sunRadiationDao = new MeasureDao();
		sunRadiationDao
				.insertarDatos(datos);
		return null;
	}

}
