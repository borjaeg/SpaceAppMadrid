package es.tony.etl.load;

import java.util.List;

import org.apache.log4j.Logger;

import es.tony.auxiliar.Stage;
import es.tony.datewarehouseII.ETLProcess;

public class LoadProcessLocation implements ETLProcess {

	private final static Logger log = Logger.getLogger(LoadProcessMeteo.class);
	List<Stage> datos;

	public LoadProcessLocation(List<Stage> datos) {
		this.datos = datos;
	}

	public Object doIt() {

		return null;
	}

}
