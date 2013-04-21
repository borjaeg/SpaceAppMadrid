package es.tony.persistence;


public class AggregateSolarRadiationVO {
	private double sunRadiationAvg;
	private double sunRadiationMax;
	private double sunRadiationMin;
	
	
	public AggregateSolarRadiationVO() {}
	
	
	public AggregateSolarRadiationVO(double sunRadiationAvg, double sunRadiationMax,
			double sunRadiationMin) {
		super();
		this.sunRadiationAvg = sunRadiationAvg;
		this.sunRadiationMax = sunRadiationMax;
		this.sunRadiationMin = sunRadiationMin;
	}


	public double getSunRadiationAvg() {
		return sunRadiationAvg;
	}


	public void setSunRadiationAvg(double sunRadiationAvg) {
		this.sunRadiationAvg = sunRadiationAvg;
	}


	public double getSunRadiationMax() {
		return sunRadiationMax;
	}


	public void setSunRadiationMax(double sunRadiationMax) {
		this.sunRadiationMax = sunRadiationMax;
	}


	public double getSunRadiationMin() {
		return sunRadiationMin;
	}


	public void setSunRadiationMin(double sunRadiationMin) {
		this.sunRadiationMin = sunRadiationMin;
	}
}

