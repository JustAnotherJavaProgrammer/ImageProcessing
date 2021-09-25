package core.filters.combinators;

import core.filters.Filter;
import core.filters.Matrix;

public class CommonConditions {
	
	/**
	 * Creates a condition, which will use use filter B if its output value is greater than or equal to the threshold value
	 * @param threshold The threshold value to be used
	 * @return The newly created threshold condition
	 */
	public static Condition thresholdB(final double threshold) {
		return new Condition() {
			
			@Override
			public boolean apply(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB) {
				return filterOutputB < threshold;
			}
		};
	}
}
