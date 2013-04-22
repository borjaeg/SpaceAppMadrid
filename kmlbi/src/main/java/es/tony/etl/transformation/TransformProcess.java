package es.tony.etl.transformation;


import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import ch.hsr.geohash.GeoHash;
import es.tony.auxiliar.Stage;
import es.tony.datewarehouseII.ETLProcess;


public class TransformProcess implements ETLProcess {

	String datos;
	private final static Logger log = Logger.getLogger(TransformProcess.class);

	public TransformProcess(String datos) {
		this.datos = datos;
	}

	public List<Stage> doIt() {
		String patron = "Location: Latitude {0}   Longitude {1}";
		String patron2 = "Elevation (meters): Average for one degree lat/lon region = {0}   Site = na";
		String patron3 = "Climate zone: {0} (reference Briggs et al, http://www.energycodes.gov)";
		// String patron4 =
		// "{0} {1} {2} {3} {4} {5} {6} {7} {8} {9} {10} {11} {12} {13} {14} {15} {16} {17} {18}";
		String patron4 = "{0} {1} {2} {3} {4} {5} {6} {7} {8}";
		MessageFormat mf = new MessageFormat(patron);
		MessageFormat mf2 = new MessageFormat(patron2);
		MessageFormat mf3 = new MessageFormat(patron3);
		MessageFormat mf4 = new MessageFormat(patron4);
		Object data[] = null;
		Object data2[] = null;
		Object data3[] = null;
		Object data4[] = null;
		List<Stage> lista = new ArrayList<Stage>();
		Scanner sc = new Scanner(datos);
		Stage stage = null;
		sc.nextLine();
		sc.nextLine();
		// Latitude&Longitude
		try {
			data = mf.parse(sc.nextLine());
			System.out.println(data[0]);
			System.out.println(data[1]);
			// data = normalizeLatLong(data);
		} catch (ParseException e) {
			log.error(e.getStackTrace().toString());
		}
		// Elevation
		try {
			data2 = mf2.parse(sc.nextLine());
		} catch (ParseException e) {
			log.error(e.getStackTrace().toString());
		}
		// Climate Zone
		try {
			data3 = mf3.parse(sc.nextLine());
		} catch (ParseException e) {
			log.error(e.getStackTrace().toString());
		}
		for (int i = 0; i < 11; i++) //18
			sc.nextLine();
		// Date&Measure
		while (sc.hasNextLine()) {
			try {

				data4 = mf4.parse(sc.nextLine().replaceAll("\\s\\s+", " "));
//				for (int i = 0; i < 11; i++) //18
//					System.out.println(data4[i]);
			} catch (ParseException e) {
				log.error(e.getStackTrace().toString());
			}
			for (int i = 3; i < data4.length; i++) {
				String sunRa = (String) data4[i];
				if (sunRa.trim().equals("-")) {
					data4[i] = "0.0";
				} else if (sunRa.trim().equals("")) {
					data4[i] = "0.0";
				}
			}
			stage = new Stage();
			stage.setLat((String) data[0]);
			System.out.println("Lat: " + stage.getLat());
			stage.setLongi((String) data[1]);
			System.out.println("Long: " + stage.getLongi());
			stage.setElevation((String) data2[0]);
			stage.setClimateZone((String) data3[0]);
			stage.setYear((String) data4[0]);
			stage.setMonth((String) data4[1]);
			stage.setDay((String) data4[2]);
			stage.setHorizontalSolarRadiation((String) data4[3]);
			stage.setRelativeHumidity((String) data4[4]);
			stage.setDewFrost((String) data4[5]);
			stage.setAvgTemperature((String) data4[6]);
			stage.setWindSpeed((String) data4[7]);
			stage.setPrecipitation((String) data4[8]);

			stage.setGeoHash(GeoHash
					.withCharacterPrecision(Double.valueOf((String) data[0]),
							Double.valueOf((String) data[1]), 12).toBase32()
					.toString());
			System.out.println("GeoHash: " + stage.getGeoHash());
			lista.add(stage);
		}
		return lista;
	}

	// private Object[] normalizeLatLong(Object[] data) {
	// String[] aux;
	// aux = ((String) data[0]).split(".");
	// data[0] = aux[0];
	// aux = ((String) data[1]).split(".");
	// data[1] = aux[1];
	//
	// return data;
	// }

}
