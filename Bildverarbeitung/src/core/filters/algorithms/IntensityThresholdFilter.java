package core.filters.algorithms;

import core.filters.Filter;
import core.filters.Matrix;

public class IntensityThresholdFilter implements Filter {

	private double threshold;
	public IntensityThresholdFilter() {
		this(128);
	}
	
	public IntensityThresholdFilter(double threshold) {
		this.threshold = threshold;
	}
	
	@Override
	public double transform(Matrix imageData) {
		return imageData.getValue(1, 1) < threshold ? 0: 255;
	}
	
	public double getThreshold() {
		return threshold;
	}
	
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

}
