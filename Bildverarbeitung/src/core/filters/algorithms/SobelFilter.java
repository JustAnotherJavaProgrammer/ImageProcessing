package core.filters.algorithms;

import core.filters.Filter;
import core.filters.Matrix;

public class SobelFilter implements Filter {
	private static SobelFilter instance;

	public static SobelFilter getInstance() {
		if (instance == null)
			instance = new SobelFilter();
		return instance;
	}

	private Filter sobelX = CommonMaskFilters.sobelX();
	private Filter sobelY = CommonMaskFilters.sobelY();

	@Override
	public double transform(Matrix imageData) {
		double sX = sobelX.transform(imageData);
		double sY = sobelY.transform(imageData);
		return Math.sqrt(sX * sX + sY * sY);
	}

}
