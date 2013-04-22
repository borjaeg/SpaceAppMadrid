package es.tony.configuration;

public class Configurator {

	private String user;
	private String pass;
	private String minYear;
	private String maxYear;
	private String driver_name;
	private String driver_url;
	private String latMax;
	private String latMin;
	private String longMax;
	private String longMin;
	private String granularity;
	private String measureTable;
	private String meteoData;
	
	

	public Configurator(String user, String pass, String minYear,
			String maxYear, String driver_name, String driver_url,
			String latMax, String latMin, String longMax, String longMin,
			String granularity, String measureTable, String meteoData) {
		super();
		this.user = user;
		this.pass = pass;
		this.minYear = minYear;
		this.maxYear = maxYear;
		this.driver_name = driver_name;
		this.driver_url = driver_url;
		this.latMax = latMax;
		this.latMin = latMin;
		this.longMax = longMax;
		this.longMin = longMin;
		this.granularity = granularity;
		this.measureTable = measureTable;
		this.meteoData = meteoData;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMinYear() {
		return minYear;
	}

	public void setMinYear(String minYear) {
		this.minYear = minYear;
	}

	public String getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(String maxYear) {
		this.maxYear = maxYear;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getDriver_url() {
		return driver_url;
	}

	public void setDriver_url(String driver_url) {
		this.driver_url = driver_url;
	}

	public String getLatMax() {
		return latMax;
	}

	public void setLatMax(String latMax) {
		this.latMax = latMax;
	}

	public String getLatMin() {
		return latMin;
	}

	public void setLatMin(String latMin) {
		this.latMin = latMin;
	}

	public String getLongMax() {
		return longMax;
	}

	public void setLongMax(String longMax) {
		this.longMax = longMax;
	}

	public String getLongMin() {
		return longMin;
	}

	public void setLongMin(String longMin) {
		this.longMin = longMin;
	}

	public String getGranularity() {
		return granularity;
	}

	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}

	public String getMeasureTable() {
		return measureTable;
	}

	public void setMeasureTable(String measureTable) {
		this.measureTable = measureTable;
	}

	public String getMeteoData() {
		return this.meteoData;
	}

}
