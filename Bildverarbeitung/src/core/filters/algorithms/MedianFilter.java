package core.filters.algorithms;

import core.Util;
import core.filters.Filter;
import core.filters.Matrix;
import core.filters.MatrixUtils;

public class MedianFilter implements Filter {
	private static MedianFilter instance;

	public static MedianFilter getInstance() {
		if (instance == null)
			instance = new MedianFilter();
		return instance;
	}

	@Override
	public double transform(Matrix imageData) {
		return Util.median(MatrixUtils.flatten(imageData.toArray()));
	}

}
