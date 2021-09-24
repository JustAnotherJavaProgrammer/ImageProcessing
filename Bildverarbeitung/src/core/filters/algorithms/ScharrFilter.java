package core.filters.algorithms;

import core.filters.Filter;
import core.filters.Matrix;

public class ScharrFilter implements Filter {
	private static ScharrFilter instance;

	public static ScharrFilter getInstance() {
		if (instance == null)
			instance = new ScharrFilter();
		return instance;
	}

	private Filter scharrX = CommonMaskFilters.scharrX();
	private Filter scharrY = CommonMaskFilters.scharrY();

	@Override
	public double transform(Matrix imageData) {
		double sX = scharrX.transform(imageData);
		double sY = scharrY.transform(imageData);
		return Math.sqrt(sX * sX + sY * sY);
	}

}
