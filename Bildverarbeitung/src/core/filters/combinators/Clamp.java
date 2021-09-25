package core.filters.combinators;

public class Clamp {
	private double min;
	private double max;

	public Clamp(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public static Clamp getDefault() {
		return new Clamp(0, 255);
	}

	public double clamp(double val) {
		return clamp(val, min, max);
	}

	public static double clamp(double val, double min, double max) {
		if (val < min)
			return min;
		if (val > max)
			return max;
		return val;
	}
	
	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public void setMax(double max) {
		this.max = max;
	}

}
