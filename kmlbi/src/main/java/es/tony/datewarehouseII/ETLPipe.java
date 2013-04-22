package es.tony.datewarehouseII;

import es.tony.configuration.Configurator;
import es.tony.configuration.ETLConf;
import es.tony.etl.extraction.ExtractCommand;

public class ETLPipe extends Thread {

	private static final int INTERVALOS = Integer.parseInt(ETLConf
			.getGranularity());

	private static Configurator configurator;

	public static Configurator getConfigurator() {
		return ETLPipe.configurator;
	}

	public ETLPipe(Configurator configurator) {
		ETLPipe.configurator = configurator;
	}

	private int calculaIntervaloLat() {
		return (Integer.parseInt(configurator.getLatMax()) - Integer
				.parseInt(configurator.getLatMin()));
	}
	
	private int calculaIntervaloLong() {
		return (Integer.parseInt(configurator.getLongMax()) - Integer
				.parseInt(configurator.getLongMin()));
	}

	@Override
	public void run() {
		int intervaloLat = calculaIntervaloLat();
		System.out.println(intervaloLat);
		int intervaloLong = calculaIntervaloLong();
		System.out.println(intervaloLong);
		double latitude = Double.valueOf(configurator.getLatMin());
		
		for (int i = 0; i < intervaloLat; i++) {
			double longitude = Double.valueOf(configurator.getLongMin());
			for (int j = 0; j < intervaloLong; j++) {
				ExtractCommand ec = new ExtractCommand(latitude, longitude);
				ec.execute();
				longitude += 1;
			}
			latitude += 1;
		}
	}
}
