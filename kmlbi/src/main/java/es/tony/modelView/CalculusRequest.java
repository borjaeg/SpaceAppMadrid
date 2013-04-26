package es.tony.modelView;

public class CalculusRequest {
	private double surface;
	private int generators;
	private int efficiency;
	private int latitude;
	private int longitude;
	
	
	public CalculusRequest(double surface, int generators, int efficiency,
			int latitude, int longitude) {
		this.surface = surface;
		this.generators = generators;
		this.efficiency = efficiency;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public double getSurface() {
		return surface;
	}


	public void setSurface(double surface) {
		this.surface = surface;
	}


	public int getGenerators() {
		return generators;
	}


	public void setGenerators(int generators) {
		this.generators = generators;
	}


	public int getEfficiency() {
		return efficiency;
	}


	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
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
