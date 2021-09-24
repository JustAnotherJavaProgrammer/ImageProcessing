package core.filters.algorithms;

import core.Util;
import core.filters.Filter;
import core.filters.Matrix;
import core.filters.MatrixUtils;

public class MaximumValueFilter implements Filter {

	private static MaximumValueFilter instance;

	public static MaximumValueFilter getInstance() {
		if (instance == null)
			instance = new MaximumValueFilter();
		return instance;
	}

	@Override
	public double transform(Matrix imageData) {
		return Util.maxOfMultiple(MatrixUtils.flatten(imageData.toArray()));
	}

}
