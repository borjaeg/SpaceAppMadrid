package es.tony.businessLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Polygon;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;
import es.tony.domain.OlapData;

public class KmlCreator {
	List<OlapData> olapDataList;
	final Kml kml = KmlFactory.createKml();
	private final static Logger log = Logger.getLogger(KmlCreator.class);

	public KmlCreator(List<OlapData> olapDataList) {
		this.olapDataList = olapDataList;
	}

	public Kml getKml() {
		return kml;
	}

	public File doIt() {
		final Document document = kml.createAndSetDocument();

		String units = "";
		if (MdxProcess.measure.equals("Solar Radiation"))
			units = "kWh/m^2";
		else if (MdxProcess.measure.equals("Wind Speed")) {
			units = "m/s";
		} else if (MdxProcess.measure.equals("Dew Frost")) {
			units = "C (degrees)";
		} else if (MdxProcess.measure.equals("Relative Humidity")) {
			units = "% (Percentage)";
		} else if (MdxProcess.measure.equals("Precipitation")) {
			units = "mm3";
		} else if (MdxProcess.measure.equals("Temperature")) {
			units = "C (degrees)";
		}

		for (int i = 0; i < olapDataList.size(); i++) {
			document.createAndAddStyle()
					.withId("examplePolyStyle" + i)
					.createAndSetPolyStyle()
					.withOutline(false)
					.withColor(
							sunRadiationToColor(olapDataList.get(i)
									.getMeasure()));

			int decimal = String.valueOf(
					olapDataList.get(i).getMeasure()).indexOf('.');

			String formattedData = String.valueOf(
					olapDataList.get(i).getMeasure()).substring(0,
					decimal + 2);

			TimeSpan ts = new TimeSpan().withBegin(
					olapDataList.get(i).getDate()).withEnd(
					olapDataList.get(i).getNextDate());
			Placemark placemark = document
					.createAndAddPlacemark()
					.withName("Summary:")
					.withDescription(
							"<![CDATA[ <br>Latitude: "
									+ olapDataList.get(i).getLatitude()
									+ "<br>Longitude: "
									+ olapDataList.get(i).getLongitude()
									+ "<br>" + "Value: " + formattedData
									+ "<br>Units: " + units + "<br> "
									+ "<a href = \"analize.html\">Energy2Money!!</a>")
					.withStyleUrl("#examplePolyStyle" + i)
					.withTimePrimitive(ts);
			Polygon polygon = placemark.createAndSetPolygon().withExtrude(true)
					.withExtrude(true)
					.withAltitudeMode(AltitudeMode.RELATIVE_TO_GROUND);
			latLongToCoordinates(olapDataList.get(i).getLatitude(),
					olapDataList.get(i).getLongitude(), polygon, olapDataList
							.get(i).getMeasure(), olapDataList.get(i)
							.getDate());
		}

		File kmlFile = null;
		try {
			kmlFile = new File("MeteoMDX.kml");
			kml.marshal(kmlFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return kmlFile;
	}

	private void latLongToCoordinates(float latitude, float longitude,
			Polygon polygon, double sunRadiationMax, String fecha) {
		float lado = (float) 0.5;
		float altura = 3200;
		double value = 0.1;

		polygon.createAndSetOuterBoundaryIs()
				.createAndSetLinearRing()
				.addToCoordinates(longitude + lado + value, latitude + lado,
						altura)
				.addToCoordinates(longitude - lado + value, latitude + lado,
						altura)
				.addToCoordinates(longitude - lado + value, latitude - lado,
						altura)
				.addToCoordinates(longitude + lado + value, latitude - lado,
						altura)
				.addToCoordinates(longitude + lado + value, latitude + lado,
						altura);

	}

	private String sunRadiationToColor(double solarRadiation) {
		String color = null;
		color = "aa";
		int rango = 20;

		Double[] valores = new Double[rango];

		double intervalo = (MdxProcess.maxValue - MdxProcess.minValue) / rango;
		valores[0] = MdxProcess.minValue;

		for (int i = 1; i < rango; i++) {
			valores[i] = valores[i - 1] + intervalo;
		}

		if (solarRadiation >= MdxProcess.minValue
				&& solarRadiation < valores[1])
			color += "ff0066";
		else if (solarRadiation >= valores[1] && solarRadiation < valores[2])
			color += "e3a34f";
		else if (solarRadiation >= valores[2] && solarRadiation < valores[3])
			color += "e3d44f";
		else if (solarRadiation >= valores[3] && solarRadiation < valores[4])
			color += "bce34f";
		else if (solarRadiation >= valores[4] && solarRadiation < valores[5])
			color += "92e34f";
		else if (solarRadiation >= valores[5] && solarRadiation < valores[6])
			color += "2cbd19";
		else if (solarRadiation >= valores[6] && solarRadiation < valores[7])
			color += "6ee094";
		else if (solarRadiation >= valores[7] && solarRadiation < valores[8])
			color += "9ce6b5";
		else if (solarRadiation >= valores[8] && solarRadiation < valores[9])
			color += "72e8c1";
		else if (solarRadiation >= valores[9] && solarRadiation < valores[10])
			color += "2df2d6";
		else if (solarRadiation >= valores[10] && solarRadiation < valores[11])
			color += "9af5f3";
		else if (solarRadiation >= valores[11] && solarRadiation < valores[12])
			color += "68F2F0";
		else if (solarRadiation >= valores[12] && solarRadiation < valores[13])
			color += "63D8F2";
		else if (solarRadiation >= valores[13] && solarRadiation < valores[14])
			color += "6db4f7";
		else if (solarRadiation >= valores[14] && solarRadiation < valores[15])
			color += "3a7bf2";
		else if (solarRadiation >= valores[15] && solarRadiation < valores[16])
			color += "60e6e6";
		else if (solarRadiation >= valores[16]
				&& solarRadiation < MdxProcess.maxValue)
			color += "3042e6";
		else {
			color += "0b1999";
			log.error("");
			log.error("");
			log.error("");
			log.error("");
			log.error("");
			log.error("");
			log.error("");
			log.error("");
			log.error("RADIACION SOLAR ERRONEA: " + solarRadiation);
		}

		return color;
	}
}
