package es.tony.persistence;


public class MeteofactVO {
	private int anyo;
	private int mes;
	private int dia;
	private double latitude;
	private double longitude;
	private double solar_radiation;
	private double relative_humidity;
	private double dew_frost;
	private double temperature;
	private double windspeed;
	private double pricipitation;
	
	
	public MeteofactVO() {}


	public MeteofactVO(int anyo, int mes, int dia, double latitude,
			double longitude, double solar_radiation, double relative_humidity,
			double dew_frost, double temperature, double windspeed,
			double pricipitation) {
		super();
		this.anyo = anyo;
		this.mes = mes;
		this.dia = dia;
		this.latitude = latitude;
		this.longitude = longitude;
		this.solar_radiation = solar_radiation;
		this.relative_humidity = relative_humidity;
		this.dew_frost = dew_frost;
		this.temperature = temperature;
		this.windspeed = windspeed;
		this.pricipitation = pricipitation;
	}


	public int getAnyo() {
		return anyo;
	}


	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getSolar_radiation() {
		return solar_radiation;
	}


	public void setSolar_radiation(double solar_radiation) {
		this.solar_radiation = solar_radiation;
	}


	public double getRelative_humidity() {
		return relative_humidity;
	}


	public void setRelative_humidity(double relative_humidity) {
		this.relative_humidity = relative_humidity;
	}


	public double getDew_frost() {
		return dew_frost;
	}


	public void setDew_frost(double dew_frost) {
		this.dew_frost = dew_frost;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


	public double getWindspeed() {
		return windspeed;
	}


	public void setWindspeed(double windspeed) {
		this.windspeed = windspeed;
	}


	public double getPricipitation() {
		return pricipitation;
	}


	public void setPricipitation(double pricipitation) {
		this.pricipitation = pricipitation;
	}
}
