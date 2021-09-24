package core.filters.algorithms;

import core.Util;
import core.filters.Filter;
import core.filters.Matrix;
import core.filters.MatrixUtils;

public class MinimumValueFilter implements Filter {

	private static MinimumValueFilter instance;

	public static MinimumValueFilter getInstance() {
		if (instance == null)
			instance = new MinimumValueFilter();
		return instance;
	}

	@Override
	public double transform(Matrix imageData) {
		return Util.minOfMultiple(MatrixUtils.flatten(imageData.toArray()));
	}

}
