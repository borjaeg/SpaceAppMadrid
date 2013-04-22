package es.tony.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ETLConf {

	private static String user;
	private static String pass;
	private static String minYear;
	private static String maxYear;
	private static String driver_name;
	private static String driver_url;
	private static String latMax;
	private static String latMin;
	private static String longMax;
	private static String longMin;
	private static String granularity;
	private static String measureColumn;
	private static final String USER = "user";
	private static final String PASS = "password";
	private static final String MIN_YEAR = "minYear";
	private static final String MAX_YEAR = "maxYear";
	private static final String DRIVER_NAME = "driver_name";
	private static final String DRIVER_URL = "driver_url";
	private static final String LATMAX = "latMax";
	private static final String LATMIN = "latMin";
	private static final String LONGMAX = "longMax";
	private static final String LONGMIN = "longMin";
	private static final String GRANULARITY = "granularidad";

	public ETLConf() {

	}

	public ETLConf(Configurator configurator) {
		ETLConf.user = configurator.getUser();
		ETLConf.pass = configurator.getPass();
		ETLConf.minYear = configurator.getMinYear();
		ETLConf.maxYear = configurator.getMaxYear();
		ETLConf.driver_name = configurator.getDriver_name();
		ETLConf.driver_url = configurator.getDriver_url();
		ETLConf.latMax = configurator.getLatMax();
		ETLConf.latMin = configurator.getLatMin();
		ETLConf.longMax = configurator.getLatMax();
		ETLConf.longMin = configurator.getLatMin();
		ETLConf.granularity = configurator.getGranularity();
		ETLConf.measureColumn = configurator.getMeasureTable();
	}

	public static void configure() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = ETLConf.class.getClassLoader().getResourceAsStream(
					"ETLConf.properties");
			prop.load(is);
			user = prop.getProperty(USER);
			pass = prop.getProperty(PASS);
			minYear = prop.getProperty(MIN_YEAR);
			maxYear = prop.getProperty(MAX_YEAR);
			driver_name = prop.getProperty(DRIVER_NAME);
			driver_url = prop.getProperty(DRIVER_URL);
			latMax = prop.getProperty(LATMAX);
			latMin = prop.getProperty(LATMIN);
			longMax = prop.getProperty(LONGMAX);
			longMin = prop.getProperty(LONGMIN);
			granularity = prop.getProperty(GRANULARITY);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getUser() {
		return user;
	}

	public static String getPass() {
		return pass;
	}

	public static String getMinYear() {
		return minYear;
	}

	public static String getMaxYear() {
		return maxYear;
	}

	public static String getDriverName() {
		return driver_name;
	}

	public static String getURLName() {
		return driver_url;
	}

	public static String getMaxLat() {
		return latMax;
	}

	public static String getMinLat() {
		return latMin;
	}

	public static String getLongMax() {
		return longMax;
	}

	public static String getLongMin() {
		return longMin;
	}

	public static String getGranularity() {
		return granularity;
	}

	public static String getMeasureTable() {
		return measureColumn;
	}

	public static String getDriver_name() {
		return driver_name;
	}

	public static void setDriver_name(String driver_name) {
		ETLConf.driver_name = driver_name;
	}

	public static String getDriver_url() {
		return driver_url;
	}

	public static void setDriver_url(String driver_url) {
		ETLConf.driver_url = driver_url;
	}

	public static String getLatMax() {
		return latMax;
	}

	public static void setLatMax(String latMax) {
		ETLConf.latMax = latMax;
	}

	public static String getLatMin() {
		return latMin;
	}

	public static void setLatMin(String latMin) {
		ETLConf.latMin = latMin;
	}

	public static void setUser(String user) {
		ETLConf.user = user;
	}

	public static void setPass(String pass) {
		ETLConf.pass = pass;
	}

	public static void setMinYear(String minYear) {
		ETLConf.minYear = minYear;
	}

	public static void setMaxYear(String maxYear) {
		ETLConf.maxYear = maxYear;
	}

	public static void setLongMax(String longMax) {
		ETLConf.longMax = longMax;
	}

	public static void setLongMin(String longMin) {
		ETLConf.longMin = longMin;
	}

	public static void setGranularity(String granularity) {
		ETLConf.granularity = granularity;
	}

	public static void setMeasureColumn(String measureColumn) {
		ETLConf.measureColumn = measureColumn;
	}

	public static String getMeasureColumn() {
		return measureColumn;
	}

}
