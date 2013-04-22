package es.tony.main;

import org.apache.log4j.Logger;

import es.tony.configuration.Configurator;
import es.tony.configuration.ETLConf;
import es.tony.dao.ConexionBaseDatos;
import es.tony.datewarehouseII.ETLPipe;
import es.tony.datewarehouseII.LocationDimensionCreator;
import es.tony.datewarehouseII.TemporalDimensionCreator;
public class PipeCreator {

	private final static Logger log = Logger.getLogger(PipeCreator.class);

	public static void main(String[] args) {
		// Se crea la tabla con datos temporarles que apareceran en el DWH
		ETLConf.configure();
		TemporalDimensionCreator.createDimension();
		LocationDimensionCreator.createDimension();
		ETLPipe etlPipe_1;
		ETLPipe etlPipe_2;
		ETLPipe etlPipe_3;
		ConexionBaseDatos.init();

		Configurator configurator = new Configurator("postgres",
				"password", "1983", "2012", "org.postgresql.Driver",
				"jdbc:postgresql://localhost:5432/MeteoDB", "45", "36", "5",
				"-9", "10", "SolarRadiation", "swv_dwn");
		etlPipe_1 = new ETLPipe(configurator);


		try {
			etlPipe_1.join();
		} catch (InterruptedException e) {
			log.error(e.getStackTrace().toString());
		}
	}
}
