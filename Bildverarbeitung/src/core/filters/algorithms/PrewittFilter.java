package core.filters.algorithms;

import core.filters.Filter;
import core.filters.Matrix;

public class PrewittFilter implements Filter {
	private static PrewittFilter instance;

	public static PrewittFilter getInstance() {
		if (instance == null)
			instance = new PrewittFilter();
		return instance;
	}

	private Filter prewittX = CommonMaskFilters.prewittX();
	private Filter prewittY = CommonMaskFilters.prewittY();

	@Override
	public double transform(Matrix imageData) {
		double sX = prewittX.transform(imageData);
		double sY = prewittY.transform(imageData);
		return Math.sqrt(sX * sX + sY * sY);
	}

}
