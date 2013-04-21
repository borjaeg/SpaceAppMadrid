package es.tony.domain;

import java.util.Date;

public interface RequestIF {

	public float getLatitudeFrom();

	public float getLatitudeTo();

	public float getLongitudeFrom();

	public float getLongitudeTo();

	public void setLatitudeTo(float latitude);

	public void setLatitudeFrom(float latitude);

	public void setLongitudeTo(float latitude);

	public void setLongitudeFrom(float latitude);

	public int getAnyoFrom();

	public void setAnyoFrom(int year);

	public int getAnyoTo();

	public void setAnyoTo(int year);

	public void setAnyo(int anyo);

	public String getOperation();

	public void setOperation(String operation);

	public String getFechaTo();

	public void setFechaTo(String fecha);

	public String getFechaFrom();

	public void setFechaFrom(String fecha);

	public int getMonthFrom();

	public void setMothFrom(int month);

	public int getMonthTo();

	public void setMothTo(int month);
	
	public String getAggr();
	
	public void  setAggr(String aggr);

}
