package es.tony.domain;

public class OlapData {

	private float latitude;
	private float longitude;
	private double sunRadiationAvg;
	private String fecha;
	private String fechaSig;

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public double getSunRadiationAvg() {
		return sunRadiationAvg;
	}

	public void setSunRadiationAvg(double sunRadiationAvg) {
		this.sunRadiationAvg = sunRadiationAvg;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaSig() {
		return fechaSig;
	}

	public void setFechaSig(String fechaSig) {
		this.fechaSig = fechaSig;
	}

	@Override
	public String toString() {
		return "OlapData [latitude=" + latitude + ", longitude=" + longitude
				+ ", sunRadiationAvg=" + sunRadiationAvg + ", sunRadiationMax="
				+ ", sunRadiationMin=" + ", fecha=" + fecha + "]";
	}

}
