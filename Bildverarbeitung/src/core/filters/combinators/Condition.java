package core.filters.combinators;

import core.filters.Filter;
import core.filters.Matrix;

public interface Condition {
	/**
	 * Decides, which filterOutput to use
	 * @param filterOutputA The output of filter A
	 * @param filterOutputB The output of filter B
	 * @param imageData The input matrix
	 * @param fA Filter A
	 * @param fB Filter B
	 * @return {@code true} to use filterOutputA, {@code false} to use filterOutputB
	 */
	public boolean apply(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB);
}
