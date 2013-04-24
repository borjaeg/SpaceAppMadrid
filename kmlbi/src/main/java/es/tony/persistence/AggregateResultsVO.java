package es.tony.persistence;


public class AggregateResultsVO {
	private double avg;
	private double max;
	private double min;
	
	
	public AggregateResultsVO() {}
	
	
	public AggregateResultsVO(double avg, double max, double min) {
		super();
		this.avg = avg;
		this.max = max;
		this.min = min;
	}


	public double getAvg() {
		return avg;
	}


	public void setAvg(double avg) {
		this.avg = avg;
	}


	public double getMax() {
		return max;
	}


	public void setMax(double max) {
		this.max = max;
	}


	public double getMin() {
		return min;
	}


	public void setMin(double min) {
		this.min = min;
	}
}

