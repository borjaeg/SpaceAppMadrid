package es.tony.domain;

import java.util.Date;

public class RequestVO implements RequestIF {

	private float latitudeFrom;
	private float latitudeTo;
	private float longitudeFrom;
	private float longitudeTo;
	private String fechaFrom;
	private String fechaTo;
	private String operation;
	private int fAnyo;
	private int tAnyo;
	private int fMes;
	private int tMes;
	private String aggr;

	public float getLatitudeFrom() {
		return latitudeFrom;
	}

	public void setLatitudeFrom(float latitudeFrom) {
		this.latitudeFrom = latitudeFrom;
	}

	public float getLatitudeTo() {
		return latitudeTo;
	}

	public void setLatitudeTo(float latitudeTo) {
		this.latitudeTo = latitudeTo;
	}

	public float getLongitudeFrom() {
		return longitudeFrom;
	}

	public void setLongitudeFrom(float longitudeFrom) {
		this.longitudeFrom = longitudeFrom;
	}

	public float getLongitudeTo() {
		return longitudeTo;
	}

	public void setLongitudeTo(float longitudeTo) {
		this.longitudeTo = longitudeTo;
	}

	public String getFechaFrom() {
		return fechaFrom;
	}

	public void setFechaFrom(String fecha) {
		this.fechaFrom = fecha;
	}

	public String getFechaTo() {
		return fechaTo;
	}

	public void setFechaTo(String fecha) {
		this.fechaTo = fecha;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "RequestVO [latitudeFrom=" + latitudeFrom + ", latitudeTo="
				+ latitudeTo + ", longitudeFrom=" + longitudeFrom
				+ ", longitudeTo=" + longitudeTo + ", fecha=" + fechaFrom + ","
				+ "  operation=" + operation + "]";
	}

	@Override
	public int getAnyoFrom() {
		// TODO Auto-generated method stub
		return fAnyo;
	}

	@Override
	public void setAnyoFrom(int year) {
		this.fAnyo = year;

	}

	@Override
	public int getAnyoTo() {
		// TODO Auto-generated method stub
		return tAnyo;
	}

	@Override
	public void setAnyoTo(int year) {
		this.tAnyo = year;

	}

	@Override
	public void setAnyo(int anyo) {
		this.tAnyo = anyo;
	}

	@Override
	public int getMonthFrom() {
		return fMes;
	}

	@Override
	public void setMothFrom(int month) {
		this.fMes = month;

	}

	@Override
	public int getMonthTo() {
		return tMes;
	}

	@Override
	public void setMothTo(int month) {
		this.tMes = month;
	}

	@Override
	public String getAggr() {
		return this.aggr;
	}

	@Override
	public void setAggr(String aggr) {
		this.aggr = aggr;

	}

}
