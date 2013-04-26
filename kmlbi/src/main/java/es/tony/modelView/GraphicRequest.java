package es.tony.modelView;

import es.tony.persistence.TypeMeasure;

public class GraphicRequest {
	private TypeMeasure typeMeasure;	
	private int yearFrom;
	private int yearTo;
	private int scale;
	private int latitude;
	private int longitude;
	
	
	public GraphicRequest(TypeMeasure typeMeasure, int yearFrom, int yearTo,
			int scale, int latitude, int longitude) {
		this.typeMeasure = typeMeasure;
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.scale = scale;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public TypeMeasure getTypeMeasure() {
		return typeMeasure;
	}


	public void setTypeMeasure(TypeMeasure typeMeasure) {
		this.typeMeasure = typeMeasure;
	}


	public int getYearFrom() {
		return yearFrom;
	}


	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}


	public int getYearTo() {
		return yearTo;
	}


	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}


	public int getScale() {
		return scale;
	}


	public void setScale(int scale) {
		this.scale = scale;
	}


	public int getLatitude() {
		return latitude;
	}


	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}


	public int getLongitude() {
		return longitude;
	}


	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
}