package core.filters.combinators;

import core.filters.Filter;
import core.filters.Matrix;

public interface Combinator {
	public double combine(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB);
}
