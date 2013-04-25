package es.tony.domain;

public class OlapData {

	private float latitude;
	private float longitude;
	private double measure;
	private String date;
	private String nextDate;

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

	public double getMeasure() {
		return measure;
	}

	public void setMeasure(double measure) {
		this.measure = measure;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	@Override
	public String toString() {
		return "OlapData [latitude=" + latitude + ", longitude=" + longitude
				+ ", measure=" + measure + ", sunRadiationMax="
				+ ", sunRadiationMin=" + ", date=" + date + "]";
	}

}
