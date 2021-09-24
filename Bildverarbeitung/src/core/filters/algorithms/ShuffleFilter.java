package core.filters.algorithms;

import core.Util;
import core.filters.Filter;
import core.filters.Matrix;
import core.filters.MatrixUtils;

public class ShuffleFilter implements Filter {
	private static ShuffleFilter instance;

	public static ShuffleFilter getInstance() {
		if (instance == null)
			instance = new ShuffleFilter();
		return instance;
	}

	@Override
	public double transform(Matrix imageData) {
		return Util.randomElement(MatrixUtils.flatten(imageData.toArray()));
	}

}
